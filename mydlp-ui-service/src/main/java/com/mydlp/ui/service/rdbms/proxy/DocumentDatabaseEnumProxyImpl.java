package com.mydlp.ui.service.rdbms.proxy;

import java.nio.CharBuffer;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

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
	
	@Autowired
	@Qualifier("policyTransactionTemplate")
	protected TransactionTemplate transactionTemplate;
	

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
			final AbstractEntity entity) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				documentDatabaseDAO.truncateRDBMSEntries((DocumentDatabase)entity);
			}
		});
	}

	@Override
	public void delete(RDBMSInformationTarget rdbmsInformationTarget,
			final AbstractEntity entity, final String identifier) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				documentDatabaseDAO.removeRDBMSEntryWithOrigId((DocumentDatabase)entity, identifier);
			}
		});
	}

	@Override
	public void save(RDBMSInformationTarget rdbmsInformationTarget,
			final AbstractEntity entity, final String identifier, final String rowReturnValue) {
		Integer documentId = transactionTemplate.execute(new TransactionCallback<Integer>() {
			@Override
			public Integer doInTransaction(TransactionStatus arg0) {
				return documentDatabaseDAO.putRDBMSEntry((DocumentDatabase) entity, identifier, rowReturnValue);
			}
		});
		thriftService.generateFingerprints(documentId.longValue(), FILENAME, Charset.forName("UTF-8").encode(CharBuffer.wrap(rowReturnValue)));
	}
	
}
