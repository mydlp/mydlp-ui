package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;

@Inheritance
@Entity
public abstract class RuleContainer extends AbstractEntity {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7443455024775710496L;
	
	
	protected List<Rule> children;
	
	@OneToMany(mappedBy="category", cascade={CascadeType.ALL})
	public List<Rule> getChildren() {
		return children;
	}
	
	public void setChildren(List<Rule> children) {
		this.children = children;
	}

}
