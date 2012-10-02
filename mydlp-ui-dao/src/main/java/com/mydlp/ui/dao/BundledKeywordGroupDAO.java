package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.BundledKeywordGroup;


public interface BundledKeywordGroupDAO {
	
	 public List<BundledKeywordGroup> getBundledKeywordGroups();
	 	 
	 public BundledKeywordGroup getBundledKeywordGroupById(Integer id);
}
