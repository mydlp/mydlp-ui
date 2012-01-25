package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.Config;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00027_Config_General_Advanced extends AbstractGranule {

	@Override
	protected void callback() {

		Config mailArchive = new Config();
		mailArchive.setKey("mail_archive");
		mailArchive.setValue("false");

		Config archiveInbound = new Config();
		archiveInbound.setKey("archive_inbound");
		archiveInbound.setValue("false");

		Config archiveMinimumSize = new Config();
		archiveMinimumSize.setKey("archive_minimum_size");
		archiveMinimumSize.setValue("256");


		Config maximumObjectSize = new Config();
		maximumObjectSize.setKey("maximum_object_size");
		maximumObjectSize.setValue("10485760");

		Config maximumMemoryObject	 = new Config();
		maximumMemoryObject.setKey("maximum_memory_object");
		maximumMemoryObject.setValue("204800");

		Config maximumChunkSize = new Config();	
		maximumChunkSize.setKey("maximum_chunk_size");
		maximumChunkSize.setValue("1048576");

		Config supervisorMaxRestartCount = new Config();	
		supervisorMaxRestartCount.setKey("supervisor_max_restart_count");
		supervisorMaxRestartCount.setValue("5");

		Config supervisorMaxRestartTime	 = new Config();	
		supervisorMaxRestartTime.setKey("supervisor_max_restart_time");
		supervisorMaxRestartTime.setValue("20");

		Config supervisorKillTimeout = new Config();	
		supervisorKillTimeout.setKey("supervisor_kill_timeout");
		supervisorKillTimeout.setValue("20");

		Config fsmTimeout = new Config();	
		fsmTimeout.setKey("fsm_timeout");
		fsmTimeout.setValue("120000");

		Config spawnTimeout = new Config();	
		spawnTimeout.setKey("spawn_timeout");
		spawnTimeout.setValue("60000");

		Config queryCacheCleaunpInterval = new Config();	
		queryCacheCleaunpInterval.setKey("query_cache_cleanup_interval");
		queryCacheCleaunpInterval.setValue("900000");

		Config queryCacheMaximumSize = new Config();	
		queryCacheMaximumSize.setKey("query_cache_maximum_size");
		queryCacheMaximumSize.setValue("2000000");			

		getHibernateTemplate().saveOrUpdate(mailArchive);
		getHibernateTemplate().saveOrUpdate(archiveMinimumSize);
		getHibernateTemplate().saveOrUpdate(archiveInbound);
		getHibernateTemplate().saveOrUpdate(maximumObjectSize);
		getHibernateTemplate().saveOrUpdate(maximumMemoryObject);
		getHibernateTemplate().saveOrUpdate(maximumChunkSize);
		getHibernateTemplate().saveOrUpdate(supervisorMaxRestartCount);
		getHibernateTemplate().saveOrUpdate(supervisorMaxRestartTime);
		getHibernateTemplate().saveOrUpdate(supervisorKillTimeout);
		getHibernateTemplate().saveOrUpdate(fsmTimeout);
		getHibernateTemplate().saveOrUpdate(spawnTimeout);
		getHibernateTemplate().saveOrUpdate(queryCacheCleaunpInterval);
		getHibernateTemplate().saveOrUpdate(queryCacheMaximumSize);		

	}

}
