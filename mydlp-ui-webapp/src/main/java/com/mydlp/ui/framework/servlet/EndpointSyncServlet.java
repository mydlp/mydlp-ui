package com.mydlp.ui.framework.servlet;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;

import com.mydlp.ui.dao.EndpointStatusDAO;
import com.mydlp.ui.framework.util.NIOUtil;
import com.mydlp.ui.service.EndpointSyncService;
import com.mydlp.ui.service.PayloadProcessService;
import com.mydlp.ui.service.PayloadProcessService.ImproperPayloadEncapsulationException;
import com.mydlp.ui.service.PayloadProcessService.SyncObject;
import com.mydlp.ui.thrift.MyDLPUIThriftService;

@Service("syncServlet")
public class EndpointSyncServlet implements HttpRequestHandler {

	private static Logger logger = LoggerFactory.getLogger(EndpointSyncServlet.class);
	private static Logger errorLogger = LoggerFactory.getLogger("IERROR");
	
	protected static final int MAX_CONTENT_LENGTH = 10*1024*1024;

	@Autowired
	protected MyDLPUIThriftService thriftService;
	
	@Autowired
	protected EndpointStatusDAO endpointStatusDAO;
	
	@Autowired
	protected EndpointSyncService endpointSyncService;
	
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
				
				String ruleTableUniqId = req.getParameter("rid");
				String userH = req.getParameter("uh");
				String ipAddress = req.getRemoteAddr();
				
				try {
					Map<String,String> endpointMeta = 
							thriftService.registerUserAddress(
									syncObject.getEndpointId(), ipAddress, userH, 
									syncObject.getPayload());
					endpointSyncService.asyncRegisterEndpointMeta(endpointMeta,
							syncObject.getEndpointId(), ipAddress, userH, syncObject.getPayload());
				} catch (Throwable e) {
					logger.error("Runtime error occured when reading request payload", e);
				}
				ByteBuffer thriftResponse = thriftService.getRuletable(
						syncObject.getEndpointId(),	ruleTableUniqId);
				if (thriftResponse != null) {
					syncObject.setPayload(thriftResponse);
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
			errorLogger.error("Improper sync request received from address: " + req.getRemoteAddr() + " . Sending invalid response.");
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


	protected ByteBuffer getErrorResponse() {
		return Charset.forName("ISO-8859-1").encode(CharBuffer.wrap("error"));
	}
	
	protected ByteBuffer getInvalidResponse() {
		return Charset.forName("ISO-8859-1").encode(CharBuffer.wrap("invalid"));
	}
	 
}
