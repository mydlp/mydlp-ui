package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.InformationType;

@Repository("InfomationTypeDAO")
@Transactional
public class InformationTypeDAOImpl extends AbstractDAO implements InformationTypeDAO {

	@SuppressWarnings("unchecked")
	public List<InformationType> getInformationTypes() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InformationType.class)
					.add(Restrictions.isNull("category"));
		return getHibernateTemplate().findByCriteria(criteria);
	}

}
