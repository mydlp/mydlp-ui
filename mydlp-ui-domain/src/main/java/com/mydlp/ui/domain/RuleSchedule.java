package com.mydlp.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class RuleSchedule extends AbstractEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8613217283851321246L;
	
	protected ScheduleDetails sun;
	protected ScheduleDetails mon;
	protected ScheduleDetails tue;
	protected ScheduleDetails wed;
	protected ScheduleDetails thu;
	protected ScheduleDetails fri;
	protected ScheduleDetails sat;
	
	protected Boolean isDaily;
	protected int startHour;
	protected String startDay;
	
	
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
	public Boolean getIsDaily() {
		return isDaily;
	}
	public void setIsDaily(Boolean isDaily) {
		this.isDaily = isDaily;
	}
	public int getStartHour() {
		return startHour;
	}
	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}
	
	@Column(nullable=true)
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
}
