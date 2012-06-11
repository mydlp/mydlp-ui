package com.mydlp.ui.service.rdbms.proxy;

import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.RDBMSInformationTarget;

public interface RDBMSObjectEnumProxy {

	public Boolean handle(	RDBMSInformationTarget rdbmsInformationTarget,
							AbstractEntity entity,
							String rowReturnValue);
	
	public Boolean shouldStoreValue();

}
