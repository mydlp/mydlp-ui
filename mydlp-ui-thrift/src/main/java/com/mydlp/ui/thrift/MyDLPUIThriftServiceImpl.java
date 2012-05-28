package com.mydlp.ui.thrift;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

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
	
	protected static final String THRIFT_HOST = "127.0.0.1";
	protected static final int THRIFT_PORT = 9092;
	protected static final int MAX_POOL_SIZE = 128;
	protected static final int POOL_SIZE = 16;

	protected class Connection {
		protected Mydlp_ui.Client client;
		protected TTransport transport;
		
		public void init() {
			if (transport != null)
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
			if (transport != null && ! transport.isOpen())
				transport.close();
			transport = null;
			client = null;
		}
		
		public void ensureOpen() {
			if (transport == null || !transport.isOpen())
				init();
		}
		
	}
	
	private final ConcurrentLinkedQueue<Connection> freeInstances = new ConcurrentLinkedQueue<Connection>();
	private final ConcurrentLinkedQueue<Connection> busyInstances = new ConcurrentLinkedQueue<Connection>();
   

	@PostConstruct
	protected synchronized void init() {
		for (int i = 0; i < POOL_SIZE; i++) {
			newConnection();
		}
	}
	
	@PreDestroy
	protected synchronized void destroy() {
	}
	
	protected void newConnection() {
		if (freeInstances.size() + busyInstances.size() < MAX_POOL_SIZE) {
			Connection conn = new Connection();
			freeInstances.add(conn);
		}
	}
	
	protected Connection takeOverConnection() {
		Connection conn = null;
		while (conn == null) {
			synchronized (freeInstances) {
				if (freeInstances.size() == 0) {
					newConnection();
					logger.info("All " + busyInstances.size() + " connections are busy. Spawning new.");
				}
				conn = freeInstances.poll();
			}
			if (conn != null) {
				synchronized (busyInstances) {
					busyInstances.add(conn);
				}
			} else {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					logger.error("sleep interrupted",e);
				}
			}
		}
		conn.ensureOpen();
		return conn;
	}
	
	protected void releaseConnection(Connection conn) {
		busyInstances.remove(conn);
		synchronized (freeInstances) {
			freeInstances.add(conn);
			int gap = freeInstances.size() - POOL_SIZE;
			if (gap > 0) {
				logger.info("There are " + gap + " extra connections waiting redundantly. Destroying...");
				for (int i = 0; i < gap; i++) {
					Connection c = freeInstances.poll();
					c.destroy();
				}
			}
		}
	}
	
	protected interface ThriftCall<T> {
		public T execute(Connection thriftConnection) throws TException;
	}
	
	protected <T> T call(ThriftCall<T> thriftCall) {
		Connection conn = null;
		T result = null;
		try {
			conn = takeOverConnection();
			result = thriftCall.execute(conn);
		} catch (NullPointerException e) {
			logger.error("Can not establish thrift service connection.");
		} catch (TException e) {
			logger.error("Thrift execution error", e);
			conn.destroy();
		} finally {
			releaseConnection(conn);
		}
		return result;
	}

	@Override
	public void compileFilter(final Integer filterId) {
		call(new ThriftCall<Void>() {
			@Override
			public Void execute(Connection thriftConnection) throws TException {
				thriftConnection.client.compileCustomer(filterId);
				return null;
			}
		});
	}

	@Override
	public ByteBuffer getRuletable(final String ipAddress, final String userH, final String revisionId) {
		return call(new ThriftCall<ByteBuffer>() {
			@Override
			public ByteBuffer execute(Connection thriftConnection) throws TException {
				return thriftConnection.client.getRuletable(ipAddress, userH, revisionId);
			}
		});
	}

	@Override
	public String receiveBegin(final String ipAddress) {
		return call(new ThriftCall<String>() {
			@Override
			public String execute(Connection thriftConnection) throws TException {
				return thriftConnection.client.receiveBegin(ipAddress);
			}
		});
	}

	@Override
	public String receiveChunk(final String ipAddress, final long itemId,
			final ByteBuffer chunkData, final int chunkNum, final int chunkNumTotal) {
		return call(new ThriftCall<String>() {
			@Override
			public String execute(Connection thriftConnection) throws TException {
				return thriftConnection.client.receiveChunk(ipAddress, itemId, chunkData, chunkNum, chunkNumTotal);
			}
		});
	}

	@Override
	public List<Long> getFingerprints(final String filename, final ByteBuffer data) {
		return call(new ThriftCall<List<Long>>() {
			@Override
			public List<Long> execute(Connection thriftConnection) throws TException {
				return thriftConnection.client.getFingerprints(filename, data);
			}
		});
	}

	@Override
	public void requeueIncident(final Integer incidentId) {
		call(new ThriftCall<Void>() {
			@Override
			public Void execute(Connection thriftConnection) throws TException {
				thriftConnection.client.requeueIncident(incidentId);
				return null;
			}
		});
	}

	@Override
	public String registerUserAddress(final String ipAddress, final String userH,
			final ByteBuffer payload) {
		return call(new ThriftCall<String>() {
			@Override
			public String execute(Connection thriftConnection) throws TException {
				return thriftConnection.client.registerUserAddress(ipAddress, userH, payload);
			}
		});
	}

}
