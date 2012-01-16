package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.MIMEType;

public interface MIMETypeDAO {
	
	public List<MIMEType> getMIMETypes();
	
	public MIMEType save(MIMEType m);
}
