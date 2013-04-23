package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.RemoteStorage;


@Repository("remoteStorageDAO")
@Transactional
public class RemoteStorageDAOImpl extends AbstractPolicyDAO implements RemoteStorageDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<RemoteStorage> getRemoteStorages() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(RemoteStorage.class);
		return getHibernateTemplate().findByCriteria(criteria);
	}

}
