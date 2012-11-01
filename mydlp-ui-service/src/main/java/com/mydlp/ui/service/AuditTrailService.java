package com.mydlp.ui.service;

public interface AuditTrailService {
	
	public void audit(Class<?> clazz, String method, Object[] args);
	
}