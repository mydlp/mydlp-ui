package com.mydlp.ui.service;


public interface DashboardItemService {

	public Object get(String itemKey);
	
	public void registerAllJob();
	
	public void consumeJob();
	
	public void registerForGeneration(String itemKey);
	
}
