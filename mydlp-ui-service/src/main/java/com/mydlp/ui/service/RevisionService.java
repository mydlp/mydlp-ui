package com.mydlp.ui.service;

import com.mydlp.ui.domain.Revision;

public interface RevisionService {

	public void save();
	
	public void restore(Revision revision);
	
}
