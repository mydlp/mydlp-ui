package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.DataFormat;

@Repository("dataFormatDAO")
@Transactional
public class DataFormatDAOImpl extends AbstractDAO implements DataFormatDAO {

	@SuppressWarnings("unchecked")
	public List<DataFormat> getDataFormats() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(DataFormat.class);
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	@Transactional(readOnly=false)
	public DataFormat save(DataFormat d) {
		getHibernateTemplate().saveOrUpdate(d);
		return d;
	}
	
}
