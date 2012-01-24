package com.mydlp.ui.thrift;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("thriftService")
public class MyDLPUIThriftServiceImpl implements MyDLPUIThriftService {

	private static Logger logger = LoggerFactory
			.getLogger(MyDLPUIThriftServiceImpl.class);

	protected Mydlp_ui.Client client;

	protected TTransport transport;

	@PostConstruct
	protected synchronized void init() {
		if (transport != null)
		{
			destroy();
		}
		try {
			transport = new TSocket("localhost", 9092);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			client = new Mydlp_ui.Client(protocol);
		} catch (TException e) {
			logger.error("Thrift init", e);
		}

	}
	
	@PreDestroy
	protected synchronized void destroy() {
		if (transport != null && ! transport.isOpen())
			transport.close();
		transport = null;
		client = null;
	}
	
	protected synchronized void ensureOpen() {
		if (transport == null || !transport.isOpen())
			init();
	}

	@Override
	public void compileFilter(Integer filterId) {
		try {
			ensureOpen();
			client.compileCustomer(filterId);
		} catch (TException e) {
			logger.error("Thrift compile filter", e);
		}
	}

}
