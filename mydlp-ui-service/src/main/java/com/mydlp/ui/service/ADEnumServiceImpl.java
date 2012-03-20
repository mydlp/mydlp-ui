package com.mydlp.ui.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.ADDomainDAO;
import com.mydlp.ui.domain.ADDomain;
import com.mydlp.ui.domain.ADDomainItemGroup;
import com.mydlp.ui.domain.ADDomainOU;
import com.mydlp.ui.domain.ADDomainRoot;
import com.mydlp.ui.domain.ADDomainUser;
import com.mydlp.ui.domain.ADDomainUserAlias;

@Service("adEnumService")
public class ADEnumServiceImpl implements ADEnumService {

	private static Logger logger = LoggerFactory.getLogger(ADEnumService.class);
	
	protected List<String> currentlyProcessingDomains = new LinkedList<String>();
	
	@Autowired
	protected ADDomainDAO adDomainDAO;
	
	@Async
	public void enumerate(ADDomain domain) {
		String distinguishedName = fqdnToLdapdn(domain.getDomainName());
		
		if (domain.getRoot() == null) {
			ADDomainRoot root = new ADDomainRoot();
			root.setDistinguishedName("mydlp-domain-root/" + domain.getDomainName());
			root.setDomain(domain);
			
			root = (ADDomainRoot) adDomainDAO.saveDomainItem(root);
			domain.setRoot(root);
			domain = adDomainDAO.saveDomain(domain);
		}
		
		if (currentlyProcessingDomains.contains(domain.getDomainName()))
		{
			logger.info("Enumerating already scheduled for domain.", domain.getDomainName());
			return;
		}
		
		try {
			currentlyProcessingDomains.add(domain.getDomainName());
			domain.setCurrentlyEnumerating(true);
			domain = adDomainDAO.saveDomain(domain);
			enumerateDN(domain, domain.getRoot(), distinguishedName);
		} catch (NamingException e) {
			logger.error("Error occured when enumerating AD", e);
			throw new RuntimeException(e);
		} finally {
			currentlyProcessingDomains.remove(domain.getDomainName());
			domain.setCurrentlyEnumerating(false);
			domain = adDomainDAO.saveDomain(domain);
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
	
	protected void enumerateDN(ADDomain domain, ADDomainItemGroup parent, String distinguishedName) throws NamingException {
		searchDNforUsers(domain, parent, distinguishedName);
		searchDNforOU(domain, parent, distinguishedName);
	}
	
	protected void searchDNforUsers(ADDomain domain, ADDomainItemGroup parent, String distinguishedName) throws NamingException {
		DirContext ctx = context(domain);
		SearchControls ctls = new SearchControls();
		String[] attrIDs =  { "displayName", "sAMAccountName", "distinguishedName", "proxyAddresses", "mailNickname"};
		ctls.setReturningAttributes(attrIDs);
		
		ctls.setSearchScope(SearchControls.ONELEVEL_SCOPE);

		NamingEnumeration<SearchResult> queryResults = ctx.search(distinguishedName,
				"(&(objectCategory=person)(objectClass=user)(!(isCriticalSystemObject=TRUE)))"
				, ctls);
		
		saveUsers(parent, queryResults);
	}
	
	protected void searchDNforOU(ADDomain domain, ADDomainItemGroup parent, String distinguishedName) throws NamingException {
		DirContext ctx = context(domain);
		SearchControls ctls = new SearchControls();
		String[] attrIDs =  { "name", "distinguishedName" };
		ctls.setReturningAttributes(attrIDs);
		
		ctls.setSearchScope(SearchControls.ONELEVEL_SCOPE);

		NamingEnumeration<SearchResult> queryResults = ctx.search(distinguishedName,
				"(&(objectClass=organizationalUnit)(!(isCriticalSystemObject=TRUE))(!(msExchVersion=*)))"
				, ctls);
		
		saveOUs(domain, parent, queryResults);
	}

	protected String fqdnToLdapdn(String domainName) {
		String arr[] = domainName.split("\\.");
		List<String> dcList = new ArrayList<String>(8);
		for (String s : arr) {
			dcList.add("dc=" + s);
		}
		return StringUtils.join(dcList, ",");
	}
	
	protected void saveUsers(ADDomainItemGroup parent, NamingEnumeration<SearchResult> queryResults) throws NamingException {
		
		while (queryResults.hasMoreElements())
		{
			SearchResult result = queryResults.next();
			Attributes attribs = result.getAttributes();

			if (null != attribs)
			{
				// "displayName", "sAMAccountName", "distinguishedName", "proxyAddresses","mailNickname"
				String displayName = null;
				String sAMAccountName = null;
				String distinguishedName = null;
				
				Set<String> userAliases = new TreeSet<String>();
				
				
				for (NamingEnumeration<? extends Attribute> ae = attribs.getAll(); ae.hasMoreElements();)
				{
					Attribute atr = (Attribute) ae.next();
					String attributeID = atr.getID();
					
					//for (int i = 0; i < atr.size(); i++) 
					//	System.out.println(attributeID + ": " + atr.get(i));
					
					
					if (atr.size() > 0)
					{
						if (attributeID.equals("displayName"))
							displayName = (String) atr.get(0);
						else if (attributeID.equals("sAMAccountName"))
							sAMAccountName = ((String) atr.get(0)).toLowerCase();
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
								val = val.substring(0, atSignIndex); // drop @domain.com
								userAliases.add(val);
							}
						else if (attributeID.equals("mailNickname"))
							for (int i = 0; i < atr.size(); i++) {
								String val = (String) atr.get(i);
								val = val.toLowerCase();
								userAliases.add(val);
							}
					}
				}
				
				ADDomainUser domainUser = (ADDomainUser) adDomainDAO.findByDistinguishedName(distinguishedName);
				Boolean saveFlag = false;
				
				if (domainUser == null) {
					domainUser = new ADDomainUser();
					domainUser.setParent(parent);
					domainUser.setDistinguishedName(distinguishedName);
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
				
				if (domainUser.getAliases() == null )
					aliasesToSave = new ArrayList<ADDomainUserAlias>();
				else
					aliasesToSave = domainUser.getAliases();
				
				for (ADDomainUserAlias adDomainUserAlias : aliasesToSave)
					if (!userAliases.remove(adDomainUserAlias.getUserAlias()))
					{
						aliasesToSave.remove(adDomainUserAlias);
						aliasesToDelete.add(adDomainUserAlias);
						saveFlag = true;
					}
				
				for (String aliasStr : userAliases) {
					ADDomainUserAlias uaObj = new ADDomainUserAlias();
					uaObj.setUserAlias(aliasStr);
					aliasesToSave.add(uaObj);
					saveFlag = true;
				}
				
				domainUser.setAliases(aliasesToSave);
				
				if (saveFlag)
					adDomainDAO.saveDomainItem(domainUser);
				
				for (ADDomainUserAlias adDomainUserAlias : aliasesToDelete) {
					adDomainDAO.remove(adDomainUserAlias);
				}
			}
		}
		
		
	}
	
	protected void saveOUs(ADDomain domain, ADDomainItemGroup parent, NamingEnumeration<SearchResult> queryResults) throws NamingException {
		while (queryResults.hasMoreElements())
		{
			SearchResult result = queryResults.next();
			Attributes attribs = result.getAttributes();

			if (null != attribs)
			{
				String name = null;
				String distinguishedName = null;
				
				
				for (NamingEnumeration<? extends Attribute> ae = attribs.getAll(); ae.hasMoreElements();)
				{
					Attribute atr = (Attribute) ae.next();
					String attributeID = atr.getID();
					
					for (int i = 0; i < atr.size(); i++) 
						System.out.println(attributeID + ": " + atr.get(i));
					
					
					if (atr.size() > 0)
					{
						if (attributeID.equals("name"))
							name = (String) atr.get(0);
						else if (attributeID.equals("distinguishedName"))
							distinguishedName = (String) atr.get(0);
					}
				}
				
				ADDomainOU domainOU = (ADDomainOU) adDomainDAO.findByDistinguishedName(distinguishedName);
				Boolean saveFlag = false;
				
				if (domainOU == null) {
					domainOU = new ADDomainOU();
					domainOU.setParent(parent);
					domainOU.setDistinguishedName(distinguishedName);
					saveFlag=true;
				}
				
				if (!name.equals(domainOU.getName())) {
					domainOU.setName(name);
					saveFlag = true;
				}
				
				if (saveFlag)
					domainOU = (ADDomainOU) adDomainDAO.saveDomainItem(domainOU);
				
				enumerateDN(domain, domainOU, domainOU.getDistinguishedName());
			}
		}
		
		
	}
	
}
