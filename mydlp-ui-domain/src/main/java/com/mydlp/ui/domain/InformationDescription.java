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
	private static final long serialVersionUID = 8165962590115982975L;
	
	protected List<InformationFeature> features;
	
	protected Integer distance;

	@OneToMany(cascade={CascadeType.ALL})
	public List<InformationFeature> getFeatures() {
		return features;
	}
	
	public void setFeatures(List<InformationFeature> features) {
		this.features = features;
	}

	@Column(nullable=false)
	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

}
