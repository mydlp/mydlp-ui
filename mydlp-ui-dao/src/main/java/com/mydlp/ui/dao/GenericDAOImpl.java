package com.mydlp.ui.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.AbstractEntity;

@Repository("genericDAO")
@Transactional
public class GenericDAOImpl extends AbstractDAO implements GenericDAO {

	@Override
	public AbstractEntity save(AbstractEntity i) {
		getHibernateTemplate().saveOrUpdate(i);
		return i;
	}
	
}
