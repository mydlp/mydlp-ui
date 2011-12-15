package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class InformationDescription extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5484620977605947960L;
	
	protected Long threshold;
	
	protected List<InformationFeature> features;

	@Column(nullable=false)
	public Long getThreshold() {
		return threshold;
	}

	public void setThreshold(Long threshold) {
		this.threshold = threshold;
	}

	@OneToMany(cascade={CascadeType.ALL})
	public List<InformationFeature> getFeatures() {
		return features;
	}
	
	public void setFeatures(List<InformationFeature> features) {
		this.features = features;
	}

}
