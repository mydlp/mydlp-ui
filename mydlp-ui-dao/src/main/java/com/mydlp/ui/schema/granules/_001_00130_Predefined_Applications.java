package com.mydlp.ui.schema.granules;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mydlp.ui.dao.DAOUtil;
import com.mydlp.ui.domain.ApplicationName;
import com.mydlp.ui.domain.InventoryCategory;
import com.mydlp.ui.domain.InventoryItem;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00130_Predefined_Applications extends AbstractGranule {

	protected void generateApplicationNames(InventoryCategory inventoryCategory, String appName, String nameKey)
	{
		ApplicationName a = new ApplicationName();
		a.setDestinationString(appName);
		InventoryItem ii = new InventoryItem();
		ii.setNameKey(nameKey);
		ii.setItem(a);
		ii.setCategory(inventoryCategory);
		
		getHibernateTemplate().saveOrUpdate(a);
		getHibernateTemplate().saveOrUpdate(ii);
	}
	
	@Override
	protected void callback() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.destinations"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory destinations = DAOUtil.getSingleResult(list);
		
		InventoryCategory appNames = new InventoryCategory();
		appNames.setNameKey("inventory.destinations.appNames");
		appNames.setEditable(false);
		appNames.setCategory(destinations);
		
		InventoryCategory msOffice = new InventoryCategory();
		msOffice.setNameKey("inventory.destinations.appNames.msOffice");
		msOffice.setEditable(false);
		msOffice.setCategory(appNames);
		
		generateApplicationNames(msOffice, "excel.exe", "inventory.destinations.appName.excel");
		generateApplicationNames(msOffice, "msaccess.exe", "inventory.destinations.appName.msaccess");
		generateApplicationNames(msOffice, "mspub.exe", "inventory.destinations.appName.mspub");
		generateApplicationNames(msOffice, "notepad.exe", "inventory.destinations.appName.notepad");
		generateApplicationNames(msOffice, "powerpnt.exe", "inventory.destinations.appName.powerpnt");
		generateApplicationNames(msOffice, "outlook.exe", "inventory.destinations.appName.outlook");
		generateApplicationNames(msOffice, "onenote.exe", "inventory.destinations.appName.onenote");
		generateApplicationNames(msOffice, "onenotem.exe", "inventory.destinations.appName.onenotem");
		generateApplicationNames(msOffice, "winword.exe", "inventory.destinations.appName.winword");
		
		InventoryCategory pdfReaders = new InventoryCategory();
		pdfReaders.setNameKey("inventory.destinations.appNames.pdfViewers");
		pdfReaders.setEditable(false);
		pdfReaders.setCategory(appNames);
		
		generateApplicationNames(pdfReaders, "AcroRd32.exe", "inventory.destinations.appName.acrord");
		generateApplicationNames(pdfReaders, "coolpdf.exe", "inventory.destinations.appName.coolpdf");
		generateApplicationNames(pdfReaders, "pdfquickview.exe", "inventory.destinations.appName.pdfquickview");
		generateApplicationNames(pdfReaders, "NitroPDF.exe", "inventory.destinations.appName.nitropdf");
		generateApplicationNames(pdfReaders, "pdfvista.exe", "inventory.destinations.appName.pdfvista");
		generateApplicationNames(pdfReaders, "FOXITR~1.EXE", "inventory.destinations.appName.foxitr");
		
		InventoryCategory designApplications = new InventoryCategory();
		designApplications.setNameKey("inventory.destinations.appNames.designApps");
		designApplications.setEditable(false);
		designApplications.setCategory(appNames);
		
		generateApplicationNames(designApplications, "acad.exe", "inventory.destinations.appName.acad");
		generateApplicationNames(designApplications, "ArchiCAD.exe", "inventory.destinations.appName.archicad");
		generateApplicationNames(designApplications, "CATIAENV.exe", "inventory.destinations.appName.catiaenv");
		
		InventoryCategory browsers = new InventoryCategory();
		browsers.setNameKey("inventory.destinations.appNames.browsers");
		browsers.setEditable(false);
		browsers.setCategory(appNames);
		
		generateApplicationNames(browsers, "chrome.exe", "inventory.destinations.appName.chrome");
		generateApplicationNames(browsers, "firefox.exe", "inventory.destinations.appName.firefox");
		generateApplicationNames(browsers, "iexplorer.exe", "inventory.destinations.appName.iexplorer");
		
		getHibernateTemplate().saveOrUpdate(msOffice);
		getHibernateTemplate().saveOrUpdate(pdfReaders);
		getHibernateTemplate().saveOrUpdate(designApplications);
		getHibernateTemplate().saveOrUpdate(browsers);
		getHibernateTemplate().saveOrUpdate(appNames);
		getHibernateTemplate().saveOrUpdate(destinations);
	}

}
