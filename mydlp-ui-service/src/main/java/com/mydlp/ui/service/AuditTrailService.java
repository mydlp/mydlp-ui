package com.mydlp.ui.service;

public interface AuditTrailService {
	
	public void audit(Class<?> clazz, String method, Object[] args);
	
	public void audit(Class<?> clazz, String user, String method, Object[] args);
	
}