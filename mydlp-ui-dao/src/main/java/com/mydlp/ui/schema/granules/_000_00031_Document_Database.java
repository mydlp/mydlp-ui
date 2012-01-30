package com.mydlp.ui.schema.granules;

import java.util.ArrayList;
import java.util.List;

import com.mydlp.ui.domain.DocumentDatabase;
import com.mydlp.ui.domain.DocumentDatabaseFileEntry;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00031_Document_Database extends AbstractGranule {

	public DocumentDatabaseFileEntry createMTObj(String md5Hash) {
		DocumentDatabaseFileEntry mt = new DocumentDatabaseFileEntry();
		mt.setMd5Hash(md5Hash);
		getHibernateTemplate().saveOrUpdate(mt);
		return mt;
	}
	
	@Override
	protected void callback() {
		
		
		DocumentDatabase df = new DocumentDatabase();
		df.setNameKey("documentDatabase.test.label");
		//df.setMatcher(m);
		List<DocumentDatabaseFileEntry> mts = new ArrayList<DocumentDatabaseFileEntry>();
		mts.add(createMTObj("example/hash/1"));
		mts.add(createMTObj("example/hash/2"));
		mts.add(createMTObj("example/hash/3"));
		mts.add(createMTObj("example/hash/4"));
		mts.add(createMTObj("example/hash/5"));
		mts.add(createMTObj("example/hash/6"));
		mts.add(createMTObj("example/hash/7"));
		mts.add(createMTObj("example/hash/8"));
		mts.add(createMTObj("example/hash/9"));
		mts.add(createMTObj("example/hash/10"));
		mts.add(createMTObj("example/hash/11"));
		df.setFileEntries(mts);
		getHibernateTemplate().saveOrUpdate(df);
		
	}
}
