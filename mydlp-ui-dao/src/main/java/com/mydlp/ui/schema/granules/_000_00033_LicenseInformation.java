package com.mydlp.ui.schema.granules;

import java.util.Calendar;

import com.mydlp.ui.domain.LicenseInformation;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00033_LicenseInformation extends AbstractGranule{

	@Override
	protected void callback() {
		int issueYear = 2012; int issueMonth = 2; int issueDay = 28;
		int expireYear = 2012; int expireMonth = 4; int expireDay = 10;
		LicenseInformation licenseInformation = new LicenseInformation();
		Calendar issueCalendar = Calendar.getInstance();
		issueCalendar.set(Calendar.YEAR, issueYear);
		issueCalendar.set(Calendar.MONTH, issueMonth);
		issueCalendar.set(Calendar.DATE, issueDay);
		licenseInformation.setIssueDate(issueCalendar.getTime());
		licenseInformation.setUserCount(100);
		licenseInformation.setLicenseType(LicenseInformation.TRIAL_LICENSE);
		licenseInformation.setLicenseKey("testLicenseKey");
		licenseInformation.setAdministrativeUserCount(1);
		Calendar expireCalendar = Calendar.getInstance();
		expireCalendar.set(Calendar.YEAR, expireYear);
		expireCalendar.set(Calendar.MONTH, expireMonth);
		expireCalendar.set(Calendar.DATE, expireDay);
		licenseInformation.setExpirationDate(expireCalendar.getTime());
		getHibernateTemplate().saveOrUpdate(licenseInformation);
	}
		
}
