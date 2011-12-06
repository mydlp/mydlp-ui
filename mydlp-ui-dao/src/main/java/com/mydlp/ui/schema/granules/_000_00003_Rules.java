package com.mydlp.ui.schema.granules;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mydlp.ui.dao.DAOUtil;
import com.mydlp.ui.domain.EndpointRule;
import com.mydlp.ui.domain.InventoryItem;
import com.mydlp.ui.domain.Item;
import com.mydlp.ui.domain.MailRule;
import com.mydlp.ui.domain.Rule;
import com.mydlp.ui.domain.RuleItem;
import com.mydlp.ui.domain.WebRule;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00003_Rules extends AbstractGranule {

	@Override
	protected void callback() {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryItem.class)
					.add(Restrictions.eq("nameKey", "inventory.networks.all"));
		@SuppressWarnings("unchecked")
		List<InventoryItem> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryItem nii = DAOUtil.getSingleResult(list);
		Item sourceItem = nii.getItem();
		
		WebRule wr = new WebRule();
		RuleItem wri1 = new RuleItem();
		wri1.setRule(wr);
		wri1.setItem(sourceItem);
		wr.setPriority(new Long(1000));
		wr.setEnabled(true);
		wr.setName("Web Rule");
		sourceItem.getCoupledRuleItems().add(wri1);
		wr.setRuleItems(new ArrayList<RuleItem>());
		wr.getRuleItems().add(wri1);
		
		MailRule mr = new MailRule();
		mr.setAction(Rule.ACTION_ARCHIVE);
		mr.setPriority(new Long(900));
		mr.setEnabled(true);
		mr.setName("Mail Rule");
		
		EndpointRule er = new EndpointRule();
		er.setPriority(new Long(8000));
		er.setEnabled(true);
		er.setName("EP Rule");
		
		getHibernateTemplate().saveOrUpdate(sourceItem);
		getHibernateTemplate().saveOrUpdate(wr);
		getHibernateTemplate().saveOrUpdate(wri1);
		getHibernateTemplate().saveOrUpdate(mr);
		getHibernateTemplate().saveOrUpdate(er);
	}

}
