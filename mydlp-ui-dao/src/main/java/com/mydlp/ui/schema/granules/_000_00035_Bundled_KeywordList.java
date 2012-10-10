package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.BundledKeywordGroup;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00035_Bundled_KeywordList extends AbstractGranule {

	@Override
	protected void callback() {
			
		BundledKeywordGroup bcg = new BundledKeywordGroup();
		bcg.setFilename("national_drug_code.txt");
		bcg.setNameKey("ndc.keywordList");
		bcg.setDescriptionKey("NationalDrugCodes");
		
		BundledKeywordGroup bcg1 = new BundledKeywordGroup();
		bcg1.setFilename("drug_names.txt");
		bcg1.setNameKey("drugName.keywordList");
		bcg1.setDescriptionKey("DrugNames");
		
		BundledKeywordGroup bcg2 = new BundledKeywordGroup();
		bcg2.setFilename("icd9_codes.txt");
		bcg2.setNameKey("icd9.code.keywordList");
		bcg2.setDescriptionKey("ICD9Codes");

		BundledKeywordGroup bcg3 = new BundledKeywordGroup();
		bcg3.setFilename("disease_names.txt");
		bcg3.setNameKey("common.diseases.keywordList");
		bcg3.setDescriptionKey("DiseaseNames");

		BundledKeywordGroup bcg4 = new BundledKeywordGroup();
		bcg4.setFilename("sensitive_diseases.txt");
		bcg4.setNameKey("sensitive.diseases.keywordList");
		bcg4.setDescriptionKey("SensitiveDiseaseNames");
		
		BundledKeywordGroup bcg5 = new BundledKeywordGroup();
		bcg5.setFilename("sensitive_drugs.txt");
		bcg5.setNameKey("sensitive.drugs.keywordList");
		bcg5.setDescriptionKey("SensitiveDrugNames");
		
		BundledKeywordGroup bcg6 = new BundledKeywordGroup();
		bcg6.setFilename("names.txt");
		bcg6.setNameKey("names.keywordList");
		bcg6.setDescriptionKey("Names");
		
		BundledKeywordGroup bcg7 = new BundledKeywordGroup();
		bcg7.setFilename("personal_finance_terms.txt");
		bcg7.setNameKey("personal.finance.terms.keywordList");
		bcg7.setDescriptionKey("PersonalFinanceTerms");

		BundledKeywordGroup bcg8 = new BundledKeywordGroup();
		bcg8.setFilename("strategic_business_documents.txt");
		bcg8.setNameKey("strategic.business.document.keywordList");
		bcg8.setDescriptionKey("StrategicBusinessDocumentKeywords");
		
		BundledKeywordGroup bcg9 = new BundledKeywordGroup();
		bcg9.setFilename("10k_cover.txt");
		bcg9.setNameKey("sox.10k.coverPage.keywordList");
		bcg9.setDescriptionKey("10KFormCoverPageKeywords");
		
		BundledKeywordGroup bcg91 = new BundledKeywordGroup();
		bcg91.setFilename("10k_content.txt");
		bcg91.setNameKey("sox.10k.contentPage.keywordList");
		bcg91.setDescriptionKey("10KFormTableofContentsKeywords");
		
		BundledKeywordGroup bcg92 = new BundledKeywordGroup();
		bcg92.setFilename("10k_performanceGraph.txt");
		bcg92.setNameKey("sox.10k.performanceGraph.keywordList");
		bcg92.setDescriptionKey("10KFormPerformanceGraphKeywords");
		
		BundledKeywordGroup bcg93 = new BundledKeywordGroup();
		bcg93.setFilename("10k_financial_data.txt");
		bcg93.setNameKey("sox.10k.dataPage.keywordList");
		bcg93.setDescriptionKey("10KFormFinancialDataKeywords");
		
		BundledKeywordGroup bcg94 = new BundledKeywordGroup();
		bcg94.setFilename("10k_financial_statement.txt");
		bcg94.setNameKey("sox.10k.statementPage.keywordList");
		bcg94.setDescriptionKey("10KFormFinancialStatementKeywords");
		
		BundledKeywordGroup bcg10 = new BundledKeywordGroup();
		bcg10.setFilename("10q_cover.txt");
		bcg10.setNameKey("sox.10q.coverPage.keywordList");
		bcg10.setDescriptionKey("10QFormCoverPageKeywords");
		
		BundledKeywordGroup bcg101 = new BundledKeywordGroup();
		bcg101.setFilename("10q_content.txt");
		bcg101.setNameKey("sox.10q.contentPage.keywordList");
		bcg101.setDescriptionKey("10QFormTableofContentsKeywords");
		
		BundledKeywordGroup bcg102 = new BundledKeywordGroup();
		bcg102.setFilename("10q_consolidated.txt");
		bcg102.setNameKey("sox.10q.balancePage.keywordList");
		bcg102.setDescriptionKey("10QFormConsolidatedBalanceSheetsKeywords");
		
		BundledKeywordGroup bcg103 = new BundledKeywordGroup();
		bcg103.setFilename("10q_information.txt");
		bcg103.setNameKey("sox.10q.information.keywordList");
		bcg103.setDescriptionKey("10QFormOtherInformationKeywords");
		
		BundledKeywordGroup bcg11 = new BundledKeywordGroup();
		bcg11.setFilename("cv.txt");
		bcg11.setNameKey("resume.for.hr.cv.keywordList");
		bcg11.setDescriptionKey("CurriculumVitaeKeywords");
		
		BundledKeywordGroup bcg12 = new BundledKeywordGroup();
		bcg12.setFilename("network_terms.txt");
		bcg12.setNameKey("network.patterns.keywordList");
		bcg12.setDescriptionKey("NetworkPatternsKeywords");
		
		BundledKeywordGroup bcg13 = new BundledKeywordGroup();
		bcg13.setFilename("india_form16.txt");
		bcg13.setNameKey("finance.indiaDocuments.form16.keywordList");
		bcg13.setDescriptionKey("IndiaFormNo.16");
		
		BundledKeywordGroup bcg14 = new BundledKeywordGroup();
		bcg14.setFilename("investment_finance_terms.txt");
		bcg14.setNameKey("finance.investment.related.information.keywordList");
		bcg14.setDescriptionKey("InvestmentInformations");
		
		BundledKeywordGroup bcg15 = new BundledKeywordGroup();
		bcg15.setFilename("pricing_terms.txt");
		bcg15.setNameKey("finance.pricing.information.keywordList");
		bcg15.setDescriptionKey("PricingInformations");
		
		BundledKeywordGroup bcg16 = new BundledKeywordGroup();
		bcg16.setFilename("india_form16A.txt");
		bcg16.setNameKey("finance.indiaDocuments.form16A.keywordList");
		bcg16.setDescriptionKey("IndiaFormNo.16A");
		
		BundledKeywordGroup bcg17 = new BundledKeywordGroup();
		bcg17.setFilename("surnames.txt");
		bcg17.setNameKey("surnames.keywordList");
		bcg17.setDescriptionKey("surnames");
		
		BundledKeywordGroup bcg18 = new BundledKeywordGroup();
		bcg18.setFilename("names_indian.txt");
		bcg18.setNameKey("IndianNames.keywordList");
		bcg18.setDescriptionKey("indian.names");
		
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
		getHibernateTemplate().saveOrUpdate(bcg17);
		getHibernateTemplate().saveOrUpdate(bcg18);
	}
}
