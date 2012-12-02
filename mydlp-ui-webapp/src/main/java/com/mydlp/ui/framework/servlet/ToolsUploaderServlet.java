package com.mydlp.ui.framework.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	protected static final int MAX_CONTENT_LENGTH = 10*1024*1024;

	// testtoken : p127.0.0.1:8080-testtesttesttesttesttesttesttest
	
	@Override
	public void handleRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String returnStr = ERROR;
		try {
			if (req.getContentLength() < MAX_CONTENT_LENGTH)
			{
				byte[] readData = IOUtils.toByteArray(req.getInputStream());
				System.out.println(new String(readData));
				returnStr = SUCCESS;
			}
			else
			{
				logger.error("Content-Length is bigger than 10MB ; " + req.getContentLength());
			}
		} catch (IOException e) {
			logger.error("IOError occurred", e);
		} catch (RuntimeException e) {
			logger.error("Runtime error occured", e);
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
