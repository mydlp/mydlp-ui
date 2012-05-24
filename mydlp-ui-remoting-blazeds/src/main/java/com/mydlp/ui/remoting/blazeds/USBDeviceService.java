package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.USBDevice;

@Secured(AuthSecurityRole.ROLE_ADMIN)
public interface USBDeviceService {

	public List<USBDevice> getUSBDevices(String searchStr, Integer offset, Integer limit);
	
	public Long getUSBDeviceCount(String searchStr);
	
}