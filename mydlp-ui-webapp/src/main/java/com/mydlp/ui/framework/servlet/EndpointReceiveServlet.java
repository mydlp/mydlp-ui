package com.mydlp.ui.framework.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;

import com.mydlp.ui.thrift.MyDLPUIThriftService;

@Service("receiveServlet")
public class EndpointReceiveServlet implements HttpRequestHandler {

	private static Logger logger = LoggerFactory
			.getLogger(EndpointReceiveServlet.class);

	private static final String ERROR = "error";
	public static final int READ_BLOCK = 8192;

	@Autowired
	protected MyDLPUIThriftService thriftService;

	@Override
	public void handleRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String returnStr = ERROR;
		try {
			String operation = req.getParameter("o");
			String ipAddress = req.getRemoteAddr();
			if (operation.equals("begin")) {
				returnStr = thriftService.receiveBegin(ipAddress);
			} else if (operation.equals("push")) {
				Long itemId = Long.parseLong(req.getParameter("i"));
				Integer chunkNum = Integer.parseInt(req.getParameter("c"));
				Integer chunkNumTotal = Integer.parseInt(req.getParameter("t"));
				ByteBuffer chunkData = ByteBuffer.allocate(READ_BLOCK);
				ReadableByteChannel channel = Channels.newChannel(req.getInputStream());
				
				while (channel.read(chunkData) != -1)  
					chunkData = resizeBuffer(chunkData);
				chunkData.position(0);
				channel.close();
				returnStr = thriftService.receiveChunk(ipAddress, itemId, chunkData, chunkNum, chunkNumTotal);
			}
		} catch (IOException e) {
			logger.error("IOError occurred", e);
		} catch (RuntimeException e) {
			logger.error("Runtime error occured", e);
		}
		PrintWriter out = resp.getWriter();
		out.print(returnStr);
		out.flush();
		out.close();
	}

	public static ByteBuffer resizeBuffer(ByteBuffer in) {
		ByteBuffer result = in;
		if (in.remaining() < READ_BLOCK) {
			// create new buffer
			result = ByteBuffer.allocate(in.capacity() * 2);
			// set limit to current position in buffer and set position to zero.
			in.flip();
			// put original buffer to new buffer
			result.put(in);
		}

		return result;
	}

}
