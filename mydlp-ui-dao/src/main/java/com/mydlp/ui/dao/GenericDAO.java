package com.mydlp.ui.dao;

import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.AbstractNamedEntity;

public interface GenericDAO {

	public AbstractEntity save(AbstractEntity i);
	
	public void remove(AbstractEntity i);

	public AbstractEntity merge(AbstractEntity entity);
	
	public void remove(String entityName, Integer id);
	
	public Boolean isObjectWithNameExists(AbstractNamedEntity namedEntity);
}
