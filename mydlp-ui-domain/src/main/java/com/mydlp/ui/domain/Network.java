package com.mydlp.ui.domain;

import javax.persistence.Entity;

@Entity
public class Network extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3817078775013482909L;

	protected Long ipBase;
	
	protected Long ipMask;

	public Long getIpBase() {
		return ipBase;
	}

	public void setIpBase(Long ipBase) {
		this.ipBase = ipBase;
	}

	public Long getIpMask() {
		return ipMask;
	}

	public void setIpMask(Long ipMask) {
		this.ipMask = ipMask;
	}
}
