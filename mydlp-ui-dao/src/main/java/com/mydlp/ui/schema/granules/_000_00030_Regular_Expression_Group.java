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
		mts.add(createMTObj("example/keyword/1", df));
		mts.add(createMTObj("example/keyword/2", df));
		mts.add(createMTObj("example/keyword/3", df));
		mts.add(createMTObj("example/keyword/4", df));
		mts.add(createMTObj("example/keyword/5", df));
		mts.add(createMTObj("example/keyword/6", df));
		mts.add(createMTObj("example/keyword/7", df));
		mts.add(createMTObj("example/keyword/8", df));
		mts.add(createMTObj("example/keyword/9", df));
		mts.add(createMTObj("example/keyword/10", df));
		mts.add(createMTObj("example/keyword/11", df));
		df.setEntries(mts);
		getHibernateTemplate().saveOrUpdate(df);
		
	}
}
