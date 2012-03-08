package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.USBDeviceDAO;
import com.mydlp.ui.domain.USBDevice;

@Service("usbDeviceBRS")
@RemotingDestination
public class USBDeviceBRSImpl implements USBDeviceService
{
	
	@Autowired
	protected USBDeviceDAO usbDeviceDAO;
	
	@Override
	public List<USBDevice> getUSBDevices(Integer offset, Integer limit) {
		return usbDeviceDAO.getUSBDevices(offset, limit);
	}

	@Override
	public Long getUSBDeviceCount() {
		return usbDeviceDAO.getUSBDeviceCount();
	}

}