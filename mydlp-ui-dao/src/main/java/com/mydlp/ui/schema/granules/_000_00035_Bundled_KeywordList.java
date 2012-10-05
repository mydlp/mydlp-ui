package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.BundledKeywordGroup;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00035_Bundled_KeywordList extends AbstractGranule {

	@Override
	protected void callback() {
			
		BundledKeywordGroup bcg = new BundledKeywordGroup();
		bcg.setFilename("ndc/filepath");
		bcg.setNameKey("ndc.keywordList");
		bcg.setDescriptionKey("NationalDrugCodes");
		
		BundledKeywordGroup bcg1 = new BundledKeywordGroup();
		bcg1.setFilename("drugName/filepath");
		bcg1.setNameKey("drugName.keywordList");
		bcg1.setDescriptionKey("DrugNames");
		
		BundledKeywordGroup bcg2 = new BundledKeywordGroup();
		bcg2.setFilename("icd9Code/filepath");
		bcg2.setNameKey("icd9.code.keywordList");
		bcg2.setDescriptionKey("ICD9Codes");

		BundledKeywordGroup bcg3 = new BundledKeywordGroup();
		bcg3.setFilename("commonDiseases/filepath");
		bcg3.setNameKey("common.diseases.keywordList");
		bcg3.setDescriptionKey("DiseaseNames");

		BundledKeywordGroup bcg4 = new BundledKeywordGroup();
		bcg4.setFilename("sensitiveDiseases/filepath");
		bcg4.setNameKey("sensitive.diseases.keywordList");
		bcg4.setDescriptionKey("SensitiveDiseaseNames");
		
		BundledKeywordGroup bcg5 = new BundledKeywordGroup();
		bcg5.setFilename("sensitiveDrugs/filepath");
		bcg5.setNameKey("sensitive.drugs.keywordList");
		bcg5.setDescriptionKey("SensitiveDrugNames");
		
		BundledKeywordGroup bcg6 = new BundledKeywordGroup();
		bcg6.setFilename("names/filepath");
		bcg6.setNameKey("names.keywordList");
		bcg6.setDescriptionKey("Names");
		
		BundledKeywordGroup bcg7 = new BundledKeywordGroup();
		bcg7.setFilename("personalFinanceTerms/filepath");
		bcg7.setNameKey("personal.finance.terms.keywordList");
		bcg7.setDescriptionKey("PersonalFinanceTerms");

		BundledKeywordGroup bcg8 = new BundledKeywordGroup();
		bcg8.setFilename("strategicBusinessDocuments/filepath");
		bcg8.setNameKey("strategic.business.document.keywordList");
		bcg8.setDescriptionKey("StrategicBusinessDocumentKeywords");
		
		BundledKeywordGroup bcg9 = new BundledKeywordGroup();
		bcg9.setFilename("10kKeywords/filepath");
		bcg9.setNameKey("sox.10k.coverPage.keywordList");
		bcg9.setDescriptionKey("10KFormCoverPageKeywords");
		
		BundledKeywordGroup bcg91 = new BundledKeywordGroup();
		bcg91.setFilename("10kKeywords/filepath");
		bcg91.setNameKey("sox.10k.contentPage.keywordList");
		bcg91.setDescriptionKey("10KFormTableofContentsKeywords");
		
		BundledKeywordGroup bcg92 = new BundledKeywordGroup();
		bcg92.setFilename("10kKeywords/filepath");
		bcg92.setNameKey("sox.10k.performanceGraph.keywordList");
		bcg92.setDescriptionKey("10KFormPerformanceGraphKeywords");
		
		BundledKeywordGroup bcg93 = new BundledKeywordGroup();
		bcg93.setFilename("10kKeywords/filepath");
		bcg93.setNameKey("sox.10k.dataPage.keywordList");
		bcg93.setDescriptionKey("10KFormFinancialDataKeywords");
		
		BundledKeywordGroup bcg94 = new BundledKeywordGroup();
		bcg94.setFilename("10kKeywords/filepath");
		bcg94.setNameKey("sox.10k.statementPage.keywordList");
		bcg94.setDescriptionKey("10KFormFinancialStatementKeywords");
		
		BundledKeywordGroup bcg10 = new BundledKeywordGroup();
		bcg10.setFilename("10qKeywords/filepath");
		bcg10.setNameKey("sox.10q.coverPage.keywordList");
		bcg10.setDescriptionKey("10QFormCoverPageKeywords");
		
		BundledKeywordGroup bcg101 = new BundledKeywordGroup();
		bcg101.setFilename("10qKeywords/filepath");
		bcg101.setNameKey("sox.10q.contentPage.keywordList");
		bcg101.setDescriptionKey("10QFormTableofContentsKeywords");
		
		BundledKeywordGroup bcg102 = new BundledKeywordGroup();
		bcg102.setFilename("10qKeywords/filepath");
		bcg102.setNameKey("sox.10q.balancePage.keywordList");
		bcg102.setDescriptionKey("10QFormConsolidatedBalanceSheetsKeywords");
		
		BundledKeywordGroup bcg103 = new BundledKeywordGroup();
		bcg103.setFilename("10qKeywords/filepath");
		bcg103.setNameKey("sox.10q.information.keywordList");
		bcg103.setDescriptionKey("10QFormOtherInformationKeywords");
		
		BundledKeywordGroup bcg11 = new BundledKeywordGroup();
		bcg11.setFilename("resumeForHrCV/filepath");
		bcg11.setNameKey("resume.for.hr.cv.keywordList");
		bcg11.setDescriptionKey("CurriculumVitaeKeywords");
		
		BundledKeywordGroup bcg12 = new BundledKeywordGroup();
		bcg12.setFilename("networkPatterns/filepath");
		bcg12.setNameKey("network.patterns.keywordList");
		bcg12.setDescriptionKey("NetworkPatternsKeywords");
		
		BundledKeywordGroup bcg13 = new BundledKeywordGroup();
		bcg13.setFilename("indiaDocumentsForm16/filepath");
		bcg13.setNameKey("finance.indiaDocuments.form16.keywordList");
		bcg13.setDescriptionKey("IndiaFormNo.16");
		
		BundledKeywordGroup bcg14 = new BundledKeywordGroup();
		bcg14.setFilename("investmentRelatedKeywords/filepath");
		bcg14.setNameKey("finance.investment.related.information.keywordList");
		bcg14.setDescriptionKey("InvestmentInformations");
		
		BundledKeywordGroup bcg15 = new BundledKeywordGroup();
		bcg15.setFilename("investmentRelatedKeywords/filepath");
		bcg15.setNameKey("finance.pricing.information.keywordList");
		bcg15.setDescriptionKey("PricingInformations");
		
		BundledKeywordGroup bcg16 = new BundledKeywordGroup();
		bcg16.setFilename("indiaDocumentsForm16/filepath");
		bcg16.setNameKey("finance.indiaDocuments.form16A.keywordList");
		bcg16.setDescriptionKey("IndiaFormNo.16A");
		
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
