package com.mydlp.ui.remoting.blazeds;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;


@Secured(AuthSecurityRole.ROLE_USER)
public interface RemoteFileUploadService {
	
	public String doRemoteUpload(byte[] bytes, String fileName);

}
