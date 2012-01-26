package com.mydlp.ui.framework.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;

import com.mydlp.ui.thrift.MyDLPUIThriftService;

@Service("syncServlet")
public class EndpointSyncServlet implements HttpRequestHandler {

	private static Logger logger = LoggerFactory.getLogger(EndpointSyncServlet.class);
	
	private static final String UP_TO_DATE = "up-to-date";

	@Autowired
	protected MyDLPUIThriftService thriftService;
	
	@Override
	public void handleRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String urlKey = req.getParameter("rid");
			String ipAddress = req.getRemoteAddr();
			ByteBuffer serializedRuleTable = thriftService.getRuletable(ipAddress, urlKey);
			WritableByteChannel channel = Channels.newChannel(resp.getOutputStream());
			resp.setContentType("application/octet-stream");
	        channel.write(serializedRuleTable);
	        channel.close();
	        return;
		} catch (IOException e) {
			logger.error("IOError occurred", e);
		} catch (RuntimeException e) {
			logger.error("Runtime error occured", e);
		}
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/octet-stream");
		out.print(UP_TO_DATE);
		out.flush();
		out.close();
	}

}
