package com.mydlp.ui.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("informationTypeDAO")
@Transactional
public class InformationTypeDAOImpl extends AbstractPolicyDAO implements InformationTypeDAO {

	@Override
	@Transactional(readOnly=true)
	public List<Map<String, String>> getITypeLabelsAndIds() {
		List<Map<String, String>> returnList = new ArrayList<Map<String,String>>();

		Query query = getSession().createQuery(
				"select t.id, i.name, i.nameKey from InformationType t, InventoryItem i where i.item=t ");
		for (@SuppressWarnings("unchecked")
		Iterator<Object[]> iterator = query.list().iterator(); iterator.hasNext();) {
			Map<String, String> returnMap = new HashMap<String, String>();
			Object[] row = (Object[]) iterator.next();
			returnMap.put("id", row[0].toString());
			returnMap.put("name", (String)row[1]);
			returnMap.put("nameKey", (String)row[2]);
			returnList.add(returnMap);
		}
		
		return returnList;
	}


}
