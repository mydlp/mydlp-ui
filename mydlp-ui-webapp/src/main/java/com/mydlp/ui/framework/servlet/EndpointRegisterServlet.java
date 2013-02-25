package com.mydlp.ui.framework.servlet;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;

import com.mydlp.ui.dao.EndpointDAO;
import com.mydlp.ui.dao.EndpointDAO.RandomExhaustedException;

@Service("registerServlet")
public class EndpointRegisterServlet implements HttpRequestHandler {

	private static Logger logger = LoggerFactory
			.getLogger(EndpointRegisterServlet.class);

	private static final Charset charset = Charset.forName("ISO-8859-1");

	private static final ByteBuffer errorResponse = charset.encode(CharBuffer.wrap("retry"));

	protected TreeMap<Date, String> latestRequests = new TreeMap<Date, String>();
	
	protected HashSet<String> bannedAddresses = new HashSet<String>();

	@Autowired
	protected EndpointDAO endpointDAO;
	
	protected Boolean isBanned(String ipAddress) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, -30);
		Date threshold = calendar.getTime();
		Entry<Date,String> lower = null;
		synchronized (latestRequests) {
			lower = latestRequests.lowerEntry(threshold);
		}
		if (lower != null) {
			while(lower != null) {
				synchronized (latestRequests) {
					latestRequests.remove(lower.getKey());
				}
				synchronized (bannedAddresses) {
					bannedAddresses.remove(lower.getValue());
				}
				synchronized (latestRequests) {
					lower = latestRequests.lowerEntry(threshold);
				}
			}
		}
		Boolean hasBan = Boolean.FALSE;
		synchronized (bannedAddresses) {
			hasBan = bannedAddresses.contains(ipAddress);
		}
		return hasBan;
	}
	
	protected void addToBanList(String ipAddress) {
		synchronized (bannedAddresses) {
			bannedAddresses.add(ipAddress);
		}
		synchronized (latestRequests) {
			latestRequests.put(new Date(), ipAddress);
		}
	}

	@Override
	public void handleRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ByteBuffer responseBuffer = null;
		String ipAddress = req.getRemoteAddr();
		try {
			if (isBanned(ipAddress))
				throw new AddressBannedException();
			String epKey = endpointDAO.registerNewEndpoint();
			responseBuffer = charset.encode(CharBuffer.wrap(epKey));
			addToBanList(ipAddress);
		} catch ( RandomExhaustedException e) {
			logger.error("Random exhausted at register for " + ipAddress, e);
		} catch (AddressBannedException e) {
			logger.error("Address " + ipAddress + " is banned for register temporarily.");
		} catch (Throwable e) {
			logger.error("Runtime error occured", e);
		}

		if (responseBuffer == null)
			responseBuffer = errorResponse;

		WritableByteChannel channel = Channels.newChannel(resp
				.getOutputStream());
		channel.write(responseBuffer);
		channel.close();
	}

	protected class AddressBannedException extends Throwable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1007072999853488771L;
		
	}
	
}
