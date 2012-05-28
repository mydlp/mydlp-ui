package com.mydlp.ui.remoting.blazeds;

import java.util.Date;
import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.AuthSecurityRole;

@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_AUDITOR})
public interface GenericService {
	
	public AbstractEntity save(AbstractEntity item);
	
	public void remove(AbstractEntity item);
	
	public void removeAll(List<AbstractEntity> items);
	
	public void sync();
	
	public Date getSystemTime();

}