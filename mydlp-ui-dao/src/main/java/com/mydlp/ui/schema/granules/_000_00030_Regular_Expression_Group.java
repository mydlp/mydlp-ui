package com.mydlp.ui.schema.granules;

import java.util.ArrayList;
import java.util.List;

import com.mydlp.ui.domain.Matcher;
import com.mydlp.ui.domain.RegularExpressionGroup;
import com.mydlp.ui.domain.RegularExpressionGroupEntry;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00030_Regular_Expression_Group extends AbstractGranule {

	
	public RegularExpressionGroupEntry createMTObj(String regex, RegularExpressionGroup re) {
		RegularExpressionGroupEntry mt = new RegularExpressionGroupEntry();
		mt.setRegex(regex);
		getHibernateTemplate().saveOrUpdate(mt);
		return mt;
	}
	
	@Override
	protected void callback() {
		
		Matcher m = new Matcher();
		m.setFunctionName("cc");
		
		RegularExpressionGroup df = new RegularExpressionGroup();
		df.setNameKey("regularExpression.test.label");
		//df.setMatcher(m);
		List<RegularExpressionGroupEntry> mts = new ArrayList<RegularExpressionGroupEntry>();
		mts.add(createMTObj("ozgen", df));
		mts.add(createMTObj("muzac", df));
		mts.add(createMTObj("medra", df));
		mts.add(createMTObj("teknoloji", df));
		mts.add(createMTObj("mydlp", df));
		mts.add(createMTObj("burak", df));
		mts.add(createMTObj("kerem", df));
		mts.add(createMTObj("ozgur", df));
		mts.add(createMTObj("cevahir", df));
		mts.add(createMTObj("batur", df));
		mts.add(createMTObj("akin", df));
		df.setEntries(mts);
		getHibernateTemplate().saveOrUpdate(df);
		
	}
}
