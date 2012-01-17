package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.MIMEType;

@Repository("MIMETYpeDAO")
@Transactional
public class MIMETypeDAOImpl extends AbstractPolicyDAO implements MIMETypeDAO{

	@SuppressWarnings("unchecked")
	public List<MIMEType> getMIMETypes() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(MIMEType.class);
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	@Transactional(readOnly=false)
	public MIMEType save(MIMEType m) {
		getHibernateTemplate().saveOrUpdate(m);
		return m;
	}

}
