package com.mydlp.ui.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyDLPUIThriftConnection {
	
	private static Logger logger = LoggerFactory
			.getLogger(MyDLPUIThriftConnection.class);
	
	protected static final String THRIFT_HOST = "127.0.0.1";
	protected static final int THRIFT_PORT = 9092;
	
	protected Mydlp_ui.Client client;
	protected TTransport transport;
	
	public void init() {
		if (!isOpen())
		{
			destroy();
		}
		try {
			transport = new TSocket(THRIFT_HOST, THRIFT_PORT);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			client = new Mydlp_ui.Client(protocol);
		} catch (TException e) {
			logger.error("Thrift init", e);
			destroy();
		}
	}
	
	public void destroy() {
		if (isOpen())
			transport.close();
		transport = null;
		client = null;
	}
	
	public void ensureOpen() {
		if (!isOpen()) init();
	}
	
	public Boolean isOpen() {
		return transport != null && transport.isOpen();
	}

}
