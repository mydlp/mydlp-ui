package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.RDBMSConnection;
import com.mydlp.ui.domain.RDBMSEnumeratedValue;
import com.mydlp.ui.domain.RDBMSInformationTarget;


public interface RDBMSConnectionDAO {
	
	public AbstractEntity save(AbstractEntity entity);
	
	public void remove(AbstractEntity entity);
	
	public Boolean hasValue(RDBMSInformationTarget rdbmsInformationTarget, int stringHashCode);
	
	public Boolean hasOtherValue(RDBMSInformationTarget rdbmsInformationTarget, int stringHashCode, String originalId);
	
	public void deleteValues(RDBMSInformationTarget rdbmsInformationTarget);
	
	public RDBMSEnumeratedValue getValue(RDBMSInformationTarget rdbmsInformationTarget, String origIdValue);
	
	public List<RDBMSConnection> getRDBMSConnections();
	
	public void finalizeProcess(Integer rdbmsInformationTargetId);
	
	public void startProcess(Integer rdbmsInformationTargetId);
	
	public RDBMSInformationTarget getInformationTargetById(Integer id);
}
