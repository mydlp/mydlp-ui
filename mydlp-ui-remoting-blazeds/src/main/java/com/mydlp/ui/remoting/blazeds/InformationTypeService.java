package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.InformationType;

@Secured(AuthSecurityRole.ROLE_USER)
public interface InformationTypeService {
	public List<InformationType> getInformationTypes();
}
