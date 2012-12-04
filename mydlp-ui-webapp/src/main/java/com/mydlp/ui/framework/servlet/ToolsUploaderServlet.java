package com.mydlp.ui.framework.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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
import com.mydlp.ui.domain.DocumentDatabase;
import com.mydlp.ui.domain.DocumentDatabaseFileEntry;
import com.mydlp.ui.domain.TemporaryAccessToken;
import com.mydlp.ui.framework.util.HashUtil;
import com.mydlp.ui.service.AuditTrailService;
import com.mydlp.ui.thrift.MyDLPUIThriftService;

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
	
	@Autowired
	protected MyDLPUIThriftService thriftService;
	
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
				Long documentDatabaseId = null;
				
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
					if (token != null)
					{
						documentDatabaseId = Long.parseLong(token.getServiceParam());
					}
				} 
				catch (Throwable e)
				{
					logger.error("Error occurred when extracting document database id", e);
				}
				
				if (token == null) 
				{
					logger.error("No valid token has been found. Token is null.");
					returnStr = INVALID_TOKEN;
				}
				else if (!token.getIpAddress().equals(req.getRemoteAddr()))
				{
					logger.error("Remote address does not match with token: " + token.getIpAddress() + " " + req.getRemoteAddr());
					returnStr = INVALID_TOKEN;
				}
				else if (!token.getServiceName().equals("tools-uploader"))
				{
					logger.error("Registered token service is not tools-uploader : " + token.getServiceName());
					returnStr = INVALID_TOKEN;
				}
				else if (documentDatabaseId == null)
				{
					logger.error("Document database id is null");
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
						logger.error("File hash is null");
						returnStr = ERROR;
					}
					else if (documentDatabaseDAO.isEntryExists(documentDatabaseId.intValue(), fileHash))
					{
						returnStr = EXISTS;
					}
					else
					{
						generateDocumentDatabaseFileEntry(documentDatabaseId, fileName, fileHash, filePath);
						//auditTrailService.audit(getClass(), token.getUsername(), "tools-uploader", new Object[]{fileName});
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
	
	public void generateDocumentDatabaseFileEntry(Long documentDatabaseId, String filename,  String md5sum, String filepath) {
		DocumentDatabase documentDatabase = documentDatabaseDAO.getDocumentDatabaseById(
				new Integer(documentDatabaseId.intValue()));
		
		DocumentDatabaseFileEntry documentDatabaseFileEntry = new DocumentDatabaseFileEntry();
		documentDatabaseFileEntry.setCreatedDate(new Date());
		documentDatabaseFileEntry.setFilename(filename);
		documentDatabaseFileEntry.setMd5Hash(md5sum);
		
		if (documentDatabase.getFileEntries() == null)
			documentDatabase.setFileEntries(new ArrayList<DocumentDatabaseFileEntry>());
		
		documentDatabaseFileEntry = documentDatabaseDAO.saveFileEntry(documentDatabaseFileEntry);
		documentDatabase.getFileEntries().add(documentDatabaseFileEntry);
		documentDatabaseDAO.save(documentDatabase);
		
		thriftService.generateFingerprintsWithFile(documentDatabaseId, filename, filepath);
		
	}

}
