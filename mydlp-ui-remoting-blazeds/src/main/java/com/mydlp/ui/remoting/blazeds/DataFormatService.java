package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.DataFormat;
import com.mydlp.ui.domain.MIMEType;

@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_SUPER_ADMIN})
public interface DataFormatService {

	public List<DataFormat> getDataFormats();
	
	public DataFormat save(DataFormat d);
	
	public List<MIMEType> getMimes(Integer dataFormatId);

}