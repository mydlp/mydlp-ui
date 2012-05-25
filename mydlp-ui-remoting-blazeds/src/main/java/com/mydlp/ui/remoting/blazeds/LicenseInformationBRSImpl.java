package com.mydlp.ui.remoting.blazeds;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
	public Boolean saveLicenseKey(String s) {
		if(s.equals("testLicenseKey"))
		{
			LicenseInformation l = getLicense();
			Calendar now = new GregorianCalendar();
			now.add(Calendar.MONTH, 1);
			l.setExpirationDate(now.getTime());
			licenseInformationDAO.save(l);

			return true;
		}
		else return false;
	}
}
