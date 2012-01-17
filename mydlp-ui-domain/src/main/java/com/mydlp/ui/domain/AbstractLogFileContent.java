package com.mydlp.ui.domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractLogFileContent extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8140915893363344335L;
	
	protected String localPath;
	protected String mimeType;
	protected Long size;

	public String getLocalPath() {
		return localPath;
	}
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
}
