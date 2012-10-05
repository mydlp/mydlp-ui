package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.BundledKeywordGroup;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00035_Bundled_KeywordList extends AbstractGranule {

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
		
		BundledKeywordGroup bcg5 = new BundledKeywordGroup();
		bcg5.setFilename("sensitiveDrugs/filepath");
		bcg5.setDescriptionKey("sensitive.drugs.descriptionKey");
		bcg5.setName("Sensitive Drug Names");
		
		BundledKeywordGroup bcg6 = new BundledKeywordGroup();
		bcg6.setFilename("names/filepath");
		bcg6.setDescriptionKey("names.descriptionKey");
		bcg6.setName("Names");
		
		BundledKeywordGroup bcg7 = new BundledKeywordGroup();
		bcg7.setFilename("personalFinanceTerms/filepath");
		bcg7.setDescriptionKey("personal.finance.terms.descriptionKey");
		bcg7.setName("Personal Finance Terms");

		BundledKeywordGroup bcg8 = new BundledKeywordGroup();
		bcg8.setFilename("strategicBusinessDocuments/filepath");
		bcg8.setDescriptionKey("strategic.business.document.descriptionKey");
		bcg8.setName("Strategic Business Document Keywords");
		
		BundledKeywordGroup bcg9 = new BundledKeywordGroup();
		bcg9.setFilename("10kKeywords/filepath");
		bcg9.setDescriptionKey("sox.10k.coverPage.descriptionKey");
		bcg9.setName("10K Form Cover Page Keywords");
		
		BundledKeywordGroup bcg91 = new BundledKeywordGroup();
		bcg91.setFilename("10kKeywords/filepath");
		bcg91.setDescriptionKey("sox.10k.contentPage.descriptionKey");
		bcg91.setName("10K Form Table of Contents Keywords");
		
		BundledKeywordGroup bcg92 = new BundledKeywordGroup();
		bcg92.setFilename("10kKeywords/filepath");
		bcg92.setDescriptionKey("sox.10k.performanceGraph.descriptionKey");
		bcg92.setName("10K Form Performance Graph Keywords");
		
		BundledKeywordGroup bcg93 = new BundledKeywordGroup();
		bcg93.setFilename("10kKeywords/filepath");
		bcg93.setDescriptionKey("sox.10k.dataPage.descriptionKey");
		bcg93.setName("10K Form Financial Data Keywords");
		
		BundledKeywordGroup bcg94 = new BundledKeywordGroup();
		bcg94.setFilename("10kKeywords/filepath");
		bcg94.setDescriptionKey("sox.10k.statementPage.descriptionKey");
		bcg94.setName("10K Form Financial Statement Keywords");
		
		BundledKeywordGroup bcg10 = new BundledKeywordGroup();
		bcg10.setFilename("10qKeywords/filepath");
		bcg10.setDescriptionKey("sox.10q.coverPage.descriptionKey");
		bcg10.setName("10Q Form Cover Page Keywords");
		
		BundledKeywordGroup bcg101 = new BundledKeywordGroup();
		bcg101.setFilename("10qKeywords/filepath");
		bcg101.setDescriptionKey("sox.10q.contentPage.descriptionKey");
		bcg101.setName("10Q Form Table of Contents Keywords");
		
		BundledKeywordGroup bcg102 = new BundledKeywordGroup();
		bcg102.setFilename("10qKeywords/filepath");
		bcg102.setDescriptionKey("sox.10q.balancePage.descriptionKey");
		bcg102.setName("10Q Form Consolidated Balance Sheets Keywords");
		
		BundledKeywordGroup bcg103 = new BundledKeywordGroup();
		bcg103.setFilename("10qKeywords/filepath");
		bcg103.setDescriptionKey("sox.10q.information.descriptionKey");
		bcg103.setName("10Q Form Other Information Keywords");
		
		BundledKeywordGroup bcg11 = new BundledKeywordGroup();
		bcg11.setFilename("resumeForHrCV/filepath");
		bcg11.setDescriptionKey("resume.for.hr.cv.descriptionKey");
		bcg11.setName("Curriculum Vitae Keywords");
		
		BundledKeywordGroup bcg12 = new BundledKeywordGroup();
		bcg12.setFilename("networkPatterns/filepath");
		bcg12.setDescriptionKey("network.patterns.descriptionKey");
		bcg12.setName("Network Patterns Keywords");
		
		BundledKeywordGroup bcg13 = new BundledKeywordGroup();
		bcg13.setFilename("indiaDocumentsForm16/filepath");
		bcg13.setDescriptionKey("finance.indiaDocuments.form16.descriptionKey");
		bcg13.setName("India Form No.16");
		
		BundledKeywordGroup bcg14 = new BundledKeywordGroup();
		bcg14.setFilename("investmentRelatedKeywords/filepath");
		bcg14.setDescriptionKey("finance.investment.related.information.descriptionKey");
		bcg14.setName("Investment Informations");
		
		BundledKeywordGroup bcg15 = new BundledKeywordGroup();
		bcg15.setFilename("investmentRelatedKeywords/filepath");
		bcg15.setDescriptionKey("finance.pricing.information.descriptionKey");
		bcg15.setName("Pricing Informations");
		
		BundledKeywordGroup bcg16 = new BundledKeywordGroup();
		bcg16.setFilename("indiaDocumentsForm16/filepath");
		bcg16.setDescriptionKey("finance.indiaDocuments.form16A.descriptionKey");
		bcg16.setName("India Form No.16A");
		
		getHibernateTemplate().saveOrUpdate(bcg);
		getHibernateTemplate().saveOrUpdate(bcg1);
		getHibernateTemplate().saveOrUpdate(bcg2);
		getHibernateTemplate().saveOrUpdate(bcg3);
		getHibernateTemplate().saveOrUpdate(bcg4);
		getHibernateTemplate().saveOrUpdate(bcg5);
		getHibernateTemplate().saveOrUpdate(bcg6);
		getHibernateTemplate().saveOrUpdate(bcg7);
		getHibernateTemplate().saveOrUpdate(bcg8);
		getHibernateTemplate().saveOrUpdate(bcg9);
		getHibernateTemplate().saveOrUpdate(bcg91);
		getHibernateTemplate().saveOrUpdate(bcg92);
		getHibernateTemplate().saveOrUpdate(bcg93);
		getHibernateTemplate().saveOrUpdate(bcg94);
		getHibernateTemplate().saveOrUpdate(bcg10);
		getHibernateTemplate().saveOrUpdate(bcg101);
		getHibernateTemplate().saveOrUpdate(bcg102);
		getHibernateTemplate().saveOrUpdate(bcg103);
		getHibernateTemplate().saveOrUpdate(bcg11);
		getHibernateTemplate().saveOrUpdate(bcg12);
		getHibernateTemplate().saveOrUpdate(bcg13);
		getHibernateTemplate().saveOrUpdate(bcg14);
		getHibernateTemplate().saveOrUpdate(bcg15);
		getHibernateTemplate().saveOrUpdate(bcg16);
	}
}
