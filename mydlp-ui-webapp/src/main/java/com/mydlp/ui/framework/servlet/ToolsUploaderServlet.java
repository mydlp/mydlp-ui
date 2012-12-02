package com.mydlp.ui.framework.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;

@Service("toolsUploaderServlet")
public class ToolsUploaderServlet implements HttpRequestHandler {

	private static Logger logger = LoggerFactory
			.getLogger(ToolsUploaderServlet.class);

	private static final String ERROR = "error";
	private static final String SUCCESS = "ok";
	
	protected static final int MAX_MEMORY_SIZE = 1*1024*1024;

	// testtoken : plocalhost:8080-testtesttesttesttesttesttesttest
	
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
				
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(MAX_MEMORY_SIZE);
				ServletFileUpload upload = new ServletFileUpload(factory);
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
				
				System.out.println(tokenKey + " " + fileName + " " + filePath);
				
				returnStr = SUCCESS;
			}
			else
			{
				logger.error("Reques is not multipart");
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
