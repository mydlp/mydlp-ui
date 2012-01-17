package com.mydlp.ui.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.AbstractEntity;

@Repository("genericDAO")
@Transactional
public class GenericDAOImpl extends AbstractPolicyDAO implements GenericDAO {

	@Override
	@Transactional(readOnly=false)
	public AbstractEntity save(AbstractEntity i) {
		getHibernateTemplate().saveOrUpdate(i);
		return i;
	}

	@Override
	@Transactional(readOnly=false)
	public void remove(AbstractEntity i) {
		getHibernateTemplate().delete(i);
	}

}
