package com.mydlp.ui.schema;

import org.springframework.orm.hibernate3.HibernateTemplate;


public abstract class AbstractGranule{
	
	protected HibernateTemplate hibernateTemplate;
	
	public void execute() {
		callback();
	}

	protected abstract void callback();

	protected HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	
}
