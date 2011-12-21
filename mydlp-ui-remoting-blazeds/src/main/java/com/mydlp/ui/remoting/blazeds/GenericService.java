package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.AuthSecurityRole;

@Secured(AuthSecurityRole.ROLE_USER)
public interface GenericService {
	
	public AbstractEntity save(AbstractEntity item);
	
	public void remove(AbstractEntity item);
	
	public void removeAll(List<AbstractEntity> items);

}