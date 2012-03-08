package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.USBDevice;

@Repository("usbDeviceDAO")
@Transactional
public class USBDeviceDAOImpl extends AbstractPolicyDAO implements USBDeviceDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<USBDevice> getUSBDevices(Integer offset, Integer limit) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(USBDevice.class)
					.addOrder(Order.desc("id"));
		return criteria.getExecutableCriteria(getSession())
			.setFirstResult(offset)
			.setMaxResults(limit)
			.list();
	}

	@Override
	public Long getUSBDeviceCount() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(USBDevice.class)
					.setProjection(Projections.rowCount());
		@SuppressWarnings("unchecked")
		List<Long> returnList = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(returnList);
	}
		
}
