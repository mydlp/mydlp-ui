package com.mydlp.ui.remoting.blazeds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.LicenseInformationDAO;
import com.mydlp.ui.domain.LicenseInformation;
import com.mydlp.ui.service.LicenseLogoInformation;
import com.mydlp.ui.service.LicenseService;

@Service("licenseInformationBRS")
@RemotingDestination
public class LicenseInformationBRSImpl implements LicenseInformationService{

	@Autowired
	protected LicenseInformationDAO licenseInformationDAO;
	
	@Autowired
	protected LicenseService licenseService;
	
	@Override
	public LicenseInformation getLicense() {
		LicenseInformation l = licenseInformationDAO.getLicense();
		if(l != null && l.getLogoKey() == "xvasion")
			LicenseLogoInformation.licenseLogoKey = "static/images/logo_xvasion_220x70.png";
		else
			LicenseLogoInformation.licenseLogoKey = "static/images/logo.png";
		return l;
	}

	@Override
	public String saveLicenseKey(String s) {
		return licenseService.enterLicenseKey(s);
	}
}
