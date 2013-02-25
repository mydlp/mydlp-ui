package com.mydlp.ui.domain;

import javax.persistence.Entity;

@Entity
public class RuleSchedule extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4904216712130122147L;
	
	protected ScheduleDetails sun;
	protected ScheduleDetails mon;
	protected ScheduleDetails tue;
	protected ScheduleDetails wed;
	protected ScheduleDetails thu;
	protected ScheduleDetails fri;
	protected ScheduleDetails sat;
	
	
	public ScheduleDetails getSun() {
		return sun;
	}
	public void setSun(ScheduleDetails sun) {
		this.sun = sun;
	}
	public ScheduleDetails getMon() {
		return mon;
	}
	public void setMon(ScheduleDetails mon) {
		this.mon = mon;
	}
	public ScheduleDetails getTue() {
		return tue;
	}
	public void setTue(ScheduleDetails tue) {
		this.tue = tue;
	}
	public ScheduleDetails getWed() {
		return wed;
	}
	public void setWed(ScheduleDetails wed) {
		this.wed = wed;
	}
	public ScheduleDetails getThu() {
		return thu;
	}
	public void setThu(ScheduleDetails thu) {
		this.thu = thu;
	}
	public ScheduleDetails getFri() {
		return fri;
	}
	public void setFri(ScheduleDetails fri) {
		this.fri = fri;
	}
	public ScheduleDetails getSat() {
		return sat;
	}
	public void setSat(ScheduleDetails sat) {
		this.sat = sat;
	}
}
