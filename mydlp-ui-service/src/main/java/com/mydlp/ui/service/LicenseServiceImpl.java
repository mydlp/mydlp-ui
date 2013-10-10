package com.mydlp.ui.service;

import java.util.Timer;
import java.util.TimerTask;

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
	
	//private static Logger logger = LoggerFactory.getLogger(LicenseServiceImpl.class);
	
	@Autowired
	protected MyDLPUIThriftService myDLPUIThriftService;
	
	@Autowired
	protected LicenseInformationDAO licenseInformationDAO;
	
	protected Integer retryCounter = 0;
	protected Boolean licenseAcquired = true;
	
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
		licenseAcquired = false;
		LicenseObject thriftResult = myDLPUIThriftService.getLicense();
		
		if (thriftResult == null) {
			return;
		}
		
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
		if(thriftResult.logo_key != currentLicense.getLogoKey())
		{
			currentLicense.setLogoKey(thriftResult.logo_key);
			saveFlag = true;
		}
		
		if(thriftResult.number_of_allocated_seats != currentLicense.getNumberOfAllocatedSeats())
		{
			currentLicense.setNumberOfAllocatedSeats(thriftResult.number_of_allocated_seats);
			saveFlag = true;
		}
		
		if(!thriftResult.user_email.equals(currentLicense.getUserEmail()))
		{
			currentLicense.setUserEmail(thriftResult.user_email);
			saveFlag = true;
		}
		
		if (saveFlag) {
			licenseInformationDAO.saveLicense(currentLicense);
		}
		licenseAcquired = true;
	}


	@Override
	public String enterLicenseKey(String licenseKey) {
		String msg = myDLPUIThriftService.saveLicenseKey(licenseKey);
		incrementRetryCounter(3);
		scheduleLicenseCheck(100);
		return msg;
	}

	@Override
	public LicenseInformation getCurrentLicense() {
		return licenseInformationDAO.getLicense();
	}

	@Override
	public void scheduleLicenseCheck() {
		scheduleLicenseCheck(100);
	}
	
	protected void scheduleLicenseCheck(final int delay) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		   public void run() {
			   scheduleLicenseCheckFun();
			   if (!licenseAcquired && retryCounter > 0)
			   {
				   retryCounter--;
				   scheduleLicenseCheck(delay);
			   }
		   }
		}, delay);
	}
	
	protected void incrementRetryCounter() {
		incrementRetryCounter(1);
	}
	
	protected void incrementRetryCounter(int i) {
		retryCounter += i;
	}
	
}
