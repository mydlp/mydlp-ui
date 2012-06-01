package com.mydlp.ui.service;

import com.mydlp.ui.domain.LicenseInformation;


public interface LicenseService {

	public void enterLicenseKey(String licenseKey);
	
	public LicenseInformation getCurrentLicense();
	
	public void scheduleLicenseCheck();
	
}
