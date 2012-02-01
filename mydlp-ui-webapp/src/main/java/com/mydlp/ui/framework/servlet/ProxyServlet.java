package com.mydlp.ui.framework.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1426434415956314536L;
	
	private static Logger logger = LoggerFactory.getLogger(ProxyServlet.class);

	protected static Map<String, String> urlMap = null;
	protected static Map<String, String> typeMap = null;
	
	protected static final String CACHE_KEY = "mydlp-url-cache-map";
	
	static {
		urlMap = new TreeMap<String,String>(); 
		urlMap.put("blogrss", "http://blog.mydlp.com/feeds/posts/default?alt=rss");
		urlMap.put("datalossdbrss", "http://datalossdb.org/latest_incidents.rss");
		
		typeMap = new TreeMap<String,String>(); 
		typeMap.put("blogrss", "application/rss+xml");
		typeMap.put("datalossdbrss", "application/rss+xml");
	}
	
	@SuppressWarnings("unchecked")
	protected Map<String, StringBuffer> getCacheMap(HttpServletRequest request)
	{
		Object obj = request.getSession().getServletContext().getAttribute(CACHE_KEY);
		if (obj == null)
		{
			obj = new TreeMap<String, StringBuffer>();
			request.getSession().getServletContext().setAttribute(CACHE_KEY, obj);
		}
		
		return (Map<String, StringBuffer>) obj;
	}
	
	protected StringBuffer getContent(Map<String, StringBuffer> cache, String urlKey)
	{
		if (cache.containsKey(urlKey))
		{
			return cache.get(urlKey);
		}
		else
		{
			StringBuffer sb = new StringBuffer();
			try {
			    URL url = new URL(urlMap.get(urlKey));
			    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			    String str;
			    while ((str = in.readLine()) != null) sb.append(str);
			    in.close();
			} catch (MalformedURLException e) {
				logger.error(e.getMessage());
				sb = null;
			} catch (IOException e) {
				logger.error(e.getMessage());
				sb = null;
			}
			if (sb != null)
			{
				cache.put(urlKey, sb);
				return sb;
			}
		}
		return new StringBuffer();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String urlKey = req.getParameter("q");
		System.out.println("is here!!!!!");
		if (urlKey != null && urlMap.containsKey(urlKey))
		{
			Map<String, StringBuffer> cache = getCacheMap(req);
			StringBuffer sb = getContent(cache, urlKey);
			resp.setContentType(typeMap.get(urlKey));
			PrintWriter out = resp.getWriter();
			out.print(sb.toString());
		}
	}

}
