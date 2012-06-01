package com.mydlp.ui.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.LicenseInformationDAO;
import com.mydlp.ui.domain.LicenseInformation;
import com.mydlp.ui.thrift.LicenseObject;
import com.mydlp.ui.thrift.MyDLPUIThriftService;


@Service("licenseService")
public class LicenseServiceImpl implements LicenseService {
	
	@Autowired
	protected MyDLPUIThriftService myDLPUIThriftService;
	
	@Autowired
	protected LicenseInformationDAO licenseInformationDAO;
	
	
	@Scheduled(cron="0 0 4 * * ?")
	public void checkLicense() {
		scheduleLicenseCheckFun();
	}
	
	@PostConstruct
	protected void init() {
		scheduleLicenseCheckFun();
	}
	
	@Async
	protected void scheduleLicenseCheckFun() {
		LicenseObject thriftResult = myDLPUIThriftService.getLicense();
		
		if (!thriftResult.is_valid) {
			licenseInformationDAO.invalidateLicense();
			return;
		}
		
		Boolean saveFlag = false;
		LicenseInformation currentLicense = licenseInformationDAO.getLicense();
		if (currentLicense == null) {
			currentLicense = new LicenseInformation();
			saveFlag = true;
		}
		
		if (thriftResult.is_trial) {
			if (! LicenseInformation.TRIAL_LICENSE.equals(currentLicense.getLicenseType()) ) {
				currentLicense.setLicenseType(LicenseInformation.TRIAL_LICENSE);
				saveFlag = true;
			} 
		} else {
			if (! thriftResult.license_type.equals(currentLicense.getLicenseType())) {
				currentLicense.setLicenseType(thriftResult.license_type);
				saveFlag = true;
			}
		}
		
		if (thriftResult.number_of_users != currentLicense.getUserCount() ) {
			currentLicense.setUserCount(thriftResult.number_of_users);
			saveFlag = true;
		}
		
		if (thriftResult.administrative_users != currentLicense.getAdministrativeUserCount()) {
			currentLicense.setAdministrativeUserCount(thriftResult.administrative_users);
			saveFlag = true;
		}
		if (thriftResult.expiration_date != currentLicense.getExpirationDate()) {
			currentLicense.setExpirationDate(thriftResult.expiration_date);
			saveFlag = true;
		}
		
		if (saveFlag) {
			licenseInformationDAO.saveLicense(currentLicense);
		}
	}


	@Override
	public void enterLicenseKey(String licenseKey) {
		myDLPUIThriftService.saveLicenseKey(licenseKey);
	}

	@Override
	public LicenseInformation getCurrentLicense() {
		return licenseInformationDAO.getLicense();
	}

	@Override
	public void scheduleLicenseCheck() {
		scheduleLicenseCheckFun();
	}
	
}
