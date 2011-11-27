package com.mydlp.ui.schema;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.AbstractDAO;
import com.mydlp.ui.dao.DAOUtil;
import com.mydlp.ui.domain.Config;

@Service
public class SchemaManagerImpl extends AbstractDAO implements SchemaManager {
	
	private static final String SCHEMA_REVISION_KEY = "schema.revision";
	
	private static final String UPDATE_GRANULE_PACKAGE = "com.mydlp.ui.schema.granules";
	
	protected Integer getSchemaRevision() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(Config.class)
					.add(Restrictions.eq("key", SCHEMA_REVISION_KEY));
		@SuppressWarnings("unchecked")
		List<Config> list = getHibernateTemplate().findByCriteria(criteria);
		Config schemaRevision =  DAOUtil.getSingleResult(list);
		
		if (schemaRevision == null) {
			schemaRevision = new Config();
			schemaRevision.setNameKey("config." + SCHEMA_REVISION_KEY);
			schemaRevision.setKey(SCHEMA_REVISION_KEY);
			schemaRevision.setValue("0");
			getHibernateTemplate().saveOrUpdate(schemaRevision);
			return 0;
		}
		
		return Integer.parseInt(schemaRevision.getValue());
	}
	
	protected void updateSchemaRevision(Integer newRevision) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(Config.class)
					.add(Restrictions.eq("key", SCHEMA_REVISION_KEY));
		@SuppressWarnings("unchecked")
		List<Config> list = getHibernateTemplate().findByCriteria(criteria);
		Config schemaRevision =  DAOUtil.getSingleResult(list);
		
		if (schemaRevision == null) {
			schemaRevision = new Config();
			schemaRevision.setNameKey("config." + SCHEMA_REVISION_KEY);
			schemaRevision.setKey(SCHEMA_REVISION_KEY);
		}
		schemaRevision.setValue(newRevision.toString());
		getHibernateTemplate().saveOrUpdate(schemaRevision);
	}
	
	@PostConstruct
	public void init() {
		updateSchema();
	}
	
	protected Integer getGranuleRevision(String granuleClassName) {
		String granuleBaseName = granuleClassName.substring(UPDATE_GRANULE_PACKAGE.length() + 1);
		String revisionStr = granuleBaseName.substring(1,4) + granuleBaseName.substring(5,10);
		return Integer.parseInt(revisionStr);
	}
	
	protected List<String> getGranuleClassNames() {
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new TypeFilter() {
			@Override
			public boolean match(MetadataReader arg0, MetadataReaderFactory arg1) throws IOException {
				return true;
			}
		});
		List<String> granuleClassNames = new ArrayList<String>();
		for (BeanDefinition bd : scanner.findCandidateComponents(UPDATE_GRANULE_PACKAGE))
		    granuleClassNames.add(bd.getBeanClassName());
		Collections.sort(granuleClassNames);
		return granuleClassNames;
	}
	
	protected void executeGranule(String granuleClassName) {
		try {
			Object object = Class.forName(granuleClassName).newInstance();
			AbstractGranule granule = (AbstractGranule) object;
			granule.setHibernateTemplate(getHibernateTemplate());
			granule.execute();
			granule.setHibernateTemplate(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void updateSchema() {
		Integer currentRevision = getSchemaRevision();
		List <String> granuleClassNames = getGranuleClassNames();
			
		Integer lastIndex = granuleClassNames.size() - 1;
		if (currentRevision == getGranuleRevision(granuleClassNames.get(lastIndex)))
			return;
		
		for (String granuleClassName: granuleClassNames) {
			Integer granuleRevision = getGranuleRevision(granuleClassName);
			if (currentRevision < granuleRevision) {
				executeGranule(granuleClassName);
				updateSchemaRevision(granuleRevision);
				currentRevision = granuleRevision;
			}
		}
		
		System.gc();
		System.gc();
	}

}
