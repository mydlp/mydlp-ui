package com.mydlp.ui.service.rdbms.proxy;

import java.nio.CharBuffer;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.DocumentDatabaseDAO;
import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.DocumentDatabase;
import com.mydlp.ui.domain.RDBMSInformationTarget;
import com.mydlp.ui.thrift.MyDLPUIThriftService;

@Service("documentDatabaseEnumProxy")
public class DocumentDatabaseEnumProxyImpl implements RDBMSObjectEnumProxy {
	
	protected static final String FILENAME = "rdbms_enum_cell.txt";

	@Autowired
	protected DocumentDatabaseDAO documentDatabaseDAO;
	
	@Autowired
	protected MyDLPUIThriftService thriftService;
	

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
		documentDatabaseDAO.truncateRDBMSEntries((DocumentDatabase)entity);
	}

	@Override
	public void delete(RDBMSInformationTarget rdbmsInformationTarget,
			AbstractEntity entity, String identifier) {
		documentDatabaseDAO.removeRDBMSEntryWithOrigId((DocumentDatabase)entity, identifier);
	}

	@Override
	public void save(RDBMSInformationTarget rdbmsInformationTarget,
			AbstractEntity entity, String identifier, String rowReturnValue) {
		Integer documentId = documentDatabaseDAO.putRDBMSEntry((DocumentDatabase) entity, identifier, rowReturnValue);
		thriftService.generateFingerprints(documentId.longValue(), FILENAME, Charset.forName("UTF-8").encode(CharBuffer.wrap(rowReturnValue)));
	}
	
}
