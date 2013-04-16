package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.BundledKeywordGroup;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00240_Bundled_KeywordList_2 extends AbstractGranule {

	@Override
	protected void callback() {
		
		BundledKeywordGroup bcg = new BundledKeywordGroup();
		bcg.setFilename("chinese_names.txt");
		bcg.setNameKey("chinese.names.keywordList");
		bcg.setDescriptionKey("ChineseNames");
		
		BundledKeywordGroup bcg1 = new BundledKeywordGroup();
		bcg1.setFilename("chinese_surnames.txt");
		bcg1.setNameKey("chinese.surnames.keywordList");
		bcg1.setDescriptionKey("ChineseSurnames");
		
		BundledKeywordGroup bcg2 = new BundledKeywordGroup();
		bcg2.setFilename("cities_china.txt");
		bcg2.setNameKey("cities.china.keywordList");
		bcg2.setDescriptionKey("CitiesChina");
		
		BundledKeywordGroup bcg3 = new BundledKeywordGroup();
		bcg3.setFilename("cities_taiwan.txt");
		bcg3.setNameKey("cities.taiwan.keywordList");
		bcg3.setDescriptionKey("CitiesTaiwan");
		
		getHibernateTemplate().saveOrUpdate(bcg);
		getHibernateTemplate().saveOrUpdate(bcg1);
		getHibernateTemplate().saveOrUpdate(bcg2);
		getHibernateTemplate().saveOrUpdate(bcg3);
		
	}
}
