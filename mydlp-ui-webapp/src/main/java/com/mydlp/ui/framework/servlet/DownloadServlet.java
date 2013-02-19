package com.mydlp.ui.framework.servlet;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.HttpRequestHandler;

import com.mydlp.ui.dao.IncidentLogDAO;
import com.mydlp.ui.dao.UserDAO;
import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.AuthUser;
import com.mydlp.ui.domain.IncidentLogFile;
import com.mydlp.ui.domain.IncidentLogFileContent;
import com.mydlp.ui.service.AuditTrailService;
import com.mydlp.ui.service.VersionService;

@Service("downloadServlet")
public class DownloadServlet implements HttpRequestHandler {

	private static Logger logger = LoggerFactory.getLogger(DownloadServlet.class);
	private static Logger errorLogger = LoggerFactory.getLogger("IERROR");

	private static final String WINDOWS_AGENT_FOLDER = "/usr/share/mydlp/endpoint/win/msi/";
	
	private static final int BUFFER_SIZE = 102400;

	@Autowired
	protected IncidentLogDAO incidentLogDAO;

	@Autowired
	protected UserDAO userDAO;

	@Autowired
	protected AuditTrailService auditTrailService;
	
	@Autowired
	protected VersionService versionService;
	
	
	@Autowired
	@Qualifier("policyTransactionTemplate")
	protected TransactionTemplate transactionTemplate;
	

	@Override
	public void handleRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String urlKey = req.getParameter("key"); 
		String urlId = req.getParameter("id");
		final String username = req.getRemoteUser();
		
		boolean isAdmin = transactionTemplate.execute(new TransactionCallback<Boolean>() {
			@Override
			public Boolean doInTransaction(TransactionStatus arg0) {
				AuthUser authUser = userDAO.findByName(username);
				if (authUser == null)
					return false;
				else
					return authUser.hasRole(AuthSecurityRole.ROLE_ADMIN);
			}
		});
		
		boolean isSuperAdmin = transactionTemplate.execute(new TransactionCallback<Boolean>() {
			@Override
			public Boolean doInTransaction(TransactionStatus arg0) {
				AuthUser authUser = userDAO.findByName(username);
				if (authUser == null)
					return false;
				else
					return authUser.hasRole(AuthSecurityRole.ROLE_SUPER_ADMIN);
			}
		});
		
		boolean isAuditor = transactionTemplate.execute(new TransactionCallback<Boolean>() {
			@Override
			public Boolean doInTransaction(TransactionStatus arg0) {
				AuthUser authUser = userDAO.findByName(username);
				if (authUser == null)
					return false;
				else
					return authUser.hasRole(AuthSecurityRole.ROLE_AUDITOR);
			}
		});
		
		if( 	(urlKey != null && urlKey.equals("latest-windows-agent")) ||
				(isAdmin && urlKey != null && urlKey.equals("user.der")) || 
				isSuperAdmin || 
				(isAuditor && urlKey == null )
				) {
			try {
				IncidentLogFile logFile = null;
				if (urlKey != null) {
					if (urlKey.equals("user.der")) {
						logFile = new IncidentLogFile();
						logFile.setFilename("mydlp-user-certificate.der");
						IncidentLogFileContent content = new IncidentLogFileContent();
						content.setMimeType("application/x-x509-ca-cert");
						content.setLocalPath("/etc/mydlp/ssl/user.der");
						logFile.setContent(content);
					} else if (urlKey.equals("latest-windows-agent")) {
						String filename = getWindowsAgentFilename(); 
						logFile = new IncidentLogFile();
						logFile.setFilename(filename);
						IncidentLogFileContent content = new IncidentLogFileContent();
						content.setMimeType("application/x-msi");
						content.setLocalPath(WINDOWS_AGENT_FOLDER + filename);
						logFile.setContent(content);
					}
					else {
						errorLogger.error("Unknown download key: " + req.getParameter("key"));
						return;
					}
				} else {
					Integer logFileId = Integer.parseInt(urlId);
					logFile = incidentLogDAO.geIncidentLogFile(logFileId);

					if (logFile == null)
					{
						errorLogger.error("Null object returned from DAO. urlId: " + urlId);
						return;
					}
				}

				File localFile = new File(logFile.getContent().getLocalPath());

				if (localFile.exists()) {
					resp.setContentType(logFile.getContent().getMimeType());
					resp.setContentLength((int) localFile.length());
					resp.setHeader( "Content-Disposition", "attachment; filename=\"" + logFile.getFilename() + "\"" );

					//
					//  Stream to the requester.
					//
					byte[] bbuf = new byte[BUFFER_SIZE];
					DataInputStream in = new DataInputStream(new FileInputStream(localFile));
					ServletOutputStream op = resp.getOutputStream();

					int length = 0;
					while ((in != null) && ((length = in.read(bbuf)) != -1))
					{
						op.write(bbuf,0,length);
					}

					in.close();
					op.flush();
					op.close();
				}
				else
				{
					resp.setContentType("text/plain");
					resp.getWriter().println("File is not available");
					errorLogger.error("File is not available: " + logFile.getContent().getLocalPath());
					resp.getOutputStream().flush();
					resp.getOutputStream().close();
				}

				auditTrailService.audit(getClass(), "download", new Object[]{urlKey, urlId});
			} catch (NumberFormatException e) {
				logger.error("Cannot format ", urlId , e);
			} catch (FileNotFoundException e) {
				logger.error("Cannot find file", e);
			} catch (IOException e) {
				logger.error("IOError occurred", e);
			} catch (RuntimeException e) {
				logger.error("Runtime error occured", e);
			}
		}
		else
		{
			errorLogger.error("User ( " + username + " ) is not authticated to complete operation ( key: " + urlKey + " id: " + urlId + " ).");
		}
	}
	
	protected String getWindowsAgentFilename() {
		String version = versionService.getWindowsAgentVersion();
		if (version == null || version.length() == 0) {
			return "none";
		}
		return "mydlp_" + version.replace('.', '_') + ".msi";
	}
	
}
