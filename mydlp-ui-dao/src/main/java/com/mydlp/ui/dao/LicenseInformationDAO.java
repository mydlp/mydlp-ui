package com.mydlp.ui.dao;

import com.mydlp.ui.domain.LicenseInformation;

public interface LicenseInformationDAO {
	
	public LicenseInformation getLicense();
	public LicenseInformation save(LicenseInformation l);
	public void remove(LicenseInformation l);
	public Boolean isSoftLimit();
	public Boolean isHardLimit();
	public Boolean isExpirationDateNear();
	public long getDayInformation();
}
