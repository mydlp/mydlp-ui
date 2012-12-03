package com.mydlp.ui.remoting.blazeds;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.TemporaryAccessTokenDAO;

@Service("tokenBRS")
@RemotingDestination
public class TokenBRSImpl implements TokenService
{
	
	@Autowired
	protected TemporaryAccessTokenDAO temporaryAccessTokenDAO;
	
	@Autowired(required=true)
	protected HttpServletRequest request;
	
	@Override
	public String generateToken(String serviceName, String serviceParam) {
	
		String ipAddress = request.getRemoteAddr();
		String username = request.getRemoteUser();
		String tokenKey = temporaryAccessTokenDAO.generateTokenKey(ipAddress, username, serviceName, serviceParam);

		String proto = "p";
		if (request.isSecure())
		{
			proto = "s";
		}
		String serverHost = request.getServerName();
		int serverPort = request.getServerPort();
		String returnToken = proto + serverHost + ":" + serverPort + "-" + tokenKey;
		
		return returnToken;
	}

	@Override
	public String generateToolsUploaderToken(String serviceParam) {
		return generateToken("tools-uploader", serviceParam);
	}
	
}