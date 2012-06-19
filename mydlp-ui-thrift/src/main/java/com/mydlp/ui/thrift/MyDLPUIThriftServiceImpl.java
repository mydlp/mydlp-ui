package com.mydlp.ui.thrift;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("thriftService")
public class MyDLPUIThriftServiceImpl implements MyDLPUIThriftService {

	private static Logger logger = LoggerFactory
			.getLogger(MyDLPUIThriftServiceImpl.class);
	
	protected static final org.apache.commons.pool.impl.GenericObjectPool.Config poolConfig = 
			new org.apache.commons.pool.impl.GenericObjectPool.Config();
	
	protected static final int CONN_RETRY_COUNT = 2;
	
	static {
		poolConfig.lifo=false;
		poolConfig.maxActive=128;
		poolConfig.maxIdle=8;
		poolConfig.whenExhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_BLOCK;
		poolConfig.maxWait=8000; // after maxWait ms NoSuchElementException will be thrown
		poolConfig.testOnBorrow = true;
		poolConfig.testOnReturn = true;
		poolConfig.timeBetweenEvictionRunsMillis = 10000;
		poolConfig.minEvictableIdleTimeMillis = 600000;
		poolConfig.testWhileIdle = true;
		poolConfig.numTestsPerEvictionRun = 2;
	}
	
	protected GenericObjectPool<MyDLPUIThriftConnection> mydlpUIConnectionPool = null;
	
	@PostConstruct
	protected synchronized void init() {
		mydlpUIConnectionPool = new GenericObjectPool<MyDLPUIThriftConnection>(new MyDLPUIThriftConnectionFactory(), poolConfig);
	}
	
	@PreDestroy
	protected synchronized void destroy() {
		try {
			mydlpUIConnectionPool.close();
		} catch (Exception e) {
			logger.error("Error occured when closing pool", e);
		}
	}
	
	protected interface ThriftCall<T> {
		public T execute(MyDLPUIThriftConnection thriftConnection) throws TException;
	}
	
	protected <T> T call(ThriftCall<T> thriftCall) {
		MyDLPUIThriftConnection conn = null;
		T result = null;
		try {
			int retryCount = CONN_RETRY_COUNT;
			while (true) {
				retryCount--;
				try {
					conn = mydlpUIConnectionPool.borrowObject();
					result = thriftCall.execute(conn);
					break;
				} catch (Throwable e) {
					if (retryCount < CONN_RETRY_COUNT - 1)
						logger.error("Error suppressed " + (CONN_RETRY_COUNT - retryCount) + " times.");
					if (retryCount == 0) {
						logger.error("Retry count had hitted zero. Not supressing exception.");
						throw e;
					}
				} finally {
					try {
						try {
							if (conn != null) conn.destroy();
						} catch (Throwable e) {
							logger.error("Error occured when destroying pool object", e);
						}
						if (conn != null) mydlpUIConnectionPool.returnObject(conn);
					} catch (Throwable e) {
						logger.error("Error occured when return object to pool", e);
					}
				}
			}
			
		} catch (NullPointerException e) {
			logger.error("Can not establish thrift service connection.");
		} catch (TApplicationException e) {
			logger.error("Thrift execution error. Type code: " + e.getType(), e);
		} catch (TException e) {
			logger.error("Thrift execution error", e);
		} catch (NoSuchElementException e) {
			logger.error("Pool has been exhausted", e);
		} catch (Exception e) {
			logger.error("Error occured when calling thrift", e);
		}
		return result;
	}

	@Override
	public void compileFilter(final Integer filterId) {
		call(new ThriftCall<Void>() {
			@Override
			public Void execute(MyDLPUIThriftConnection thriftConnection) throws TException {
				thriftConnection.client.compileCustomer(filterId);
				return null;
			}
		});
	}

	@Override
	public ByteBuffer getRuletable(final String ipAddress, final String userH, final String revisionId) {
		return call(new ThriftCall<ByteBuffer>() {
			@Override
			public ByteBuffer execute(MyDLPUIThriftConnection thriftConnection) throws TException {
				return thriftConnection.client.getRuletable(ipAddress, userH, revisionId);
			}
		});
	}

	@Override
	public String receiveBegin(final String ipAddress) {
		return call(new ThriftCall<String>() {
			@Override
			public String execute(MyDLPUIThriftConnection thriftConnection) throws TException {
				return thriftConnection.client.receiveBegin(ipAddress);
			}
		});
	}

	@Override
	public String receiveChunk(final String ipAddress, final long itemId,
			final ByteBuffer chunkData, final int chunkNum, final int chunkNumTotal) {
		return call(new ThriftCall<String>() {
			@Override
			public String execute(MyDLPUIThriftConnection thriftConnection) throws TException {
				return thriftConnection.client.receiveChunk(ipAddress, itemId, chunkData, chunkNum, chunkNumTotal);
			}
		});
	}

	@Override
	public List<Long> getFingerprints(final String filename, final ByteBuffer data) {
		return call(new ThriftCall<List<Long>>() {
			@Override
			public List<Long> execute(MyDLPUIThriftConnection thriftConnection) throws TException {
				return thriftConnection.client.getFingerprints(filename, data);
			}
		});
	}

	@Override
	public void requeueIncident(final Integer incidentId) {
		call(new ThriftCall<Void>() {
			@Override
			public Void execute(MyDLPUIThriftConnection thriftConnection) throws TException {
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
			public String execute(MyDLPUIThriftConnection thriftConnection) throws TException {
				return thriftConnection.client.registerUserAddress(ipAddress, userH, payload);
			}
		});
	}

	@Override
	public void saveLicenseKey(final String licenseKey) {
		call(new ThriftCall<Void>() {
			@Override
			public Void execute(MyDLPUIThriftConnection thriftConnection) throws TException {
				thriftConnection.client.saveLicenseKey(licenseKey);
				return null;
			}
		});
	}

	@Override
	public LicenseObject getLicense() {
		return call(new ThriftCall<LicenseObject>() {
			@Override
			public LicenseObject execute(MyDLPUIThriftConnection thriftConnection) throws TException {
				return thriftConnection.client.getLicense();
			}
		});
	}

}
