package com.mydlp.ui.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(nullable=true)
	public ScheduleDetails getSun() {
		return sun;
	}
	public void setSun(ScheduleDetails sun) {
		this.sun = sun;
	}
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(nullable=true)
	public ScheduleDetails getMon() {
		return mon;
	}
	public void setMon(ScheduleDetails mon) {
		this.mon = mon;
	}
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(nullable=true)
	public ScheduleDetails getTue() {
		return tue;
	}
	public void setTue(ScheduleDetails tue) {
		this.tue = tue;
	}
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(nullable=true)
	public ScheduleDetails getWed() {
		return wed;
	}
	public void setWed(ScheduleDetails wed) {
		this.wed = wed;
	}
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(nullable=true)
	public ScheduleDetails getThu() {
		return thu;
	}
	public void setThu(ScheduleDetails thu) {
		this.thu = thu;
	}
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(nullable=true)
	public ScheduleDetails getFri() {
		return fri;
	}
	public void setFri(ScheduleDetails fri) {
		this.fri = fri;
	}
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(nullable=true)
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
