package com.mydlp.ui.dao;

import com.mydlp.ui.domain.LicenseInformation;

public interface LicenseInformationDAO {
	
	public LicenseInformation getLicense();
	
	public LicenseInformation saveLicense(LicenseInformation licenseInformation);

	public void invalidateLicense();
}
