package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class InformationType extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5826131932804521244L;

	protected List<DataFormat> dataFormats;
	
	protected InformationDescription informationDescription;

	@ManyToMany
	public List<DataFormat> getDataFormats() {
		return dataFormats;
	}

	public void setDataFormats(List<DataFormat> dataFormats) {
		this.dataFormats = dataFormats;
	}

	@OneToOne(mappedBy="informationType", cascade={CascadeType.ALL})
	public InformationDescription getInformationDescription() {
		return informationDescription;
	}

	public void setInformationDescription(
			InformationDescription informationDescription) {
		this.informationDescription = informationDescription;
	}
	
}
