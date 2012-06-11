package com.mydlp.ui.service;

import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.RDBMSInformationTarget;


public interface RDBMSEnumService {

	public void enumerate(RDBMSInformationTarget rdbmsInformationTarget, AbstractEntity entity);
	
}
