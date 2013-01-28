package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.Config;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00027_Config_General_Advanced extends AbstractGranule {

	@Override
	protected void callback() {

		Config mailArchive = new Config();
		mailArchive.setKey("mail_archive");
		mailArchive.setValue("false");

		Config archiveMinimumSize = new Config();
		archiveMinimumSize.setKey("archive_minimum_size");
		archiveMinimumSize.setValue("2048");


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
				"<!DOCTYPE HTML>\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\" dir=\"ltr\" lang=\"en-US\">\n" + 
				"<head>\n" + 
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" + 
				"<title>Access Denied by MyDLP</title>\n" + 
				"<style type=\"text/css\">\n" + 
				"body\n" + 
				"{\n" + 
				"	font-family: verdana,arial, 'Trebuchet MS';\n" + 
				"}\n" + 
				"\n" + 
				".main-frame\n" + 
				"{\n" + 
				"	position: relative;\n" + 
				"	margin-right: auto;\n" + 
				"	margin-left: auto;\n" + 
				"	margin-top: 100px;\n" + 
				"	padding-top: 10px;\n" + 
				"	padding-bottom: 10px;\n" + 
				"	width: 600px;\n" + 
				"	height: 300px;\n" + 
				"	border:1px solid black;\n" + 
				"	background-color: #ffffff; \n" + 
				"	-moz-border-radius: 8px;\n" + 
				"	-webkit-border-radius: 8px;\n" + 
				"	border-radius: 8px;\n" + 
				"}\n" + 
				"\n" + 
				".img-td\n" + 
				"{\n" + 
				"	margin-left:auto;\n" + 
				"	margin-right:auto;\n" + 
				"	margin-top:auto;\n" + 
				"	margin-bottom:auto;\n" + 
				"	text-align: center;\n" + 
				"}\n" + 
				"\n" + 
				".img-td img\n" + 
				"{\n" + 
				"	margin-top: 10px;\n" + 
				"	margin-bottom: 10px;\n" + 
				"	margin-left:auto;\n" + 
				"	margin-right:auto;\n" + 
				"	border:0px;\n" + 
				"} \n" + 
				"\n" + 
				".reason-td {\n" + 
				"	font-size: 0.85em;\n" + 
				"}\n" + 
				"\n" + 
				".header-td {\n" + 
				"	vertical-align: bottom;\n" + 
				"}\n" + 
				"\n" + 
				"h3 {\n" + 
				"	color:#992212;\n" + 
				"	font-size: 1.5em;\n" + 
				"	vertical-align: bottom;\n" + 
				"}\n" + 
				"\n" + 
				"a {\n" + 
				"	text-decoration: none;\n" + 
				"}\n" + 
				"</style>\n" + 
				"</head>\n" + 
				"\n" + 
				"<body bgcolor=\"#dfdfdf\">\n" + 
				"<center>\n" + 
				"<div class=\"main-frame\">\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"95%\">\n" + 
				"	<tr>\n" + 
				"		<td class=\"img-td\"><a href=\"http://www.mydlp.com\">\n" + 
				"			<img src=\"http://www.mydlp.com/wp-content/uploads/mydlp_tiny.png\" alt=\"MyDLP Logo\">\n" + 
				"		</a></td>\n" + 
				"	</tr>\n" + 
				"\n" + 
				"	<tr>\n" + 
				"	<tr>\n" + 
				"		<td><hr /></td>\n" + 
				"	</tr>\n" + 
				"	<tr>\n" + 
				"		<td class=\"reason-td\">\n" + 
				"			<br />Your request has been <u>blocked</u> by <a href=\"http://www.mydlp.com\">MyDLP</a>\n" + 
				"		</td>\n" + 
				"	</tr>\n" +
				"	<tr>\n" + 
				"		<td class=\"reason-td\">\n" + 
				"			<br /> %%MESSAGE%% \n" + 
				"		</td>\n" + 
				"	</tr>\n" + 
				"	<tr>\n" + 
				"		<td class=\"reason-td\">\n" + 
				"			<br />This action has been taken according your corporate information security policies. Please contact your system administration if you have any questions.\n" + 
				"		</td>\n" + 
				"	</tr>\n" + 
				"</table>\n" + 
				"</div>\n" + 
				"</center>\n" + 
				"</body>\n" + 
				"\n" + 
				"</html>\n" + 
				"");

		getHibernateTemplate().saveOrUpdate(mailArchive);
		getHibernateTemplate().saveOrUpdate(archiveMinimumSize);
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
