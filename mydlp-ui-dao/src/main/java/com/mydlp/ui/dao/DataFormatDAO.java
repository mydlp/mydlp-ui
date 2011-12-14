package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.DataFormat;

public interface DataFormatDAO {

	public List<DataFormat> getDataFormats();
	
	public DataFormat save(DataFormat r);
	
}
