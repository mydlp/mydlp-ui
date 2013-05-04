package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;


@Entity
public class RegularExpressionGroup extends Argument {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6292826817752055047L;
	
	protected List<RegularExpressionGroupEntry> entries;
	protected RDBMSInformationTarget rdbmsInformationTarget;

	@OneToMany(cascade={CascadeType.ALL})
	@OrderBy("regex")
	public List<RegularExpressionGroupEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<RegularExpressionGroupEntry> entries) {
		this.entries = entries;
	}

	@OneToOne(cascade={CascadeType.ALL})
	public RDBMSInformationTarget getRdbmsInformationTarget() {
		return rdbmsInformationTarget;
	}

	public void setRdbmsInformationTarget(
			RDBMSInformationTarget rdbmsInformationTarget) {
		this.rdbmsInformationTarget = rdbmsInformationTarget;
	}

}
