package com.mydlp.ui.service;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

@Service("enumMasterService")
public class EnumMasterServiceImpl implements EnumMasterService {
	
	private static Logger logger = LoggerFactory.getLogger(EnumMasterServiceImpl.class);
	
	@Autowired
	protected TaskScheduler taskScheduler;
	
	protected ConcurrentLinkedQueue<EnumJob> masterQueue = new ConcurrentLinkedQueue<EnumJob>();
	
	protected Boolean isRunning = false;
	
	@Override
	public void schedule(final EnumJob enumJob) {
		synchronized (masterQueue) {
			if (masterQueue.contains(enumJob)) return;
			masterQueue.add(enumJob);
		}
		
		Runnable task = new Runnable() {
			@Override
			public void run() {
				consume();
			}
		};
		
		taskScheduler.schedule(task, new Date());
	}
	
	public void consume() {
		try {
			synchronized (masterQueue) {
				if (masterQueue.isEmpty()) return;
			}
			synchronized (isRunning) {
				if (isRunning) return;
				isRunning = true;
			}
			
			EnumJob enumJob = null;
			synchronized (masterQueue) {
				enumJob = masterQueue.poll();
			}
			
			if (enumJob != null) {
				try {
					enumJob.enumerateNow();
				} catch (Throwable e) {
					logger.error("An error occured when trying to enumerate", e);
				} 
			}
			
			synchronized (isRunning) {
				isRunning = false;
			}
		} catch (Throwable e) {
			logger.error("An error occured when trying to schedule enumeration", e);
		}
	}
	

}
