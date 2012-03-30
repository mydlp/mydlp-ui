package com.mydlp.ui.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.LicenseInformation;

@Repository("licenceInformationDAO")
@Transactional
public class LicenseInformationDAOImpl extends AbstractPolicyDAO implements LicenseInformationDAO{

	@Override
	public LicenseInformation getLicense() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(LicenseInformation.class);
		@SuppressWarnings("unchecked")
		List<LicenseInformation> list = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(list) ;
	}

	@Override
	@Transactional(readOnly=false)
	public LicenseInformation save(LicenseInformation l) {
		getHibernateTemplate().saveOrUpdate(l);
		return l;
	}

	@Override
	@Transactional(readOnly=false)
	public void remove(LicenseInformation l) {
		getHibernateTemplate().delete(l);
	}

	@Override
	public Boolean isSoftLimit() {
		Calendar now = Calendar.getInstance();
		Date nowDate = now.getTime();
		if(getLicense().getExpirationDate().before(nowDate))
			return true;
		else return false;			
	}

	@Override
	@Transactional(readOnly=false)
	public Boolean isHardLimit() {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, -30);
		Date nowDate = now.getTime();
		if(getLicense().getExpirationDate().before(nowDate))
			return true;
		else return false;
	}

}
