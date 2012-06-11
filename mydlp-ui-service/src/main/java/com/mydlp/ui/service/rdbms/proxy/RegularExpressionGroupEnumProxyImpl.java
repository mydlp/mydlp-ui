package com.mydlp.ui.service.rdbms.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.RegularExpressionGroupDAO;
import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.RDBMSInformationTarget;

@Service("regularExpressionGroupEnumProxy")
public class RegularExpressionGroupEnumProxyImpl implements RDBMSObjectEnumProxy {

	@Autowired
	protected RegularExpressionGroupDAO regularExpressionGroupDAO;

	@Override
	public Boolean handle(RDBMSInformationTarget rdbmsInformationTarget,
			AbstractEntity entity, String rowReturnValue) {
		String str = rowReturnValue.trim();
		return str.length() > 3;
	}

	@Override
	public Boolean shouldStoreValue() {
		return true;
	}
	
	
}
