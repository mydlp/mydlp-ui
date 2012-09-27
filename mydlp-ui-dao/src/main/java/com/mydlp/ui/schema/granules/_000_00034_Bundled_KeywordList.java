package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.BundledKeywordGroup;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00034_Bundled_KeywordList extends AbstractGranule {

	@Override
	protected void callback() {
			
		BundledKeywordGroup bcg = new BundledKeywordGroup();
		bcg.setFilename("ndc/filepath");
		bcg.setDescriptionKey("ndc.descriptionKey");
		bcg.setName("National Drug Codes");
		
		BundledKeywordGroup bcg1 = new BundledKeywordGroup();
		bcg1.setFilename("drugName/filepath");
		bcg1.setDescriptionKey("drugName.descriptionKey");
		bcg1.setName("Drug Names");
		
		BundledKeywordGroup bcg2 = new BundledKeywordGroup();
		bcg2.setFilename("icd9Code/filepath");
		bcg2.setDescriptionKey("icd9.code.descriptionKey");
		bcg2.setName("ICD9 Codes");

		BundledKeywordGroup bcg3 = new BundledKeywordGroup();
		bcg3.setFilename("commonDiseases/filepath");
		bcg3.setDescriptionKey("common.diseases.descriptionKey");
		bcg3.setName("Disease Names");

		BundledKeywordGroup bcg4 = new BundledKeywordGroup();
		bcg4.setFilename("sensitiveDiseases/filepath");
		bcg4.setDescriptionKey("sensitive.diseases.descriptionKey");
		bcg4.setName("Sensitive Disease Names");
		
		getHibernateTemplate().saveOrUpdate(bcg);
		getHibernateTemplate().saveOrUpdate(bcg1);
		getHibernateTemplate().saveOrUpdate(bcg2);
		getHibernateTemplate().saveOrUpdate(bcg3);
		getHibernateTemplate().saveOrUpdate(bcg4);
	}
}
