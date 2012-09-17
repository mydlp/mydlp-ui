package com.mydlp.ui.framework.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;

import com.mydlp.ui.service.LogExportService;

@Service("exportServlet")
public class ExportServlet implements HttpRequestHandler {

	private static Logger logger = LoggerFactory.getLogger(ExportServlet.class);
	
	@Autowired
	protected LogExportService logExportService;
	
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd");
	
	@Override
	public void handleRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String urlId = req.getParameter("id");
		try {
			if (urlId == null)
			{
				logger.error("Null urlId urlId: ", urlId);
				return;
			}
			byte[] exportContent = logExportService.getExport(urlId);
			
			if (exportContent == null || exportContent.length == 0)
			{
				logger.error("Null export content return from LogExportService. urlId: ", urlId);
				return;
			}
			
			
			resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	        resp.setContentLength((int) exportContent.length);
	        resp.setHeader( "Content-Disposition", "attachment; filename=\"mydlp_incidents_" + 
	        		simpleDateFormat.format(new Date()) + ".xlsx\"" );

	        ServletOutputStream op = resp.getOutputStream();

	        op.write(exportContent);
	        op.flush();
	        op.close();
			
		} catch (NumberFormatException e) {
			logger.error("Cannot format ", urlId , e);
		} catch (IOException e) {
			logger.error("IOError occurred", e);
		} catch (RuntimeException e) {
			logger.error("Runtime error occured", e);
		}
	}

}
