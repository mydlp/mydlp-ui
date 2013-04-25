package com.mydlp.ui.remoting.blazeds;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.InformationTypeDAO;

@Service("informationTypeBRS")
@RemotingDestination
public class InformationTypeBRSImpl implements InformationTypeService
{
	
	@Autowired
	protected InformationTypeDAO informationTypeDAO;
	
	@Override
	public List<Map<String, String>> getITypeLabelsAndIds() {
		return informationTypeDAO.getITypeLabelsAndIds();
	}
	
}