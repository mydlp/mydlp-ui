package com.mydlp.ui.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class CustomAction extends AbstractNamedEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9094121587021952530L;
	
	protected String typeKey;
	
	protected CustomActionDescription customActionDescription;

	@Column(nullable=false)
	public String getTypeKey() {
		return typeKey;
	}

	public void setTypeKey(String typeKey) {
		this.typeKey = typeKey.trim();
	}

	@OneToOne(mappedBy="coupledCustomAction", cascade={CascadeType.ALL})
	public CustomActionDescription getCustomActionDescription() {
		return customActionDescription;
	}

	public void setCustomActionDescription(
			CustomActionDescription customActionDescription) {
		this.customActionDescription = customActionDescription;
	}

}
