package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.InformationTypeDAO;
import com.mydlp.ui.domain.InformationType;

@Service("informationTypeBRS")
@RemotingDestination
public class InformationTypeBRSImpl implements InformationTypeService {

	@Autowired
	protected InformationTypeDAO informationTypeDAO;
	
	@Override
	public List<InformationType> getInformationTypes() {
		return informationTypeDAO.getInformationTypes();
	}

}
