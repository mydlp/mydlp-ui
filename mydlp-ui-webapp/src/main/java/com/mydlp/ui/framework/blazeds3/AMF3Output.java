package com.mydlp.ui.framework.blazeds3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import com.mydlp.ui.domain.ADDomain;
import com.mydlp.ui.domain.ADDomainGroup;
import com.mydlp.ui.domain.ADDomainItem;
import com.mydlp.ui.domain.ADDomainItemGroup;
import com.mydlp.ui.domain.ADDomainUser;
import com.mydlp.ui.domain.ADDomainUserAlias;
import com.mydlp.ui.domain.DataFormat;
import com.mydlp.ui.domain.InformationType;
import com.mydlp.ui.domain.InventoryCategory;
import com.mydlp.ui.domain.InventoryGroup;
import com.mydlp.ui.domain.InventoryItem;
import com.mydlp.ui.domain.MIMEType;
import com.mydlp.ui.domain.Rule;
import com.mydlp.ui.domain.RuleItem;
import com.mydlp.ui.domain.RuleItemGroup;
import com.mydlp.ui.domain.RuleUserAD;

import flex.messaging.io.SerializationContext;

public class AMF3Output extends flex.messaging.io.amf.Amf3Output {
	
	//private static Logger logger = LoggerFactory.getLogger(AMF3Output.class);

	public AMF3Output(SerializationContext context) {
		super(context);
	}
	
	protected void dropRedundant(Object value)
	{
		if (value == null)
		{
			return;
		}
		if (value instanceof Collection<?>) { 
			Collection<?> c = (Collection<?>) value;
			for (Object i : c) {
				dropRedundant(i);
			}
		} else if (value instanceof Rule) {
			Rule r = (Rule) value;
			dropRedundant(r.getRuleItems());
			dropRedundant(r.getRuleItemGroups());
		}
		else if (value instanceof RuleItem) {
			RuleItem ri = (RuleItem) value;
			if (ri.getItem() != null)
			{
				if (ri.getItem().getCoupledInventoryItem() != null)
				{
					ri.getItem().getCoupledInventoryItem().setCategory(null);
					ri.getItem().getCoupledInventoryItem().setGroup(null);
				}
				dropRedundant(ri.getItem());
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
		else if (value instanceof InventoryCategory)
		{
			InventoryCategory ic = (InventoryCategory) value;
			dropRedundant(ic.getChildren());
		}
		else if (value instanceof InventoryGroup)
		{
			InventoryGroup ig = (InventoryGroup) value;
			dropRedundant(ig.getChildren());
		}
		else if (value instanceof InventoryItem)
		{
			InventoryItem ii = (InventoryItem) value;
			dropRedundant(ii.getItem());
		}
		else if (value instanceof InformationType)
		{
			InformationType it = (InformationType) value;
			dropRedundant(it.getDataFormats());
		}
		else if (value instanceof RuleUserAD) {
			RuleUserAD ru = (RuleUserAD) value;
			if (ru.getDomainItem() != null)
			{
				dropRedundant(ru.getDomainItem());
			}
		}
		else if (value instanceof DataFormat) {
			DataFormat df = (DataFormat) value;
			df.setMimeTypes(new ArrayList<MIMEType>());
		}
		else if (value instanceof ADDomain)
		{
			ADDomain d = (ADDomain) value;
			d.setRoot(null);
		}		
		else if (value instanceof ADDomainItemGroup)
		{
			ADDomainItemGroup ig = (ADDomainItemGroup) value;
			ig.setParent(null);
			ig.setChildren(new ArrayList<ADDomainItem>());
		}
		else if (value instanceof ADDomainGroup)
		{
			ADDomainGroup g = (ADDomainGroup) value;
			g.setParent(null);
			g.setUsers(new HashSet<ADDomainUser>());
		}
		else if (value instanceof ADDomainUser)
		{
			ADDomainUser u = (ADDomainUser) value;
			u.setParent(null);
			u.setGroups(new HashSet<ADDomainGroup>());
			u.setAliases(new ArrayList<ADDomainUserAlias>());
		}
	}

	@Override
	public void writeObject(Object value) throws IOException {
		dropRedundant(value);
	    super.writeObject(value);
	}
	
}
