package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.MIMETypeDAO;
import com.mydlp.ui.domain.MIMEType;

@Service("MIMETypeBRS")
@RemotingDestination
public class MIMETypeBRSImpl implements MIMETypeService
{

	@Autowired
	protected MIMETypeDAO MIMETypeDAO;
	
	@Override
	public List<MIMEType> getMIMETypes() {
		return MIMETypeDAO.getMIMETypes();
	}

	@Override
	public MIMEType save(MIMEType m) {
		return MIMETypeDAO.save(m);
	}
	
}