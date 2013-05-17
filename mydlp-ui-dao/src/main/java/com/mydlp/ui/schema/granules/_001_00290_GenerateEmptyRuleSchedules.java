package com.mydlp.ui.schema.granules;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.mydlp.ui.domain.DailySchedule;
import com.mydlp.ui.domain.DiscoveryRule;
import com.mydlp.ui.domain.RemoteStorageRule;
import com.mydlp.ui.domain.Rule;
import com.mydlp.ui.domain.RuleSchedule;
import com.mydlp.ui.domain.ScheduleDayInterval;
import com.mydlp.ui.domain.ScheduleIntervals;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00290_GenerateEmptyRuleSchedules extends AbstractGranule {

	@SuppressWarnings("unchecked")
	@Override
	protected void callback() {
		List<Rule> list = new ArrayList<Rule>();
		DetachedCriteria criteria = DetachedCriteria.forClass(DiscoveryRule.class);
		list.addAll(getHibernateTemplate().findByCriteria(criteria));
		DetachedCriteria criteria2 = DetachedCriteria.forClass(RemoteStorageRule.class);
		list.addAll(getHibernateTemplate().findByCriteria(criteria2));
		
		for (Rule rule : list) {
			if (rule.getRuleSchedule() == null)
			{
				RuleSchedule s = generateDefaultSchedule();
				s.setRule(rule);
				rule.setRuleSchedule(s);
				getHibernateTemplate().saveOrUpdate(rule);
			}
		}
		
	}
	
	protected RuleSchedule generateDefaultSchedule()
	{
		RuleSchedule s = new RuleSchedule();
		
		DailySchedule d = new DailySchedule();
		d.setHour(0);
		
		ScheduleIntervals si = new ScheduleIntervals();
		si.setMon(generateDefaultDay());
		si.setTue(generateDefaultDay());
		si.setWed(generateDefaultDay());
		si.setThu(generateDefaultDay());
		si.setFri(generateDefaultDay());
		si.setSat(generateDefaultDay());
		si.setSun(generateDefaultDay());

		s.setSchedule(d);
		s.setScheduleIntervals(si);
		
		return s;
	}
	
	protected ScheduleDayInterval generateDefaultDay()
	{
		ScheduleDayInterval i = new ScheduleDayInterval();
		i.setHour00(true);
		i.setHour01(true);
		i.setHour02(true);
		i.setHour03(true);
		i.setHour04(true);
		i.setHour05(true);
		i.setHour06(true);
		i.setHour07(true);
		i.setHour08(true);
		i.setHour09(true);
		i.setHour10(true);
		i.setHour11(true);
		i.setHour12(true);
		i.setHour13(true);
		i.setHour14(true);
		i.setHour15(true);
		i.setHour16(true);
		i.setHour17(true);
		i.setHour18(true);
		i.setHour19(true);
		i.setHour20(true);
		i.setHour21(true);
		i.setHour22(true);
		i.setHour23(true);
		return i;
	}
}
