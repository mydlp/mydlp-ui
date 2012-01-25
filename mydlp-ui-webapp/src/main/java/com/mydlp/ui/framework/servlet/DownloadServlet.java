package com.mydlp.ui.framework.servlet;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;

import com.mydlp.ui.dao.IncidentLogDAO;
import com.mydlp.ui.domain.IncidentLogFile;

@Service("downloadServlet")
public class DownloadServlet implements HttpRequestHandler {

	private static Logger logger = LoggerFactory.getLogger(DownloadServlet.class);
	
	private static final int BUFFER_SIZE = 102400;

	@Autowired
	protected IncidentLogDAO incidentLogDAO;
	
	@Override
	public void handleRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String urlKey = req.getParameter("id");
		try {
			Integer logFileId = Integer.parseInt(urlKey);
			IncidentLogFile logFile = incidentLogDAO.geIncidentLogFile(logFileId);
			
			if (logFile == null)
			{
				logger.error("Null object returned from DAO.");
				return;
			}
			
			File localFile = new File(logFile.getContent().getLocalPath());
			
			resp.setContentType(logFile.getContent().getMimeType());
	        resp.setContentLength((int) localFile.length());
	        resp.setHeader( "Content-Disposition", "attachment; filename=\"" + logFile.getFilename() + "\"" );

	        //
	        //  Stream to the requester.
	        //
	        byte[] bbuf = new byte[BUFFER_SIZE];
	        DataInputStream in = new DataInputStream(new FileInputStream(localFile));
	        ServletOutputStream op = resp.getOutputStream();

	        int length = 0;
	        while ((in != null) && ((length = in.read(bbuf)) != -1))
	        {
	            op.write(bbuf,0,length);
	        }

	        in.close();
	        op.flush();
	        op.close();
			
		} catch (NumberFormatException e) {
			logger.error("Cannot format ", urlKey , e);
		} catch (FileNotFoundException e) {
			logger.error("Cannot find file", e);
		} catch (IOException e) {
			logger.error("IOError occurred", e);
		} catch (RuntimeException e) {
			logger.error("Runtime error occured", e);
		}
	}

}
