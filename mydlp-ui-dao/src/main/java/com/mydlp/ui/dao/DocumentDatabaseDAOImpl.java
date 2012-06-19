package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.Document;
import com.mydlp.ui.domain.DocumentDatabase;
import com.mydlp.ui.domain.DocumentDatabaseFileEntry;
import com.mydlp.ui.domain.DocumentDatabaseRDBMSEntry;
import com.mydlp.ui.domain.DocumentFingerprint;


@Repository("documentDatabaseDAO")
@Transactional
public class DocumentDatabaseDAOImpl extends AbstractPolicyDAO implements DocumentDatabaseDAO {

	@SuppressWarnings("unchecked")
	public List<DocumentDatabase> getDocumentDatabases() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(DocumentDatabase.class);
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	@Transactional(readOnly=false)
	public DocumentDatabase save(DocumentDatabase r) {
		getHibernateTemplate().saveOrUpdate(r);
		return r;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentDatabase> getDocumentDatabasesWithRDBMS() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(DocumentDatabase.class)
				.add(Restrictions.isNotNull("rdbmsInformationTarget"));
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public DocumentFingerprint saveFingerprint(DocumentFingerprint f) {
		getHibernateTemplate().saveOrUpdate(f);
		return f;
	}

	@Override
	public DocumentDatabase getDocumentDatabaseById(Integer id) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(DocumentDatabase.class)
				.add(Restrictions.eq("id", id));
		@SuppressWarnings("unchecked")
		List<DocumentDatabase> l = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(l);
	}

	@Override
	public DocumentDatabaseFileEntry saveFileEntry(DocumentDatabaseFileEntry f) {
		getHibernateTemplate().saveOrUpdate(f);
		return f;
	}

	@Override
	public void remove(DocumentDatabase r) {
		for (DocumentDatabaseFileEntry fe: r.getFileEntries()) {
			removeDocument(fe);
		}
		for (DocumentDatabaseRDBMSEntry re: r.getRdbmsEntries()) {
			removeDocument(re);
		}
		getHibernateTemplate().delete(r);
	}

	@Override
	public void removeDocument(Document d) {
		getHibernateTemplate().bulkUpdate("delete from DocumentFingerprint f where f.document.id=?", d.getId());
		getHibernateTemplate().delete(d);
	}

}
