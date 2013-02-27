package com.mydlp.ui.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class RuleSchedule extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6960273780215724889L;

	protected Schedule schedule;
	
	protected ScheduleIntervals scheduleIntervals;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(nullable=false)
	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(nullable=false)
	public ScheduleIntervals getScheduleIntervals() {
		return scheduleIntervals;
	}

	public void setScheduleIntervals(ScheduleIntervals scheduleIntervals) {
		this.scheduleIntervals = scheduleIntervals;
	}
}
