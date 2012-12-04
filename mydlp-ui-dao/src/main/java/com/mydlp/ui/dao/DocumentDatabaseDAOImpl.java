package com.mydlp.ui.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	protected RDBMSConnectionDAO rdbmsConnectionDAO;

	@SuppressWarnings("unchecked")
	public List<DocumentDatabase> getDocumentDatabases() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(DocumentDatabase.class);
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	@Transactional(readOnly=false)
	public DocumentDatabase save(DocumentDatabase d) {
		if (d.getRdbmsInformationTarget() == null && 
				d.getRdbmsEntries() != null &&
				! d.getRdbmsEntries().isEmpty() ) {
			for (DocumentDatabaseRDBMSEntry entry : d.getRdbmsEntries()) {
				removeDocument(entry);
			}
			d.setRdbmsEntries(new ArrayList<DocumentDatabaseRDBMSEntry>());
		}
		getHibernateTemplate().saveOrUpdate(d);
		return d;
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
		r = getDocumentDatabaseById(r.getId());
		if (r.getRdbmsInformationTarget() != null) {
			rdbmsConnectionDAO.deleteValues(r.getRdbmsInformationTarget());
		}
		
		List<Document> deleteList = new ArrayList<Document>();
		
		if (r.getFileEntries() != null)
			deleteList.addAll(r.getFileEntries());
		
		if (r.getRdbmsEntries() != null)
			deleteList.addAll(r.getRdbmsEntries());
		
		r.setFileEntries(new ArrayList<DocumentDatabaseFileEntry>());
		r.setRdbmsEntries(new ArrayList<DocumentDatabaseRDBMSEntry>());
		
		getHibernateTemplate().save(r);
		getHibernateTemplate().delete(r);
		
		for (Document document : deleteList) {
			removeDocument(document);
		}
	}

	@Override
	public void removeDocument(Document d) {
		getHibernateTemplate().bulkUpdate("delete from DocumentFingerprint f where f.document.id=?", d.getId());
		getHibernateTemplate().delete(d);
	}

	@Override
	public void truncateRDBMSEntries(DocumentDatabase documentDatabase) {
		if (documentDatabase == null) return;
		documentDatabase = getHibernateTemplate().merge(documentDatabase);
		
		if (documentDatabase == null || documentDatabase.getRdbmsEntries() == null) return;
		
		for (DocumentDatabaseRDBMSEntry documentDatabaseRDBMSEntry : documentDatabase.getRdbmsEntries())
			removeDocument(documentDatabaseRDBMSEntry);
		
		documentDatabase.setRdbmsEntries(new ArrayList<DocumentDatabaseRDBMSEntry>());
		save(documentDatabase);
	}
	
	protected DocumentDatabaseRDBMSEntry getRDBMSEntryWithOrigId(DocumentDatabase documentDatabase, String originalId) {
		documentDatabase = getHibernateTemplate().merge(documentDatabase);
		@SuppressWarnings("unchecked")
		List<DocumentDatabaseRDBMSEntry> l = getHibernateTemplate().find(
				"select re from DocumentDatabase as dd left join dd.rdbmsEntries as re where dd.id=? and re.originalId=?",
					documentDatabase.getId(), originalId
				);
		return DAOUtil.getSingleResult(l);
	}

	@Override
	public void removeRDBMSEntryWithOrigId(DocumentDatabase documentDatabase,
			String originalId) {
		if (originalId == null) {
			logger.error("Can not remove RDBMSEntry without originalId");
			return;
		}
		documentDatabase = getHibernateTemplate().merge(documentDatabase);
		DocumentDatabaseRDBMSEntry documentDatabaseRDBMSEntry = getRDBMSEntryWithOrigId(documentDatabase, originalId);
		removeDocument(documentDatabaseRDBMSEntry);
		documentDatabase.getRdbmsEntries().remove(documentDatabaseRDBMSEntry);
		getHibernateTemplate().merge(documentDatabase);
	}

	@Override
	public Integer putRDBMSEntry(DocumentDatabase documentDatabase,
			String originalId, String value) {
		documentDatabase = getHibernateTemplate().merge(documentDatabase);
		DocumentDatabaseRDBMSEntry documentDatabaseRDBMSEntry = null;
		if (originalId != null)
			documentDatabaseRDBMSEntry = getRDBMSEntryWithOrigId(documentDatabase, originalId);
		
		if (documentDatabaseRDBMSEntry == null) {
			documentDatabaseRDBMSEntry = new DocumentDatabaseRDBMSEntry();
			documentDatabaseRDBMSEntry.setOriginalId(originalId);
			if (documentDatabase.getRdbmsEntries() == null)
				documentDatabase.setRdbmsEntries(new ArrayList<DocumentDatabaseRDBMSEntry>());
			documentDatabase.getRdbmsEntries().add(documentDatabaseRDBMSEntry);
			getHibernateTemplate().saveOrUpdate(documentDatabaseRDBMSEntry);
			getHibernateTemplate().merge(documentDatabase);
		} else {
			getHibernateTemplate().bulkUpdate("delete from DocumentFingerprint f where f.document.id=?",
					documentDatabaseRDBMSEntry.getId());
		}
		
		return documentDatabaseRDBMSEntry.getId();
	}

	@Override
	public Boolean isEntryExists(Integer documentDatabaseId, String fileHash) {
		@SuppressWarnings("unchecked")
		List<DocumentDatabaseRDBMSEntry> l = getHibernateTemplate().find(
				"select re from DocumentDatabase as dd left join dd.fileEntries as re where dd.id=? and re.md5Hash=?",
					documentDatabaseId, fileHash
				);
		
		return (l != null && l.size() > 0);
	}

}
