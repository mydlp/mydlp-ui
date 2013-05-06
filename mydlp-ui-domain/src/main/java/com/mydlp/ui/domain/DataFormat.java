package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class DataFormat extends AbstractNamedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6972083009873003233L;
	
	protected List<MIMEType> mimeTypes;

	@OneToMany(cascade={CascadeType.ALL})
	@OrderBy("mimeType")
	public List<MIMEType> getMimeTypes() {
		return mimeTypes;
	}

	public void setMimeTypes(List<MIMEType> mimeTypes) {
		this.mimeTypes = mimeTypes;
	}

}
