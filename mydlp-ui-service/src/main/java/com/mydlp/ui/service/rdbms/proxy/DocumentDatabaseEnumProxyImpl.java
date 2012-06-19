package com.mydlp.ui.service.rdbms.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.DocumentDatabaseDAO;
import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.RDBMSInformationTarget;

@Service("documentDatabaseEnumProxy")
public class DocumentDatabaseEnumProxyImpl implements RDBMSObjectEnumProxy {

	@Autowired
	protected DocumentDatabaseDAO documentDatabaseDAO;

	@Override
	public Boolean isValid(RDBMSInformationTarget rdbmsInformationTarget,
			AbstractEntity entity, String rowReturnValue) {
		String str = rowReturnValue.trim();
		return str.length() > 150;
	}

	@Override
	public Boolean shouldStoreValue() {
		return false;
	}

	@Override
	public void truncate(RDBMSInformationTarget rdbmsInformationTarget,
			AbstractEntity entity) {
	}

	@Override
	public void delete(RDBMSInformationTarget rdbmsInformationTarget,
			AbstractEntity entity, String identifier) {
	}

	@Override
	public void save(RDBMSInformationTarget rdbmsInformationTarget,
			AbstractEntity entity, String identifier, String rowReturnValue) {
	}
	
}
