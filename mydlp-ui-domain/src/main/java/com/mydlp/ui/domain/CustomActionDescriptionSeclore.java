package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CustomActionDescriptionSeclore extends CustomActionDescription {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1060347367984489122L;

	protected Integer hotFolderId;
	
	protected String activityComment;

	@Column(nullable=false)
	public Integer getHotFolderId() {
		return hotFolderId;
	}

	public void setHotFolderId(Integer hotFolderId) {
		this.hotFolderId = hotFolderId;
	}

	@Column(nullable=false)
	public String getActivityComment() {
		return activityComment;
	}

	public void setActivityComment(String activityComment) {
		this.activityComment = activityComment;
	}

}
