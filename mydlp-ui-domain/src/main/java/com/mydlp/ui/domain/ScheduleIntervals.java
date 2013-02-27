package com.mydlp.ui.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ScheduleIntervals extends AbstractEntity {

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3250860745928742036L;
	
	
	protected ScheduleDayInterval sun;
	protected ScheduleDayInterval mon;
	protected ScheduleDayInterval tue;
	protected ScheduleDayInterval wed;
	protected ScheduleDayInterval thu;
	protected ScheduleDayInterval fri;
	protected ScheduleDayInterval sat;
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(nullable=true)
	public ScheduleDayInterval getSun() {
		return sun;
	}
	public void setSun(ScheduleDayInterval sun) {
		this.sun = sun;
	}
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(nullable=true)
	public ScheduleDayInterval getMon() {
		return mon;
	}
	public void setMon(ScheduleDayInterval mon) {
		this.mon = mon;
	}
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(nullable=true)
	public ScheduleDayInterval getTue() {
		return tue;
	}
	public void setTue(ScheduleDayInterval tue) {
		this.tue = tue;
	}
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(nullable=true)
	public ScheduleDayInterval getWed() {
		return wed;
	}
	public void setWed(ScheduleDayInterval wed) {
		this.wed = wed;
	}
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(nullable=true)
	public ScheduleDayInterval getThu() {
		return thu;
	}
	public void setThu(ScheduleDayInterval thu) {
		this.thu = thu;
	}
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(nullable=true)
	public ScheduleDayInterval getFri() {
		return fri;
	}
	public void setFri(ScheduleDayInterval fri) {
		this.fri = fri;
	}
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(nullable=true)
	public ScheduleDayInterval getSat() {
		return sat;
	}
	public void setSat(ScheduleDayInterval sat) {
		this.sat = sat;
	}
}
