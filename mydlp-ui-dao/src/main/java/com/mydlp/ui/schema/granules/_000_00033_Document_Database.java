package com.mydlp.ui.schema.granules;

import java.util.ArrayList;
import java.util.List;

import com.mydlp.ui.domain.DocumentDatabase;
import com.mydlp.ui.domain.DocumentDatabaseFileEntry;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00033_Document_Database extends AbstractGranule {

	public DocumentDatabaseFileEntry createMTObj(String fn) {
		DocumentDatabaseFileEntry ddfe = new DocumentDatabaseFileEntry();
		ddfe.setFilename(fn);
		getHibernateTemplate().saveOrUpdate(ddfe);
		return ddfe;
	}
	
	@Override
	protected void callback() {
		
		DocumentDatabase dd = new DocumentDatabase();
		dd.setNameKey("documentDatabase.testSecond.label");
		getHibernateTemplate().saveOrUpdate(dd);
		List<DocumentDatabaseFileEntry> ddfes = new ArrayList<DocumentDatabaseFileEntry>();
		ddfes.add(createMTObj("example/hash/6"));
		ddfes.add(createMTObj("example/hash/7"));
		ddfes.add(createMTObj("example/hash/8"));
		ddfes.add(createMTObj("example/hash/9"));
		ddfes.add(createMTObj("example/hash/10"));
		dd.setFileEntries(ddfes);
		getHibernateTemplate().saveOrUpdate(dd);
	}
}
