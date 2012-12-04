package com.mydlp.ui.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.TemporaryAccessTokenDAO;

@Service("tokenCleanupService")
public class TokenCleanupServiceImpl implements TokenCleanupService {

	
	@Autowired
	protected TemporaryAccessTokenDAO temporaryAccessTokenDAO;
	
	@Scheduled(cron="0 0/15 * * * ?")
	public void dailySchedule() {
		cleanup();
	}
	
	@PostConstruct
	public void init() {
		cleanup();
	}

	@Override
	public void cleanup() {
		temporaryAccessTokenDAO.cleanupExpiredTokens();
		temporaryAccessTokenDAO.cleanupIdleTokens();
	}
	
	
}
