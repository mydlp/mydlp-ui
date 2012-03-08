package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.USBDevice;


public interface USBDeviceDAO {

	public List<USBDevice> getUSBDevices(Integer offset, Integer limit);
	
	public Long getUSBDeviceCount();
	
}
