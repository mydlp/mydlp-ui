package com.mydlp.ui.service.rdbms.proxy;

import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.RDBMSInformationTarget;

public interface RDBMSObjectEnumProxy {

	public Boolean isValid(RDBMSInformationTarget rdbmsInformationTarget, AbstractEntity entity, String rowReturnValue);
	
	public Boolean shouldStoreValue();
	
	public void truncate(RDBMSInformationTarget rdbmsInformationTarget, AbstractEntity entity);
	
	public void delete(RDBMSInformationTarget rdbmsInformationTarget, AbstractEntity entity, String identifier);
	
	public void save(RDBMSInformationTarget rdbmsInformationTarget, AbstractEntity entity, String identifier, String rowReturnValue);
	
	public AbstractEntity refresh(RDBMSInformationTarget rdbmsInformationTarget, AbstractEntity entity);

}
