package com.mydlp.ui.domain;

import javax.persistence.Entity;

@Entity
public class WebServer extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8331986811939300472L;
	
	public static final String PROTO_HTTP = "HTTP";
	public static final String PROTO_HTTPS = "HTTPS";
	
	protected String proto = WebServer.PROTO_HTTP;
	
	protected String address;
	
	protected int port = 80;
	
	protected int digDepth = 1;
	
	protected String startPath;

	public String getProto() {
		return proto;
	}

	public void setProto(String proto) {
		this.proto = proto;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getDigDepth() {
		return digDepth;
	}

	public void setDigDepth(int digDepth) {
		this.digDepth = digDepth;
	}

	public String getStartPath() {
		return startPath;
	}

	public void setStartPath(String startPath) {
		this.startPath = startPath;
	}
	
	

}
