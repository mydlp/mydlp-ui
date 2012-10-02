package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.CustomActionDAO;
import com.mydlp.ui.domain.CustomAction;

@Service("customActionBRS")
@RemotingDestination
public class CustomActionBRSImpl implements CustomActionService
{
	
	@Autowired
	protected CustomActionDAO customActionDAO;

	@Override
	public List<CustomAction> getCustomActions(String searchStr) {
		return customActionDAO.getCustomActions(searchStr);
	}

	@Override
	public List<CustomAction> getCustomActions() {
		return customActionDAO.getCustomActions();
	}
	

}