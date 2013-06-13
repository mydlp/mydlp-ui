package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.DataFormatDAO;
import com.mydlp.ui.domain.DataFormat;
import com.mydlp.ui.domain.MIMEType;

@Service("dataFormatBRS")
@RemotingDestination
public class DataFormatBRSImpl implements DataFormatService
{
	@Autowired
	protected DataFormatDAO dataFormatDAO;

	@Override
	public List<DataFormat> getDataFormats() {
		return dataFormatDAO.getDataFormats();
	}

	@Override
	public DataFormat save(DataFormat d) {
		return dataFormatDAO.save(d);
	}

	@Override
	public List<MIMEType> getMimes(Integer dataFormatId) {
		return dataFormatDAO.getMimes(dataFormatId);
	}
}