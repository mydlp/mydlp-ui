package com.mydlp.ui.remoting.blazeds;

import java.util.Calendar;

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

	@Override
	public Boolean saveLicenseKey(String s) {
		if(s.equals("testLicenseKey"))
		{
			LicenseInformation l = getLicense();
			int expireYear = 2012; int expireMonth = 3; int expireDay = 28;
			Calendar expireCalendar = Calendar.getInstance();
			expireCalendar.set(Calendar.YEAR, expireYear);
			expireCalendar.set(Calendar.MONTH, expireMonth);
			expireCalendar.set(Calendar.DATE, expireDay);
			l.setExpirationDate(expireCalendar.getTime());
			save(l);
			return true;
		}
		else return false;
	}

	@Override
	public Boolean isExpirationDateNear() {
		return licenseInformationDAO.isExpirationDateNear();
	}

	@Override
	public String isSoftLimitOrNear() {
		if(isSoftLimit())
			return "soft";
		else if(isExpirationDateNear())
			return "near";
		else
			return "none";
	}

	@Override
	public long getDayInformation() {
		return licenseInformationDAO.getDayInformation();
	}

}
