package com.mydlp.ui.framework.servlet;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;

import com.mydlp.ui.dao.TemporaryAccessTokenDAO;
import com.mydlp.ui.domain.TemporaryAccessToken;
import com.mydlp.ui.framework.util.NIOUtil;
import com.mydlp.ui.service.PayloadProcessService;
import com.mydlp.ui.service.PayloadProcessService.ImproperPayloadEncapsulationException;
import com.mydlp.ui.service.PayloadProcessService.SyncObject;

@Service("tokenServlet")
public class EndpointTokenServlet implements HttpRequestHandler {

	private static Logger logger = LoggerFactory.getLogger(EndpointTokenServlet.class);
	private static Logger errorLogger = LoggerFactory.getLogger("IERROR");
	
	protected static final int MAX_CONTENT_LENGTH = 10*1024*1024;

	@Autowired
	protected TemporaryAccessTokenDAO temporaryAccessTokenDAO;
	
	@Autowired 
	protected PayloadProcessService payloadProcessService;
	
	@Override
	public void handleRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ByteBuffer responseBuffer = null;
		try {
			if (req.getContentLength() < MAX_CONTENT_LENGTH)
			{
				ByteBuffer chunk = NIOUtil.toByteBuffer(req.getInputStream());
				SyncObject syncObject = payloadProcessService.toSyncObject(chunk);
				String ipAddress = req.getRemoteAddr();
				
				String queryType = req.getParameter("q");
				
				if (queryType.equals("new")) {
					String tokenStr = temporaryAccessTokenDAO.generateTokenKey(ipAddress, syncObject.getEndpointId(), "iecp", "print");
					syncObject.setPayload(getResponse("TOKEN: " + tokenStr));
					responseBuffer = payloadProcessService.toByteBuffer(syncObject);
				} else if (queryType.equals("is_valid")) {
					String tokenStr = Charset.forName("ISO-8859-1").decode(syncObject.getPayload()).toString();
					TemporaryAccessToken tokenObj = temporaryAccessTokenDAO.getTokenObj(tokenStr);
					if (tokenObj == null)
					{
						syncObject.setPayload(getResponse("false"));
					}
					else
					{
						syncObject.setPayload(getResponse("true"));
						temporaryAccessTokenDAO.revokateToken(tokenStr);
					}
					responseBuffer = payloadProcessService.toByteBuffer(syncObject);
				}
			}
			else 
			{
				errorLogger.error("Content-Length is bigger than 10MB ; " + req.getContentLength());
			}
		}
		catch (ImproperPayloadEncapsulationException e)
		{
			logger.error("Improper payload.",e);
			errorLogger.error("Improper token request received from address: " + req.getRemoteAddr() + " . Sending invalid endpoint response.");
			responseBuffer = getInvalidResponse();
		}
		catch (Throwable e) {
			logger.error("Runtime error occured", e);
		}
		
		if (responseBuffer == null)
			responseBuffer = getErrorResponse();
		
		WritableByteChannel channel = Channels.newChannel(resp.getOutputStream());
        channel.write(responseBuffer);
        channel.close();
	}

	protected ByteBuffer getResponse(String str) {
		return Charset.forName("ISO-8859-1").encode(CharBuffer.wrap(str));
	}

	protected ByteBuffer getErrorResponse() {
		return Charset.forName("ISO-8859-1").encode(CharBuffer.wrap("error"));
	}
	
	protected ByteBuffer getInvalidResponse() {
		return Charset.forName("ISO-8859-1").encode(CharBuffer.wrap("invalid"));
	}
	 
}
