package com.mydlp.ui.framework.aspectj;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydlp.ui.domain.AbstractEntity;
import com.mydlp.ui.domain.AbstractNamedEntity;

@Aspect
@Service("auditTrailAspect")
public class AuditTrail {

	private static Logger logger = LoggerFactory.getLogger("AUDIT");
	
	@Autowired(required=true)
	protected HttpServletRequest request;
	
	@Pointcut("execution(public * com.mydlp.ui.remoting.blazeds..*Service.*(..))")
    public void methodsToTrail(){}
	
	@Around("methodsToTrail()")
    public Object trail(ProceedingJoinPoint pjp) throws Throwable {
        Long time = System.currentTimeMillis();
        try {
            return pjp.proceed();
        } finally {
            time = System.currentTimeMillis() - time;
            logger.info(
            		prettifyClassName(pjp.getTarget().getClass()) + "." +
            		pjp.getSignature().getName() +
            		argsToAuditString(pjp.getArgs()) +
            		" procedure is called by " +
            		"'" + request.getRemoteUser() + "' " +
            		"from " + request.getRemoteAddr() + ", " +
            		"and completed in " + time.toString() + "ms."
            	);
        }
        
    }
	
	protected static String argsToAuditString(Object [] args) {
		List<String> auditStrList = new ArrayList<String>();
		for (int i = 0; i < args.length; i++) {
			String auditStr = objectToAuditString(args[i]);
			auditStrList.add(auditStr);
		}
		return "(" + StringUtils.join(auditStrList, ",") + ")";
	}
	
	private static final String MYDLP_UI_REMOTING_PACKAGE = "com.mydlp.ui.remoting.";
	
	private static final String MYDLP_UI_DOMAIN_PACKAGE = "com.mydlp.ui.domain.";
	
	private static final String JAVA_LANG_PACKAGE = "java.lang.";
	
	protected static <T> String prettifyClassName(Class<T> c) {
		return prettifyClassName(c.getName());
	}
	
	protected static String prettifyClassName(String value) {
		String className = new String(value);
		if (className.startsWith(MYDLP_UI_REMOTING_PACKAGE))
			className = className.substring(MYDLP_UI_REMOTING_PACKAGE.length());
		else if (className.startsWith(MYDLP_UI_DOMAIN_PACKAGE))
			className = className.substring(MYDLP_UI_DOMAIN_PACKAGE.length());
		else if (className.startsWith(JAVA_LANG_PACKAGE))
			className = className.substring(JAVA_LANG_PACKAGE.length());
		return className;
	}
	
	protected static String objectToAuditString(Object o) {
		String auditStr = "";
		String className = o.getClass().getName();
		className = prettifyClassName(className);
		
		if (o instanceof AbstractEntity) {
			String objectId = ((AbstractEntity) o).getId().toString();
			
			auditStr += className + "[id:" + objectId + "]";
			if (o instanceof AbstractNamedEntity) {
				String objectName = ((AbstractNamedEntity) o).getName();
				String objectNameKey = ((AbstractNamedEntity) o).getNameKey();
				if (objectName != null && objectName.length() > 0)
					auditStr += "[name:" + objectName + "]";
				else
					auditStr += "[nameKey:" + objectNameKey + "]";
			}
			return auditStr;
		}
		
		return o.toString();
	}
	
}
