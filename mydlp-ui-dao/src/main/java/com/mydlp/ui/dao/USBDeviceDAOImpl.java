package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.USBDevice;

@Repository("usbDeviceDAO")
@Transactional
public class USBDeviceDAOImpl extends AbstractPolicyDAO implements USBDeviceDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<USBDevice> getUSBDevices(String searchStr, Integer offset, Integer limit) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(USBDevice.class)
					.addOrder(Order.desc("id"));
		criteria = applySearchCriteria(criteria, searchStr);
		return criteria.getExecutableCriteria(getSession())
			.setFirstResult(offset)
			.setMaxResults(limit)
			.list();
	}
	
	protected DetachedCriteria applySearchCriteria(DetachedCriteria detachedCriteria, String searchStr)
	{
		DetachedCriteria criteria = detachedCriteria;
		
		if (searchStr == null || searchStr.length() == 0)
			return criteria;
		
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(Restrictions.sqlRestriction("(1=0)")); //  defaults to false
		
		disjunction.add(Restrictions.ilike("comment", "%" + searchStr + "%"));
		disjunction.add(Restrictions.ilike("deviceId", "%" + searchStr + "%"));
		disjunction.add(Restrictions.ilike("uniqId", "%" + searchStr + "%"));
		
		criteria = criteria.add(disjunction);
		
		return criteria;
	}

	@Override
	public Long getUSBDeviceCount(String searchStr) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(USBDevice.class)
					.setProjection(Projections.rowCount());
		criteria = applySearchCriteria(criteria, searchStr);
		@SuppressWarnings("unchecked")
		List<Long> returnList = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(returnList);
	}
		
}
