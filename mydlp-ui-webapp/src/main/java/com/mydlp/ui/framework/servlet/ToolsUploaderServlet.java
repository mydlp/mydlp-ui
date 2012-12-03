package com.mydlp.ui.framework.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;

import com.mydlp.ui.dao.DocumentDatabaseDAO;
import com.mydlp.ui.dao.TemporaryAccessTokenDAO;
import com.mydlp.ui.domain.TemporaryAccessToken;
import com.mydlp.ui.framework.util.HashUtil;
import com.mydlp.ui.service.AuditTrailService;

@Service("toolsUploaderServlet")
public class ToolsUploaderServlet implements HttpRequestHandler {

	private static Logger logger = LoggerFactory
			.getLogger(ToolsUploaderServlet.class);

	private static final String ERROR = "error";
	private static final String SUCCESS = "ok";
	private static final String EXISTS = "exists";
	private static final String INVALID_TOKEN = "invalidtoken";
	
	protected static final int MAX_MEMORY_SIZE = 1*1024*1024;

	// testtoken : plocalhost:8080-testtesttesttesttesttesttesttest

	@Autowired
	protected AuditTrailService auditTrailService;
	
	@Autowired
	protected TemporaryAccessTokenDAO temporaryAccessTokenDAO;
	
	@Autowired
	protected DocumentDatabaseDAO documentDatabaseDAO;
	
	@Override
	public void handleRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String returnStr = ERROR;
		
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			if (isMultipart)
			{
				String tokenKey = null;
				String fileName = null;
				String filePath = null;
				Integer documentDatabaseId = null;
				
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(MAX_MEMORY_SIZE);
				ServletFileUpload upload = new ServletFileUpload(factory);
				@SuppressWarnings("unchecked")
				List<FileItem> items = upload.parseRequest(req);
				for (FileItem fileItem : items) {
					if (fileItem.isFormField())
					{
						String fieldName = fileItem.getFieldName();
						String fieldValue = fileItem.getString();
						
						if (fieldName.equals("tokenKey"))
						{
							tokenKey = fieldValue;
						}
						else if (fieldName.equals("fileName"))
						{
							fileName = fieldValue;
						}
					}
					else
					{
						File tempFile = File.createTempFile("tools-uploader-data-", ".tmp");
						fileItem.write(tempFile);
						filePath = tempFile.getCanonicalPath();
					}
				}
				
				TemporaryAccessToken token = temporaryAccessTokenDAO.getTokenObj(tokenKey);
				
				try {
					documentDatabaseId = Integer.parseInt(token.getServiceParam());
				} 
				catch (Throwable e)
				{
					logger.error("Error occurred when extracting document database id", e);
				}
				
				if (token == null) 
				{
					returnStr = INVALID_TOKEN;
				}
				else if (!token.getIpAddress().equals(req.getRemoteAddr()))
				{
					returnStr = INVALID_TOKEN;
				}
				else if (!token.getServiceName().equals("tools-uploader"))
				{
					returnStr = INVALID_TOKEN;
				}
				else if (documentDatabaseId == null)
				{
					returnStr = INVALID_TOKEN;
				}
				else
				{
					String fileHash = null;
					
					try {
						fileHash = HashUtil.getMD5Checksum(filePath);
					}
					catch (Throwable e)
					{
						logger.error("Error occurred when creating checksum", e);
					}
				
					if (fileHash == null)
					{
						returnStr = ERROR;
					}
					else if (documentDatabaseDAO.isEntryExists(documentDatabaseId, fileHash))
					{
						returnStr = EXISTS;
					}
					else
					{
					
						
						System.out.println(tokenKey + " " + fileName + " " + filePath);
						
						auditTrailService.audit(getClass(), token.getUsername(), "tools-uploader", new Object[]{fileName});
						returnStr = SUCCESS;
					}
				}
			}
			else
			{
				logger.error("Request is not multipart");
			}
		} catch (IOException e) {
			logger.error("IOError occurred", e);
		} catch (FileUploadException e) {
			logger.error("FileUpload error occured", e);
		} catch (Throwable e) {
			logger.error("Unexpected error occured", e);
		} 
		
		if (returnStr == null || returnStr.length() == 0)
		{
			logger.error("Returned empty or null value from thrift call");
			returnStr = ERROR;
		}
		PrintWriter out = resp.getWriter();
		out.print(returnStr);
		out.flush();
		out.close();
	}

}
