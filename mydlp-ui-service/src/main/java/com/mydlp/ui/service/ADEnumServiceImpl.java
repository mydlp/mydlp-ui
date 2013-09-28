package com.mydlp.ui.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.mydlp.ui.dao.ADDomainDAO;
import com.mydlp.ui.domain.ADDomain;
import com.mydlp.ui.domain.ADDomainGroup;
import com.mydlp.ui.domain.ADDomainItem;
import com.mydlp.ui.domain.ADDomainItemGroup;
import com.mydlp.ui.domain.ADDomainOU;
import com.mydlp.ui.domain.ADDomainRoot;
import com.mydlp.ui.domain.ADDomainUser;
import com.mydlp.ui.domain.ADDomainUserAlias;
import com.mydlp.ui.service.EnumMasterService.EnumJob;

@Service("adEnumService")
public class ADEnumServiceImpl implements ADEnumService {

	private static Logger logger = LoggerFactory
			.getLogger(ADEnumServiceImpl.class);

	protected static final String AD_KEY_USER = "user";
	protected static final String AD_KEY_OU = "ou";
	protected static final String AD_KEY_GROUP = "group";

	@Autowired
	protected ADDomainDAO adDomainDAO;

	@Autowired
	@Qualifier("policyTransactionTemplate")
	protected TransactionTemplate transactionTemplate;

	@Autowired
	protected EnumMasterService enumMasterService;

	public class ADEnumJob extends EnumJob {

		protected ADEnumService adEnumService;

		protected Integer domainId;

		@Override
		public void enumerateNow() {
			adEnumService.enumerate(domainId);
		}

		@Override
		public String toString() {
			return "ADEnumService_" + domainId;
		}
	}

	@PostConstruct
	public void init() {
		adDomainDAO.finalizeAll();
	}

	@Scheduled(cron = "0 0 4 * * ?")
	public void dailySchedule() {
		for (ADDomain ad : adDomainDAO.getADDomains())
			if (ad.getId() != null)
				schedule(ad.getId());
	}

	public void schedule(Integer enumDomainId) {
		ADEnumJob adEnumJob = new ADEnumJob();
		adEnumJob.domainId = enumDomainId;
		adEnumJob.adEnumService = this;
		enumMasterService.schedule(adEnumJob);
	}

	public void enumerate(final Integer domainId) {
		enumerateFun(domainId);
	}

	public void enumerateFun(final Integer domainId) {
		ADDomain domain = null;
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(
						TransactionStatus arg0) {
					adDomainDAO.startProcess(domainId);
				}
			});

			domain = transactionTemplate
					.execute(new TransactionCallback<ADDomain>() {
						@Override
						public ADDomain doInTransaction(TransactionStatus arg0) {
							logger.info("Getting domain object.", domainId);
							ADDomain domain = adDomainDAO
									.getDomainById(domainId);
							if (domain == null) {
								logger.info(
										"Domain object is null for domainId.",
										domainId);
								return null;
							}

							logger.info("Enumerating started for domain.",
									domain.getDomainName());
							if (domain.getRoot() == null) {
								logger.info("Creating root object for domain.",
										domain.getDomainName());
								ADDomainRoot root = new ADDomainRoot();
								root.setDistinguishedName("mydlp-domain-root/"
										+ domain.getDomainName());
								root.setDomain(domain);
								root = (ADDomainRoot) adDomainDAO
										.saveDomainItem(root);
								domain.setRoot(root);
								domain = adDomainDAO.saveDomain(domain);
							}
							logger.info(
									"Last save before querying LDAP server.",
									domain.getDomainName());
							domain = (ADDomain) adDomainDAO.merge(domain);
							return domain;
						}
					});
			if (domain == null) {
				return;
			}
			Map<String, Set<String>> memberships = initMemberships();
			final Map<String, Set<Integer>> itemsToRemove = initItemsToRemove();
			logger.info("Starting querying LDAP server.",
					domain.getDomainName());
			enumerateDN(domain, domain.getRoot(),
					domain.getBaseDistinguishedName(), memberships,
					itemsToRemove);
			logger.info("Starting populating groups.", domain.getDomainName());
			enumerateMemberships(domain, memberships);
			logger.info("Removing items from database.", domain.getDomainName());
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(
						TransactionStatus arg0) {
					adDomainDAO.removeDomainItems(itemsToRemove);
				}
			});
			logger.info("Cleaning up orphan entries.", domain.getDomainName());
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(
						TransactionStatus arg0) {
					adDomainDAO.cleanupGhostEntries();
				}
			});
			logger.info("Finalizing process.", domain.getDomainName());
		} catch (Throwable e) {
			logger.error("Error occured", e);
		} finally {
			adDomainDAO.finalizeProcess(domainId);
			logger.info("Removing domain from enumeration queue list.",
					domain.getDomainName());
		}
	}

	protected Map<String, Set<Integer>> initItemsToRemove() {
		Map<String, Set<Integer>> itemsToRemove = new TreeMap<String, Set<Integer>>();
		itemsToRemove.put(AD_KEY_USER, new TreeSet<Integer>());
		itemsToRemove.put(AD_KEY_OU, new TreeSet<Integer>());
		itemsToRemove.put(AD_KEY_GROUP, new TreeSet<Integer>());
		return itemsToRemove;
	}

	protected Map<String, Set<String>> initMemberships() {
		Map<String, Set<String>> memberships = new HashMap<String, Set<String>>();
		return memberships;
	}

	protected void addToMemberships(Map<String, Set<String>> memberships,
			String userDN, String groupDN) {
		if (!memberships.containsKey(userDN)) {
			memberships.put(userDN, new HashSet<String>());
		}
		Set<String> groupDNs = memberships.get(userDN);
		groupDNs.add(groupDN);
	}

	protected void addToMemberships(Map<String, Set<String>> memberships,
			String userDN, Set<String> groups) {
		for (String groupDN : groups) {
			addToMemberships(memberships, userDN, groupDN);
		}
	}

	protected void enumerateMemberships(ADDomain domain,
			Map<String, Set<String>> memberships) {
		for (String userDN : memberships.keySet()) {
			enumerateUserMembership(domain, userDN, memberships.get(userDN));
		}
	}

	protected void enumerateUserMembership(final ADDomain domain,
			final String userDN, Set<String> groupDNs) {
		final Set<ADDomainGroup> dummy = new HashSet<ADDomainGroup>();

		final ADDomainUser userObject = transactionTemplate
				.execute(new TransactionCallback<ADDomainUser>() {
					@Override
					public ADDomainUser doInTransaction(TransactionStatus arg0) {
						ADDomainUser u = (ADDomainUser) adDomainDAO
								.findByDistinguishedName(domain, userDN);
						if (u != null)
						{
							dummy.addAll(u.getGroups());
						}
						return u;
					}
				});

		if (userObject == null) {
			logger.info("Cannot find userObject for dn '" + userDN + "'.");
			return;
		}

		Set<ADDomainGroup> userGroups = userObject.getGroups();

		for (final String groupDN : groupDNs) {
			final ADDomainGroup groupObject = transactionTemplate
					.execute(new TransactionCallback<ADDomainGroup>() {
						@Override
						public ADDomainGroup doInTransaction(
								TransactionStatus arg0) {
							return (ADDomainGroup) adDomainDAO
									.findByDistinguishedName(domain, groupDN);
						}
					});
			if (groupObject == null) {
				logger.info("Cannot find groupObject for dn '" + groupDN
						+ "'. Related membership of userObject with dn '"
						+ userDN + "' will be ignored.");
				continue;
			}

			dummy.remove(groupObject);

			if (userGroups == null || !userGroups.contains(groupObject)) {
				if (userGroups == null) {
					userGroups = new HashSet<ADDomainGroup>();
					userObject.setGroups(userGroups);
				}
				userGroups.add(groupObject);

				transactionTemplate
						.execute(new TransactionCallbackWithoutResult() {
							@Override
							protected void doInTransactionWithoutResult(
									TransactionStatus arg0) {
								adDomainDAO.saveDomainItem(userObject);
							}
						});
			}
		}

		for (final ADDomainGroup exGroup : dummy) {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(
						TransactionStatus arg0) {
					adDomainDAO.removeGroupMember(exGroup.getId(),
							userObject.getId());
				}
			});
		}
	}

	protected DirContext context(ADDomain domain) throws NamingException {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://" + domain.getServerIp() + ":389");
		env.put(Context.SECURITY_PRINCIPAL, domain.getLoginUsername());
		env.put(Context.SECURITY_CREDENTIALS, domain.getLoginPassword());
		env.put(Context.REFERRAL, "follow");
		DirContext ctx = new InitialDirContext(env);
		return ctx;
	}

	protected void enumerateDN(ADDomain domain, final ADDomainItemGroup parent,
			String distinguishedName, Map<String, Set<String>> memberships,
			final Map<String, Set<Integer>> itemsToRemove)
			throws NamingException {
		logger.info("Enumerating objects under  dn '" + distinguishedName
				+ "'.");
		List<ADDomainItem> children = transactionTemplate
				.execute(new TransactionCallback<List<ADDomainItem>>() {
					@Override
					public List<ADDomainItem> doInTransaction(
							TransactionStatus arg0) {
						return adDomainDAO.getChildrenOf(parent);
					}
				});

		final List<ADDomainItem> dummy = new ArrayList<ADDomainItem>();
		dummy.addAll(children);

		try {
			List<ADDomainItem> enumeratedUsers = searchDNforUsers(domain,
					parent, distinguishedName, memberships);
			dummy.removeAll(enumeratedUsers);
		} catch (Throwable e) {
			logger.error(
					"Error occurred when trying to enumerate users under dn: "
							+ distinguishedName, e);
		}

		try {
			List<ADDomainItem> enumeratedGroups = searchDNforGroups(domain,
					parent, distinguishedName);
			dummy.removeAll(enumeratedGroups);
		} catch (Throwable e) {
			logger.error(
					"Error occurred when trying to enumerate groups under dn: "
							+ distinguishedName, e);
		}

		try {
			List<ADDomainItem> enumeratedOUs = searchDNforOU(domain, parent,
					distinguishedName, memberships, itemsToRemove);
			dummy.removeAll(enumeratedOUs);
		} catch (Throwable e) {
			logger.error(
					"Error occurred when trying to enumerate organizational units under dn: "
							+ distinguishedName, e);
		}

		{
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(
						TransactionStatus arg0) {
					for (ADDomainItem adDomainItem : dummy) {
						try {
							removeDomainItem(adDomainItem, itemsToRemove);
							adDomainDAO.saveDomainItem(parent);
						} catch (UncategorizedSQLException e) {
							logger.error(
									"Does not removing ADDomainItem, because item is probably used in inventory: ",
									adDomainItem.getId());
						} catch (Throwable e) {
							logger.error(
									"Error occurred when trying to remove domain item",
									e);
						}
					}
				}
			});
		}

	}

	protected void removeDomainItem(ADDomainItem adDomainItem,
			Map<String, Set<Integer>> itemsToRemove) {
		Set<Integer> groupSet = null;
		if (adDomainItem instanceof ADDomainUser) {
			groupSet = itemsToRemove.get(AD_KEY_USER);
		} else if (adDomainItem instanceof ADDomainGroup) {
			groupSet = itemsToRemove.get(AD_KEY_GROUP);
		} else if (adDomainItem instanceof ADDomainOU) {
			groupSet = itemsToRemove.get(AD_KEY_OU);
		}

		if (groupSet != null) {
			groupSet.add(adDomainItem.getId());
		} else {
			logger.error("Cannot remove domain category for item. Clazz: "
					+ adDomainItem.getClass().getSimpleName());
		}
	}

	protected Name strToName(String distinguishedName)
			throws InvalidNameException {
		String escapedDistinguishedName = new String(distinguishedName);
		Name composite = new CompositeName().add(escapedDistinguishedName);
		return composite;
	}

	protected List<ADDomainItem> searchDNforUsers(ADDomain domain,
			ADDomainItemGroup parent, String distinguishedName,
			Map<String, Set<String>> memberships) throws NamingException {
		DirContext ctx = context(domain);
		SearchControls ctls = new SearchControls();
		String[] attrIDs = { "displayName", "sAMAccountName",
				"distinguishedName", "proxyAddresses", "mailNickname",
				"memberOf" };
		ctls.setReturningAttributes(attrIDs);

		ctls.setSearchScope(SearchControls.ONELEVEL_SCOPE);
		NamingEnumeration<SearchResult> queryResults = ctx.search(
				strToName(distinguishedName),
				"(&(objectCategory=person)(objectClass=user))", ctls);
		return saveUsers(domain, parent, queryResults, memberships);
	}

	protected List<ADDomainItem> searchDNforOU(ADDomain domain,
			ADDomainItemGroup parent, String distinguishedName,
			Map<String, Set<String>> memberships,
			Map<String, Set<Integer>> itemsToRemove) throws NamingException {
		DirContext ctx = context(domain);
		SearchControls ctls = new SearchControls();
		String[] attrIDs = { "name", "distinguishedName" };
		ctls.setReturningAttributes(attrIDs);

		ctls.setSearchScope(SearchControls.ONELEVEL_SCOPE);

		NamingEnumeration<SearchResult> queryResults = ctx
				.search(strToName(distinguishedName),
						"(&(objectClass=organizationalUnit)(!(isCriticalSystemObject=TRUE))(!(msExchVersion=*)))",
						ctls);

		return saveOUs(domain, parent, queryResults, memberships, itemsToRemove);
	}

	protected List<ADDomainItem> searchDNforGroups(ADDomain domain,
			ADDomainItemGroup parent, String distinguishedName)
			throws NamingException {
		DirContext ctx = context(domain);
		SearchControls ctls = new SearchControls();
		String[] attrIDs = { "name", "distinguishedName" };
		ctls.setReturningAttributes(attrIDs);

		ctls.setSearchScope(SearchControls.ONELEVEL_SCOPE);

		NamingEnumeration<SearchResult> queryResults = ctx.search(
				strToName(distinguishedName), "(&(objectClass=group))", ctls);

		return saveGroups(domain, parent, queryResults);
	}

	protected List<ADDomainItem> saveUsers(final ADDomain domain,
			ADDomainItemGroup parent,
			NamingEnumeration<SearchResult> queryResults,
			Map<String, Set<String>> memberships) throws NamingException {

		List<ADDomainItem> resultList = new ArrayList<ADDomainItem>();

		while (queryResults.hasMoreElements()) {
			SearchResult result = queryResults.next();
			Attributes attribs = result.getAttributes();

			if (null != attribs) {
				// "displayName", "sAMAccountName", "distinguishedName",
				// "proxyAddresses","mailNickname","memberOf"
				String displayName = "";
				String sAMAccountName = "";
				String distinguishedName = "";

				Set<String> userAliases = new HashSet<String>();
				Set<String> groups = new HashSet<String>();

				for (NamingEnumeration<? extends Attribute> ae = attribs
						.getAll(); ae.hasMoreElements();) {
					Attribute atr = (Attribute) ae.next();
					String attributeID = atr.getID();

					// for (int i = 0; i < atr.size(); i++)
					// System.out.println(attributeID + ": " + atr.get(i));

					if (atr.size() > 0) {
						if (attributeID.equals("displayName"))
							displayName = (String) atr.get(0);
						else if (attributeID.equals("sAMAccountName"))
							sAMAccountName = ((String) atr.get(0))
									.toLowerCase();
						else if (attributeID.equals("distinguishedName"))
							distinguishedName = (String) atr.get(0);
						else if (attributeID.equals("proxyAddresses"))
							for (int i = 0; i < atr.size(); i++) {
								String val = (String) atr.get(i);
								val = val.toLowerCase();
								if (!val.startsWith("smtp:"))
									continue;
								val = val.substring(5); // Drop smtp:
								int atSignIndex = val.indexOf('@');
								val = val.substring(0, atSignIndex); // drop
																		// @domain.com
								userAliases.add(val);
							}
						else if (attributeID.equals("mailNickname"))
							for (int i = 0; i < atr.size(); i++) {
								String val = (String) atr.get(i);
								val = val.toLowerCase();
								userAliases.add(val);
							}
						else if (attributeID.equals("memberOf"))
							for (int i = 0; i < atr.size(); i++)
								groups.add((String) atr.get(i));
					}
				}

				ADDomainUser domainUser = null;
				{
					final String distinguishedNameF = distinguishedName;
					domainUser = (ADDomainUser) transactionTemplate
							.execute(new TransactionCallback<ADDomainItem>() {
								@Override
								public ADDomainItem doInTransaction(
										TransactionStatus arg0) {
									return adDomainDAO.findByDistinguishedName(
											domain, distinguishedNameF);
								}
							});
				}
				Boolean saveFlag = false;

				if (domainUser == null) {
					domainUser = new ADDomainUser();
					domainUser.setParent(parent);
					domainUser.setDistinguishedName(distinguishedName);
					domainUser.setDomain(domain);
					saveFlag = true;
				}

				if (!displayName.equals(domainUser.getDisplayName())) {
					domainUser.setDisplayName(displayName);
					saveFlag = true;
				}

				if (!sAMAccountName.equals(domainUser.getsAMAccountName())) {
					domainUser.setsAMAccountName(sAMAccountName);
					saveFlag = true;
				}

				userAliases.remove(sAMAccountName);

				List<ADDomainUserAlias> aliasesToSave = null;
				List<ADDomainUserAlias> aliasesToDelete = new ArrayList<ADDomainUserAlias>();
				if (domainUser.getAliases() == null)
					aliasesToSave = new ArrayList<ADDomainUserAlias>();
				else
					aliasesToSave = domainUser.getAliases();

				for (ADDomainUserAlias adDomainUserAlias : aliasesToSave) {
					if (!userAliases.remove(adDomainUserAlias.getUserAlias())) {
						aliasesToDelete.add(adDomainUserAlias);
						saveFlag = true;
					}
				}
				
				aliasesToSave.removeAll(aliasesToDelete);

				for (String aliasStr : userAliases) {
					ADDomainUserAlias uaObj = new ADDomainUserAlias();
					uaObj.setUserAlias(aliasStr);
					aliasesToSave.add(uaObj);
					saveFlag = true;
				}
				domainUser.setAliases(aliasesToSave);

				{
					final Boolean saveFlagF = saveFlag;
					final ADDomainUser domainUserF = domainUser;
					final List<ADDomainUserAlias> aliasesToDeleteF = aliasesToDelete;
					transactionTemplate
							.execute(new TransactionCallbackWithoutResult() {
								@Override
								protected void doInTransactionWithoutResult(
										TransactionStatus arg0) {
									if (saveFlagF)
										adDomainDAO.saveDomainItem(domainUserF);
									for (ADDomainUserAlias adDomainUserAlias : aliasesToDeleteF) {
										adDomainDAO.remove(adDomainUserAlias);
									}
								}
							});
				}

				addToMemberships(memberships, distinguishedName, groups);
				resultList.add(domainUser);
			}
		}

		return resultList;
	}

	protected List<ADDomainItem> saveOUs(final ADDomain domain,
			ADDomainItemGroup parent,
			NamingEnumeration<SearchResult> queryResults,
			Map<String, Set<String>> memberships,
			Map<String, Set<Integer>> itemsToRemove) throws NamingException {
		List<ADDomainItem> resultList = new ArrayList<ADDomainItem>();

		while (queryResults.hasMoreElements()) {
			SearchResult result = queryResults.next();
			Attributes attribs = result.getAttributes();

			if (null != attribs) {
				String name = "";
				String distinguishedName = "";

				for (NamingEnumeration<? extends Attribute> ae = attribs
						.getAll(); ae.hasMoreElements();) {
					Attribute atr = (Attribute) ae.next();
					String attributeID = atr.getID();

					// for (int i = 0; i < atr.size(); i++)
					// System.out.println(attributeID + ": " + atr.get(i));

					if (atr.size() > 0) {
						if (attributeID.equals("name"))
							name = (String) atr.get(0);
						else if (attributeID.equals("distinguishedName"))
							distinguishedName = (String) atr.get(0);
					}
				}

				ADDomainOU domainOU = null;
				{
					final String distinguishedNameF = distinguishedName;
					domainOU = (ADDomainOU) transactionTemplate
							.execute(new TransactionCallback<ADDomainItem>() {
								@Override
								public ADDomainItem doInTransaction(
										TransactionStatus arg0) {
									return adDomainDAO.findByDistinguishedName(
											domain, distinguishedNameF);
								}
							});
				}
				Boolean saveFlag = false;

				if (domainOU == null) {
					domainOU = new ADDomainOU();
					domainOU.setParent(parent);
					domainOU.setDistinguishedName(distinguishedName);
					domainOU.setDomain(domain);
					saveFlag = true;
				}

				if (!name.equals(domainOU.getName())) {
					domainOU.setName(name);
					saveFlag = true;
				}

				if (saveFlag) {
					final ADDomainOU domainOUF = domainOU;
					domainOU = (ADDomainOU) transactionTemplate
							.execute(new TransactionCallback<ADDomainItem>() {
								@Override
								public ADDomainItem doInTransaction(
										TransactionStatus arg0) {
									return adDomainDAO
											.saveDomainItem(domainOUF);
								}
							});
				}

				enumerateDN(domain, domainOU, domainOU.getDistinguishedName(),
						memberships, itemsToRemove);
				resultList.add(domainOU);
			}
		}

		return resultList;
	}

	protected List<ADDomainItem> saveGroups(final ADDomain domain,
			ADDomainItemGroup parent,
			NamingEnumeration<SearchResult> queryResults)
			throws NamingException {
		List<ADDomainItem> resultList = new ArrayList<ADDomainItem>();

		while (queryResults.hasMoreElements()) {
			SearchResult result = queryResults.next();
			Attributes attribs = result.getAttributes();

			if (null != attribs) {
				String name = "";
				String distinguishedName = "";

				for (NamingEnumeration<? extends Attribute> ae = attribs
						.getAll(); ae.hasMoreElements();) {
					Attribute atr = (Attribute) ae.next();
					String attributeID = atr.getID();

					// for (int i = 0; i < atr.size(); i++)
					// System.out.println(attributeID + ": " + atr.get(i));

					if (atr.size() > 0) {
						if (attributeID.equals("name"))
							name = (String) atr.get(0);
						else if (attributeID.equals("distinguishedName"))
							distinguishedName = (String) atr.get(0);
					}
				}

				ADDomainGroup domainGroup = null;
				{
					final String distinguishedNameF = distinguishedName;
					domainGroup = (ADDomainGroup) transactionTemplate
							.execute(new TransactionCallback<ADDomainItem>() {
								@Override
								public ADDomainItem doInTransaction(
										TransactionStatus arg0) {
									return adDomainDAO.findByDistinguishedName(
											domain, distinguishedNameF);
								}
							});
				}
				Boolean saveFlag = false;

				if (domainGroup == null) {
					domainGroup = new ADDomainGroup();
					domainGroup.setParent(parent);
					domainGroup.setDistinguishedName(distinguishedName);
					domainGroup.setDomain(domain);
					saveFlag = true;
				}

				if (!name.equals(domainGroup.getName())) {
					domainGroup.setName(name);
					saveFlag = true;
				}

				if (saveFlag) {
					final ADDomainGroup domainGroupF = domainGroup;
					domainGroup = (ADDomainGroup) transactionTemplate
							.execute(new TransactionCallback<ADDomainItem>() {
								@Override
								public ADDomainItem doInTransaction(
										TransactionStatus arg0) {
									return adDomainDAO
											.saveDomainItem(domainGroupF);
								}
							});
				}

				resultList.add(domainGroup);
			}
		}

		return resultList;
	}

	@Override
	public String testConnection(ADDomain domain) {
		DirContext ctx = null;
		NamingEnumeration<SearchResult> queryResults = null;
		try {
			ctx = context(domain);
			SearchControls ctls = new SearchControls();
			String[] attrIDs = { "name", "distinguishedName" };
			ctls.setReturningAttributes(attrIDs);
			ctls.setSearchScope(SearchControls.ONELEVEL_SCOPE);
			queryResults = ctx
					.search(strToName(domain.getBaseDistinguishedName()),
							"(&(objectClass=organizationalUnit)(!(isCriticalSystemObject=TRUE))(!(msExchVersion=*)))",
							ctls);
			return "OK";
		} catch (NamingException e) {
			logger.error("Error occured when connecting to LDAP server.", e);
			return e.toString();
		} catch (Throwable e) {
			logger.error("Error occured when connecting to LDAP server.", e);
			return e.toString();
		} finally {
			try {
				if (queryResults != null)
					queryResults.close();
				if (ctx != null)
					ctx.close();
			} catch (Throwable e) {
				logger.error("Error occured when closing contexts.", e);
			}
		}
	}

}
