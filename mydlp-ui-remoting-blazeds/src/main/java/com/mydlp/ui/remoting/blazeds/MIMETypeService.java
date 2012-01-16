package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.MIMEType;

@Secured(AuthSecurityRole.ROLE_USER)
public interface MIMETypeService {

	public List<MIMEType> getMIMETypes();
	
	public MIMEType save(MIMEType m);

}