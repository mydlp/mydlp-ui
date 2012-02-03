package com.mydlp.ui.schema.granules;

import java.util.ArrayList;
import java.util.List;

import com.mydlp.ui.domain.DocumentDatabase;
import com.mydlp.ui.domain.DocumentDatabaseFileEntry;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00031_Document_Database extends AbstractGranule {

	public DocumentDatabaseFileEntry createMTObj(String fn) {
		DocumentDatabaseFileEntry ddfe = new DocumentDatabaseFileEntry();
		ddfe.setFilename(fn);
		getHibernateTemplate().saveOrUpdate(ddfe);
		return ddfe;
	}
	
	@Override
	protected void callback() {
		
		DocumentDatabase dd = new DocumentDatabase();
		dd.setNameKey("documentDatabase.test.label");
		getHibernateTemplate().saveOrUpdate(dd);
		List<DocumentDatabaseFileEntry> ddfes = new ArrayList<DocumentDatabaseFileEntry>();
		ddfes.add(createMTObj("example/hash/1"));
		ddfes.add(createMTObj("example/hash/2"));
		ddfes.add(createMTObj("example/hash/3"));
		ddfes.add(createMTObj("example/hash/4"));
		ddfes.add(createMTObj("example/hash/5"));
		dd.setFileEntries(ddfes);
		getHibernateTemplate().saveOrUpdate(dd);
	}
}
