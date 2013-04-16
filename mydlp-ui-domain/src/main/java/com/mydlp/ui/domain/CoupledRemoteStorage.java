package com.mydlp.ui.domain;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CoupledRemoteStorage extends AbstractEntity{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2227340341614182252L;
	
	protected RemoteStorage remoteStorage;
	protected List<String> excludeList;
	
	@OneToOne
	public RemoteStorage getRemoteStorage() {
		return remoteStorage;
	}
	public void setRemoteStorage(RemoteStorage remoteStorage) {
		this.remoteStorage = remoteStorage;
	}
	
	@ElementCollection
	@CollectionTable(name="ExcludeList", joinColumns=@JoinColumn(name="coupledRemoteStorage_id"))
	@Column(name="excludeList")
	public List<String> getExcludeList() {
		return excludeList;
	}
	public void setExcludeList(List<String> excludeList) {
		this.excludeList = excludeList;
	}

}
