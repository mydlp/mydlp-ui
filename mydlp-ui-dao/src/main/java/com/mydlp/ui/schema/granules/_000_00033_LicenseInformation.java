package com.mydlp.ui.schema.granules;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.mydlp.ui.domain.LicenseInformation;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00033_LicenseInformation extends AbstractGranule{

	@Override
	protected void callback() {
		
		LicenseInformation licenseInformation = new LicenseInformation();
		Calendar issueCalendar = new GregorianCalendar();
		licenseInformation.setIssueDate(issueCalendar.getTime());
		licenseInformation.setUserCount(100);
		licenseInformation.setLicenseType(LicenseInformation.ENTERPRISE_LICENSE);
		licenseInformation.setLicenseKey("testLicenseKey");
		licenseInformation.setAdministrativeUserCount(1);
		Calendar expireCalendar = new GregorianCalendar();
		expireCalendar.add(Calendar.MONTH, 1);
		licenseInformation.setExpirationDate(expireCalendar.getTime());
		getHibernateTemplate().saveOrUpdate(licenseInformation);
	}
		
}
