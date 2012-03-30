package com.mydlp.ui.remoting.blazeds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.LicenseInformationDAO;
import com.mydlp.ui.domain.LicenseInformation;

@Service("licenseInformationBRS")
@RemotingDestination
public class LicenseInformationBRSImpl implements LicenseInformationService{

	@Autowired
	protected LicenseInformationDAO licenseInformationDAO;
	
	@Override
	public LicenseInformation getLicense() {
		return licenseInformationDAO.getLicense();
	}

	@Override
	public LicenseInformation save(LicenseInformation l) {
		return licenseInformationDAO.save(l);
	}

	@Override
	public void remove(LicenseInformation l) {
		licenseInformationDAO.remove(l);
	}

	@Override
	public Boolean isSoftLimit() {
		return licenseInformationDAO.isSoftLimit();
	}

	@Override
	public Boolean isHardLimit() {
		return licenseInformationDAO.isHardLimit();
	}

}
