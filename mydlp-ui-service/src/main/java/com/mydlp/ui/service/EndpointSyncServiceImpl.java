package com.mydlp.ui.service;

import java.nio.ByteBuffer;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.mydlp.ui.dao.EndpointDAO;
import com.mydlp.ui.dao.EndpointStatusDAO;
import com.mydlp.ui.thrift.MyDLPUIThriftService;

@Service("endpointSyncService")
public class EndpointSyncServiceImpl implements EndpointSyncService {

	private static Logger logger = LoggerFactory.getLogger(EndpointSyncServiceImpl.class);

	@Autowired
	protected EndpointStatusDAO endpointStatusDAO;
	
	@Autowired
	protected EndpointDAO endpointDAO;

	@Autowired
	protected MyDLPUIThriftService thriftService;
	
	@Autowired
	@Qualifier("policyTransactionTemplate")
	protected TransactionTemplate transactionTemplate;
	
	@Async
	@Override
	public void asyncRegisterEndpointMeta(final Map<String,String> endpointMeta,
			final String endpointId, final String ipAddress,
			final String usernameHash, final ByteBuffer payload) {
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				asyncRegisterEndpointMetaFun(endpointMeta, endpointId, ipAddress, usernameHash, payload);
			}
		});
	}
	
	public void asyncRegisterEndpointMetaFun(Map<String,String> endpointMeta, String endpointId, String ipAddress,
			String usernameHash, ByteBuffer payload) {
		try {
			final String endpointAlias = endpointDAO.getEndpointAlias(endpointId);
			if (endpointAlias == null) return;
			
			String endpointUsername = endpointMeta.get("user");
			String endpointVersion = endpointMeta.get("version");
			String osName = endpointMeta.get("os");
			String hostname = endpointMeta.get("hostname");
			String discoverInProgS = endpointMeta.get("discover_inprog");
			Boolean discoverInProg = Boolean.FALSE;
			if (discoverInProgS != null && discoverInProgS.equals("yes"))
			{
				discoverInProg = Boolean.TRUE;
			}
			endpointStatusDAO.upToDateEndpoint(endpointAlias, ipAddress, hostname, endpointUsername, osName, endpointVersion, discoverInProg);
		} catch (Throwable e) {
			logger.error("Runtime error occured when registering endpoint meta", e);
		}
		
	}
	
}
