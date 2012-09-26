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

import com.mydlp.ui.dao.EndpointStatusDAO;
import com.mydlp.ui.thrift.MyDLPUIThriftService;

@Service("endpointSyncService")
public class EndpointSyncServiceImpl implements EndpointSyncService {

	private static Logger logger = LoggerFactory.getLogger(EndpointSyncServiceImpl.class);

	@Autowired
	protected EndpointStatusDAO endpointStatusDAO;

	@Autowired
	protected MyDLPUIThriftService thriftService;
	
	@Autowired
	@Qualifier("policyTransactionTemplate")
	protected TransactionTemplate transactionTemplate;
	
	@Async
	@Override
	public void asyncRegisterEndpointMeta(final String ipAddress,
			final String usernameHash, final ByteBuffer payload) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				asyncRegisterEndpointMetaFun(ipAddress, usernameHash, payload);
			}
		});
	}
	
	public void asyncRegisterEndpointMetaFun(String ipAddress,
			String usernameHash, ByteBuffer payload) {
		try {
			Map<String,String> endpointMeta = thriftService.registerUserAddress(ipAddress, usernameHash, payload);
			
			String endpointUsername = endpointMeta.get("user");
			String endpointVersion = endpointMeta.get("version");
			
			endpointStatusDAO.upToDateEndpoint(ipAddress, endpointVersion, endpointUsername);
		} catch (Throwable e) {
			logger.error("Runtime error occured when registering endpoint meta", e);
		}
		
	}
	
}
