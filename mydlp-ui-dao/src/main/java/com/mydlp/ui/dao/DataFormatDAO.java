package com.mydlp.ui.dao;

import java.util.List;

import com.mydlp.ui.domain.DataFormat;
import com.mydlp.ui.domain.MIMEType;

public interface DataFormatDAO {

	public List<DataFormat> getDataFormats();
	
	public DataFormat save(DataFormat r);
	
	public List<MIMEType> getMimes(Integer dataFormatId);
	
}
