package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class WeeklySchedule extends Schedule {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3125595445264878652L;
	
	
	protected Boolean sun;
	protected Boolean mon;
	protected Boolean tue;
	protected Boolean wed;
	protected Boolean thu;
	protected Boolean fri;
	protected Boolean sat;
	
	@Column(nullable=true)
	public Boolean getSun() {
		return sun;
	}
	public void setSun(Boolean sun) {
		this.sun = sun;
	}
	
	@Column(nullable=true)
	public Boolean getMon() {
		return mon;
	}
	public void setMon(Boolean mon) {
		this.mon = mon;
	}
	
	@Column(nullable=true)
	public Boolean getTue() {
		return tue;
	}
	public void setTue(Boolean tue) {
		this.tue = tue;
	}
	
	@Column(nullable=true)
	public Boolean getWed() {
		return wed;
	}
	public void setWed(Boolean wed) {
		this.wed = wed;
	}
	
	@Column(nullable=true)
	public Boolean getThu() {
		return thu;
	}
	public void setThu(Boolean thu) {
		this.thu = thu;
	}
	
	@Column(nullable=true)
	public Boolean getFri() {
		return fri;
	}
	public void setFri(Boolean fri) {
		this.fri = fri;
	}
	
	@Column(nullable=true)
	public Boolean getSat() {
		return sat;
	}
	public void setSat(Boolean sat) {
		this.sat = sat;
	}
	
}