package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.BundledKeywordGroup;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00240_Bundled_KeywordList_2 extends AbstractGranule {

	@Override
	protected void callback() {
		BundledKeywordGroup bcg0 = new BundledKeywordGroup();
		bcg0.setFilename("chinese_lastnames.txt");
		bcg0.setNameKey("chinese.lastnames.keywordList");
		bcg0.setDescriptionKey("ChineseSurnames");
		
		BundledKeywordGroup bcg1 = new BundledKeywordGroup();
		bcg1.setFilename("taiwanese_lastnames.txt");
		bcg1.setNameKey("taiwanese.lastnames.keywordList");
		bcg1.setDescriptionKey("TaiwaneseSurnames");
		
		BundledKeywordGroup bcg2 = new BundledKeywordGroup();
		bcg2.setFilename("cities_china.txt");
		bcg2.setNameKey("cities.china.keywordList");
		bcg2.setDescriptionKey("CitiesChina");
		
		BundledKeywordGroup bcg3 = new BundledKeywordGroup();
		bcg3.setFilename("cities_taiwan.txt");
		bcg3.setNameKey("cities.taiwan.keywordList");
		bcg3.setDescriptionKey("CitiesTaiwan");
		
		BundledKeywordGroup bcg4 = new BundledKeywordGroup();
		bcg4.setFilename("cities_hongkong.txt");
		bcg4.setNameKey("cities.hongkong.keywordList");
		bcg4.setDescriptionKey("CitiesHongkong");
		
		BundledKeywordGroup bcg5 = new BundledKeywordGroup();
		bcg5.setFilename("regions_china.txt");
		bcg5.setNameKey("regions.china.keywordList");
		bcg5.setDescriptionKey("RegionsChina");
		
		BundledKeywordGroup bcg6 = new BundledKeywordGroup();
		bcg6.setFilename("regions_taiwan.txt");
		bcg6.setNameKey("regions.taiwan.keywordList");
		bcg6.setDescriptionKey("RegionsTaiwan");
		
		BundledKeywordGroup bcg7 = new BundledKeywordGroup();
		bcg7.setFilename("regions_hongkong.txt");
		bcg7.setNameKey("regions.hongkong.keywordList");
		bcg7.setDescriptionKey("RegionsHongkong");
		
		BundledKeywordGroup bcg8 = new BundledKeywordGroup();
		bcg8.setFilename("chinese_address_terms.txt");
		bcg8.setNameKey("chinese.addressTerms.keywordList");
		bcg8.setDescriptionKey("ChineseAddressTerms");
		
		getHibernateTemplate().saveOrUpdate(bcg0);
		getHibernateTemplate().saveOrUpdate(bcg1);
		getHibernateTemplate().saveOrUpdate(bcg2);
		getHibernateTemplate().saveOrUpdate(bcg3);
		getHibernateTemplate().saveOrUpdate(bcg4);
		getHibernateTemplate().saveOrUpdate(bcg5);
		getHibernateTemplate().saveOrUpdate(bcg6);
		getHibernateTemplate().saveOrUpdate(bcg7);
		getHibernateTemplate().saveOrUpdate(bcg8);
	}
}
