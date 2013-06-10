package com.mydlp.ui.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.ADDomain;
import com.mydlp.ui.domain.ADDomainGroup;
import com.mydlp.ui.domain.ADDomainItem;
import com.mydlp.ui.domain.ADDomainItemGroup;
import com.mydlp.ui.domain.ADDomainOU;
import com.mydlp.ui.domain.ADDomainRoot;
import com.mydlp.ui.domain.ADDomainUser;
import com.mydlp.ui.domain.AbstractEntity;

@Repository("adDomainDAO")
@Transactional
public class ADDomainDAOImpl extends AbstractPolicyDAO implements ADDomainDAO {
	
	private static Logger logger = LoggerFactory.getLogger(ADDomainDAOImpl.class);

	@Override
	@Transactional(readOnly = false)
	public ADDomain saveDomain(ADDomain domain) {
		getHibernateTemplate().saveOrUpdate(domain);
		return domain;
	}

	@Override
	@Transactional(readOnly = false)
	public ADDomainItem saveDomainItem(ADDomainItem domainItem) {
		getHibernateTemplate().saveOrUpdate(domainItem);
		return domainItem;
	}

	@Override
	@Transactional(readOnly = false)
	public void remove(AbstractEntity domainObj) {
		getHibernateTemplate().delete(domainObj);
	}

	@Override
	public ADDomainItem findByDistinguishedName(Integer domainId,
			String distinguishedName) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(ADDomainItem.class)
				.add(Restrictions.eq("distinguishedName", distinguishedName))
				.add(Restrictions.eq("domainId", domainId));
		if (distinguishedName != null)
			criteria = criteria.add(Restrictions.eq("distinguishedNameHash",
					new Integer(distinguishedName.hashCode())));
		@SuppressWarnings("unchecked")
		List<ADDomainItem> l = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(l);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ADDomain> getADDomains() {
		DetachedCriteria criteria = DetachedCriteria.forClass(ADDomain.class);
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public List<ADDomainItem> getChildrenOf(ADDomainItemGroup domainItemGroup) {
		return getChildrenOfById(domainItemGroup.getId());
	}
	
	@SuppressWarnings("unchecked")
	public List<ADDomainItem> getChildrenOfById(Integer parentID) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				ADDomainItem.class).add(
				Restrictions.eq("parent.id", parentID));
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public void finalizeProcess(Integer domainId) {
		getHibernateTemplate()
				.bulkUpdate(
						"update from ADDomain d set d.currentlyEnumerating=false where d.id=?",
						domainId);
	}

	@Override
	public void startProcess(Integer domainId) {
		getHibernateTemplate()
				.bulkUpdate(
						"update from ADDomain d set d.currentlyEnumerating=true where d.id=?",
						domainId);
	}

	@Override
	public AbstractEntity merge(AbstractEntity domainObj) {
		return getHibernateTemplate().merge(domainObj);
	}

	@Override
	public ADDomainRoot getDomainRoot(Integer domainId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				ADDomainRoot.class).add(Restrictions.eq("domain.id", domainId));
		@SuppressWarnings("unchecked")
		List<ADDomainRoot> l = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(l);
	}

	@Override
	public ADDomain getDomainById(Integer id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ADDomain.class)
				.add(Restrictions.eq("id", id));
		@SuppressWarnings("unchecked")
		List<ADDomain> l = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(l);
	}

	@Override
	public List<ADDomainItem> getFilteredADDomains(String searchString) {
		List<ADDomainItem> result = new ArrayList<ADDomainItem>();

		DetachedCriteria criteria = DetachedCriteria
				.forClass(ADDomainGroup.class);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(Restrictions.sqlRestriction("(1=0)")); // defaults to
																// false
		disjunction.add(Restrictions.ilike("name", "%" + searchString + "%"));
		// disjunction.add(Restrictions.ilike("distinguishedName", "%" +
		// searchString + "%"));
		criteria = criteria.add(disjunction);

		@SuppressWarnings("unchecked")
		List<ADDomainItem> result1 = getHibernateTemplate().findByCriteria(
				criteria);

		DetachedCriteria criteria2 = DetachedCriteria
				.forClass(ADDomainOU.class);
		Disjunction disjunction2 = Restrictions.disjunction();
		disjunction2.add(Restrictions.sqlRestriction("(1=0)")); // defaults to
																// false
		disjunction2.add(Restrictions.ilike("name", "%" + searchString + "%"));
		// disjunction2.add(Restrictions.ilike("distinguishedName", "%" +
		// searchString + "%"));
		criteria2 = criteria2.add(disjunction2);

		@SuppressWarnings("unchecked")
		List<ADDomainItem> result2 = getHibernateTemplate().findByCriteria(
				criteria2);

		// DetachedCriteria subquery =
		// DetachedCriteria.forClass(ADDomainUser.class, "aliases");

		DetachedCriteria criteria3 = DetachedCriteria
				.forClass(ADDomainUser.class);
		Disjunction disjunction3 = Restrictions.disjunction();
		disjunction3.add(Restrictions.sqlRestriction("(1=0)")); // defaults to
																// false
		disjunction3.add(Restrictions.ilike("displayName", "%" + searchString
				+ "%"));
		disjunction3.add(Restrictions.ilike("sAMAccountName", "%"
				+ searchString + "%"));
		// disjunction3.add(Restrictions.ilike("distinguishedName", "%" +
		// searchString + "%"));
		// disjunction3.add(Restrictions.ilike("aliases.userAlias",
		// searchString));
		criteria3 = criteria3.add(disjunction3);

		@SuppressWarnings("unchecked")
		List<ADDomainItem> result3 = getHibernateTemplate().findByCriteria(
				criteria3);

		result.addAll(result1);
		result.addAll(result2);
		result.addAll(result3);

		return result;
	}

	@Override
	public ADDomainItem findByDistinguishedName(ADDomain domain,
			String distinguishedName) {
		return findByDistinguishedName(domain.getId(), distinguishedName);
	}

	@Override
	public void finalizeAll() {
		getHibernateTemplate().bulkUpdate(
				"update from ADDomain d set d.currentlyEnumerating=false");
	}

	@Override
	public ADDomainItem reloadDomainItem(ADDomainItem item) {
		Integer id = item.getId();
		getHibernateTemplate().evict(item);
		return getHibernateTemplate().get(ADDomainItem.class, id);
	}

	protected static final String AD_KEY_USER = "user";
	protected static final String AD_KEY_OU = "ou";
	protected static final String AD_KEY_GROUP = "group";
	
	protected void removeOU(Integer id) {
		Map<String, Set<Integer>> itemsToRemove = new TreeMap<String, Set<Integer>>();
		itemsToRemove.put(AD_KEY_USER, new TreeSet<Integer>());
		itemsToRemove.put(AD_KEY_OU, new TreeSet<Integer>());
		itemsToRemove.put(AD_KEY_GROUP, new TreeSet<Integer>());
		
		List<ADDomainItem> itemsOfOU = getChildrenOfById(id);
		for (ADDomainItem adDomainItem : itemsOfOU) {
			Set<Integer> groupSet = null;
			if (adDomainItem instanceof ADDomainUser) 
			{
				groupSet = itemsToRemove.get(AD_KEY_USER);
			} 
			else if (adDomainItem instanceof ADDomainGroup) 
			{
				groupSet = itemsToRemove.get(AD_KEY_GROUP);
			} 
			else if (adDomainItem instanceof ADDomainOU) 
			{
				groupSet = itemsToRemove.get(AD_KEY_OU);
			}
			
			if (groupSet != null)
			{
				groupSet.add(adDomainItem.getId());
			}
			else
			{
				logger.error("Cannot remove domain category for item. Clazz: " + adDomainItem.getClass().getSimpleName());
			}
		}
		
		removeDomainItems(itemsToRemove);
	}
	
	
	@Override
	public void removeDomainItems(Map<String, Set<Integer>> itemsToRemove) {
		for (String key : itemsToRemove.keySet()) {
			for (Integer id : itemsToRemove.get(key)) {
				if (key.equals(AD_KEY_USER))
				{
					removeGroupMembershipsOfUser(id);
				}
				else if (key.equals(AD_KEY_GROUP))
				{
					removeGroupMemberships(id);
				}
				else if (key.equals(AD_KEY_OU))
				{
					removeOU(id);
				}
				
				getHibernateTemplate().bulkUpdate(
						"delete from ADDomainItem di where di.id=?", id);
			}
		}
	}
	
	public void removeGroupMemberships(Integer groupId) {
		getSession().createSQLQuery("delete from ADDomainUser_ADDomainGroup where groups_id=:groupid")
		.setInteger("groupid", groupId)
		.executeUpdate();
	}
	
	public void removeGroupMembershipsOfUser(Integer userId) {
		getSession().createSQLQuery("delete from ADDomainUser_ADDomainGroup where users_id=:userid")
		.setInteger("userid", userId)
		.executeUpdate();
	}
	
	@Override
	public void removeGroupMember(Integer groupId, Integer userId) {
		getSession().createSQLQuery("delete from ADDomainUser_ADDomainGroup where users_id=:userid and groups_id=:groupid")
					.setInteger("userid", userId)
					.setInteger("groupid", groupId)
					.executeUpdate();
	}

}
