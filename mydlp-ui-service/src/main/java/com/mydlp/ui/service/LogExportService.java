package com.mydlp.ui.service;

import java.util.List;

import com.mydlp.ui.domain.AuthUser;

public interface LogExportService {

	public String exportExcel(AuthUser authUser, List<List<Object>> criteriaList);
	
	public byte[] getExport(String exportId);
	
}
