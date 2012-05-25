package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class InformationDescription extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8165962590115982975L;
	
	protected List<InformationFeature> features;

	@OneToMany(cascade={CascadeType.ALL})
	public List<InformationFeature> getFeatures() {
		return features;
	}
	
	public void setFeatures(List<InformationFeature> features) {
		this.features = features;
	}

}
