package com.mydlp.ui.service.rdbms.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.mydlp.ui.dao.RegularExpressionGroupDAO;
import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.RDBMSInformationTarget;
import com.mydlp.ui.domain.RegularExpressionGroup;

@Service("regularExpressionGroupEnumProxy")
public class RegularExpressionGroupEnumProxyImpl implements RDBMSObjectEnumProxy {

	@Autowired
	protected RegularExpressionGroupDAO regularExpressionGroupDAO;
	
	@Autowired
	@Qualifier("policyTransactionTemplate")
	protected TransactionTemplate transactionTemplate;
	
	@Override
	public Boolean isValid(RDBMSInformationTarget rdbmsInformationTarget,
			AbstractEntity entity, String rowReturnValue) {
		String str = rowReturnValue.trim();
		return str.length() > 3;
	}

	@Override
	public Boolean shouldStoreValue() {
		return true;
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

	@Override
	public AbstractEntity refresh(
			RDBMSInformationTarget rdbmsInformationTarget, final AbstractEntity entity) {
		return transactionTemplate.execute(new TransactionCallback<RegularExpressionGroup>() {
			@Override
			public RegularExpressionGroup doInTransaction(TransactionStatus arg0) {
				return regularExpressionGroupDAO.getRegularExpressionGroupById(entity.getId());
			}
		});
	}
	
	
}
