package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.Config;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00033_Config_Syslog extends AbstractGranule {

	@Override
	protected void callback() {

		Config syslogAclHost = new Config();
		syslogAclHost.setKey("syslog_acl_host");
		syslogAclHost.setValue("127.0.0.1");
		Config syslogAclPort = new Config();
		syslogAclPort.setKey("syslog_acl_port");
		syslogAclPort.setValue("514");
		Config syslogAclFacility = new Config();
		syslogAclFacility.setKey("syslog_acl_facility");
		syslogAclFacility.setValue("local6");
		
		Config syslogDiagHost = new Config();
		syslogDiagHost.setKey("syslog_diag_host");
		syslogDiagHost.setValue("127.0.0.1");
		Config syslogDiagPort = new Config();
		syslogDiagPort.setKey("syslog_diag_port");
		syslogDiagPort.setValue("514");
		Config syslogDiagFacility = new Config();
		syslogDiagFacility.setKey("syslog_diag_facility");
		syslogDiagFacility.setValue("local6");

		Config syslogReportHost = new Config();
		syslogReportHost.setKey("syslog_report_host");
		syslogReportHost.setValue("127.0.0.1");
		Config syslogReportPort = new Config();
		syslogReportPort.setKey("syslog_report_port");
		syslogReportPort.setValue("514");
		Config syslogReportFacility = new Config();
		syslogReportFacility.setKey("syslog_report_facility");
		syslogReportFacility.setValue("local7");

		getHibernateTemplate().saveOrUpdate(syslogAclHost);
		getHibernateTemplate().saveOrUpdate(syslogAclPort);
		getHibernateTemplate().saveOrUpdate(syslogAclFacility);
		getHibernateTemplate().saveOrUpdate(syslogDiagHost);
		getHibernateTemplate().saveOrUpdate(syslogDiagPort);
		getHibernateTemplate().saveOrUpdate(syslogDiagFacility);
		getHibernateTemplate().saveOrUpdate(syslogReportHost);
		getHibernateTemplate().saveOrUpdate(syslogReportPort);
		getHibernateTemplate().saveOrUpdate(syslogReportFacility);


	}

}
