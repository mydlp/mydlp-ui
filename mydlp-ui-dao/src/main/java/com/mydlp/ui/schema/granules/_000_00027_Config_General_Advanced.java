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
		
		Config deniedPage = new Config();	
		deniedPage.setKey("denied_page_html");
		deniedPage.setValue(
				"<!DOCTYPE HTML>\r\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\" dir=\"ltr\" lang=\"en-US\">\r\n" + 
				"<head>\r\n" + 
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n" + 
				"<title>Access Denied by MyDLP</title>\r\n" + 
				"<style type=\"text/css\">\r\n" + 
				"body\r\n" + 
				"{\r\n" + 
				"	font-family: verdana,arial, 'Trebuchet MS';\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".main-frame\r\n" + 
				"{\r\n" + 
				"	position: relative;\r\n" + 
				"	margin-right: auto;\r\n" + 
				"	margin-left: auto;\r\n" + 
				"	margin-top: 100px;\r\n" + 
				"	padding-top: 10px;\r\n" + 
				"	padding-bottom: 10px;\r\n" + 
				"	width: 600px;\r\n" + 
				"	height: 300px;\r\n" + 
				"	border:1px solid black;\r\n" + 
				"	background-color: #ffffff; \r\n" + 
				"	-moz-border-radius: 8px;\r\n" + 
				"	-webkit-border-radius: 8px;\r\n" + 
				"	border-radius: 8px;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".img-td\r\n" + 
				"{\r\n" + 
				"	margin-left:auto;\r\n" + 
				"	margin-right:auto;\r\n" + 
				"	margin-top:auto;\r\n" + 
				"	margin-bottom:auto;\r\n" + 
				"	text-align: center;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".img-td img\r\n" + 
				"{\r\n" + 
				"	margin-top: 10px;\r\n" + 
				"	margin-bottom: 10px;\r\n" + 
				"	margin-left:auto;\r\n" + 
				"	margin-right:auto;\r\n" + 
				"	border:0px;\r\n" + 
				"} \r\n" + 
				"\r\n" + 
				".reason-td {\r\n" + 
				"	font-size: 0.85em;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".header-td {\r\n" + 
				"	vertical-align: bottom;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"h3 {\r\n" + 
				"	color:#992212;\r\n" + 
				"	font-size: 1.5em;\r\n" + 
				"	vertical-align: bottom;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"a {\r\n" + 
				"	text-decoration: none;\r\n" + 
				"}\r\n" + 
				"</style>\r\n" + 
				"</head>\r\n" + 
				"\r\n" + 
				"<body bgcolor=\"#dfdfdf\">\r\n" + 
				"<center>\r\n" + 
				"<div class=\"main-frame\">\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"95%\">\r\n" + 
				"	<tr>\r\n" + 
				"		<td class=\"img-td\"><a href=\"http://www.mydlp.com\">\r\n" + 
				"			<img src=\"http://www.mydlp.com/wp-content/uploads/mydlp_tiny.png\" alt=\"MyDLP Logo\">\r\n" + 
				"		</a></td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"	<tr>\r\n" + 
				"		<td><hr /></td>\r\n" + 
				"	</tr>\r\n" + 
				"	<tr>\r\n" + 
				"		<td class=\"reason-td\">\r\n" + 
				"			<br />Your request has been <u>blocked</u> by <a href=\"http://www.mydlp.com\">MyDLP</a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"	<tr>\r\n" + 
				"		<td class=\"reason-td\">\r\n" + 
				"			<br />This action has been taken according your corporate information security policies. Please contact your system administration if you have any questions.\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"</table>\r\n" + 
				"</div>\r\n" + 
				"</center>\r\n" + 
				"</body>\r\n" + 
				"\r\n" + 
				"</html>\r\n" + 
				"");

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
		getHibernateTemplate().saveOrUpdate(deniedPage);	

	}

}
