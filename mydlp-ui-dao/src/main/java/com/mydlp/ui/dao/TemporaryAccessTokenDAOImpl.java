package com.mydlp.ui.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.TemporaryAccessToken;

@Repository("temporaryAccessTokenDAO")
@Transactional
public class TemporaryAccessTokenDAOImpl extends AbstractLogDAO implements
		TemporaryAccessTokenDAO {
	
	private static Logger logger = LoggerFactory.getLogger(TemporaryAccessTokenDAOImpl.class);

	@Override
	public String generateTokenKey(String ipAddress, String username,
			String serviceName, String serviceParam) {
		revokateTokens(ipAddress, username, serviceName);
		TemporaryAccessToken token = new TemporaryAccessToken();
		token.setIpAddress(ipAddress);
		token.setUsername(username);
		token.setServiceName(serviceName);
		token.setServiceParam(serviceParam);
		
		String tokenKey = md5(
				ipAddress + username + serviceName + serviceParam +
				System.currentTimeMillis() + token.toString() + Thread.currentThread().toString()
				);
		
		token.setTokenKey(tokenKey);
		token.setLastUpdate(new Date());
		Calendar cal = Calendar.getInstance();
		cal.setTime(token.getLastUpdate());
		cal.add(Calendar.HOUR_OF_DAY, 2);
		token.setExpirationDate(cal.getTime());
		
		getHibernateTemplate().saveOrUpdate(token);
		
		return tokenKey;
	}
	
	private static String md5(String input) {
        String md5 = null;
        if(null == input) return null;
        try {
	        MessageDigest digest = MessageDigest.getInstance("MD5");
	        digest.update(input.getBytes(), 0, input.length());
	        md5 = new BigInteger(1, digest.digest()).toString(16);
	        if (md5.length() == 31)
	        {
	        	md5 = "0" + md5;
	        }
        } catch (NoSuchAlgorithmException e) {
            logger.error("No such algorithm", e);
        }
        return md5;
    }

	protected void revokateTokens(String ipAddress, String username, String serviceName)
	{
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(TemporaryAccessToken.class)
					.add(Restrictions.eq("ipAddress", ipAddress))
					.add(Restrictions.eq("username", username))
					.add(Restrictions.eq("serviceName", serviceName));
		@SuppressWarnings("unchecked")
		List<TemporaryAccessToken> list = getHibernateTemplate().findByCriteria(criteria);
		for (TemporaryAccessToken temporaryAccessToken : list) {
			getHibernateTemplate().delete(temporaryAccessToken);
		}
	}

	@Override
	public TemporaryAccessToken getTokenObj(String tokenKey) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(TemporaryAccessToken.class)
					.add(Restrictions.eq("tokenKey", tokenKey));
		@SuppressWarnings("unchecked")
		List<TemporaryAccessToken> list = getHibernateTemplate().findByCriteria(criteria);
		TemporaryAccessToken token = DAOUtil.getSingleResult(list);
		
		if (token != null)
		{
			token.setLastUpdate(new Date());
			getHibernateTemplate().saveOrUpdate(token);
		}
		return token;
	}

	@Override
	public void cleanupExpiredTokens() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(TemporaryAccessToken.class)
					.add(Restrictions.lt("expirationDate", new Date()));
		@SuppressWarnings("unchecked")
		List<TemporaryAccessToken> list = getHibernateTemplate().findByCriteria(criteria);
		for (TemporaryAccessToken temporaryAccessToken : list) {
			getHibernateTemplate().delete(temporaryAccessToken);
		}
	}

	@Override
	public void cleanupIdleTokens() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MINUTE, -20);
		
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(TemporaryAccessToken.class)
					.add(Restrictions.lt("lastUpdate", cal.getTime()));
		@SuppressWarnings("unchecked")
		List<TemporaryAccessToken> list = getHibernateTemplate().findByCriteria(criteria);
		for (TemporaryAccessToken temporaryAccessToken : list) {
			getHibernateTemplate().delete(temporaryAccessToken);
		}
	}

	@Override
	public Boolean hasAnyValidToken(String serviceName, String serviceParam) {
		cleanupExpiredTokens();
		cleanupIdleTokens();
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(TemporaryAccessToken.class)
					.add(Restrictions.eq("serviceName", serviceName))
					.add(Restrictions.eq("serviceParam", serviceParam));
		@SuppressWarnings("unchecked")
		List<TemporaryAccessToken> list = getHibernateTemplate().findByCriteria(criteria);
		return !list.isEmpty();
	}

	@Override
	public void revokateAllTokens(String serviceName, String serviceParam) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(TemporaryAccessToken.class)
					.add(Restrictions.eq("serviceName", serviceName))
					.add(Restrictions.eq("serviceParam", serviceParam));
		@SuppressWarnings("unchecked")
		List<TemporaryAccessToken> list = getHibernateTemplate().findByCriteria(criteria);
		for (TemporaryAccessToken temporaryAccessToken : list) {
			getHibernateTemplate().delete(temporaryAccessToken);
		}
	}
	
}
