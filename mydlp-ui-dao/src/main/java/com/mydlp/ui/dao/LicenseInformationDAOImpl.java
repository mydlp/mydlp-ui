package com.mydlp.ui.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.LicenseInformation;

@Repository("licenceInformationDAO")
@Transactional
public class LicenseInformationDAOImpl extends AbstractPolicyDAO implements LicenseInformationDAO {

	@Override
	public LicenseInformation getLicense() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(LicenseInformation.class);
		@SuppressWarnings("unchecked")
		List<LicenseInformation> list = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(list) ;
	}

	@Override
	public LicenseInformation saveLicense(LicenseInformation licenseInformation) {
		invalidateLicense();
		getHibernateTemplate().saveOrUpdate(licenseInformation);
		return licenseInformation;
	}

	@Override
	public void invalidateLicense() {
		getHibernateTemplate().bulkUpdate("delete from LicenseInformation l");
	}

}
