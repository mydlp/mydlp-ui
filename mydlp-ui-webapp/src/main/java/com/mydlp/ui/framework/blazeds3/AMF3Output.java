package com.mydlp.ui.framework.blazeds3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import com.mydlp.ui.domain.ADDomainGroup;
import com.mydlp.ui.domain.ADDomainItem;
import com.mydlp.ui.domain.ADDomainItemGroup;
import com.mydlp.ui.domain.ADDomainUser;
import com.mydlp.ui.domain.InventoryItem;
import com.mydlp.ui.domain.RuleItem;
import com.mydlp.ui.domain.RuleItemGroup;
import com.mydlp.ui.domain.RuleUserAD;

import flex.messaging.io.SerializationContext;

public class AMF3Output extends flex.messaging.io.amf.Amf3Output {
	
	//private static Logger logger = LoggerFactory.getLogger(AMF3Output.class);

	public AMF3Output(SerializationContext context) {
		super(context);
	}

	@Override
	public void writeObject(Object value) throws IOException {
		if (value instanceof RuleItem) {
			RuleItem ri = (RuleItem) value;
			if (ri.getItem() != null)
			{
				if (ri.getItem().getCoupledInventoryItem() != null)
				{
					ri.getItem().getCoupledInventoryItem().setCategory(null);
					ri.getItem().getCoupledInventoryItem().setGroup(null);
				}
			}
	    }
		else if (value instanceof RuleItemGroup) {
			RuleItemGroup rig = (RuleItemGroup) value;
			if (rig.getGroup() != null)
			{
				rig.getGroup().setCategory(null);
				rig.getGroup().setChildren(new ArrayList<InventoryItem>());
			}
	    }
		else if (value instanceof RuleUserAD) {
			RuleUserAD ru = (RuleUserAD) value;
			if (ru.getDomainItem() != null)
			{
				ru.getDomainItem().setParent(null);
				
				if (ru.getDomainItem() instanceof ADDomainItemGroup)
				{
					ADDomainItemGroup ig = (ADDomainItemGroup) ru.getDomainItem();
					ig.setChildren(new ArrayList<ADDomainItem>());
				}
				else if (ru.getDomainItem() instanceof ADDomainGroup)
				{
					ADDomainGroup g = (ADDomainGroup) ru.getDomainItem();
					g.setUsers(new HashSet<ADDomainUser>());
				}
				else if (ru.getDomainItem() instanceof ADDomainUser)
				{
					ADDomainUser u = (ADDomainUser) ru.getDomainItem();
					u.setGroups(new HashSet<ADDomainGroup>());
				}
			}
		}
	    
	    super.writeObject(value);
	}
	
}
