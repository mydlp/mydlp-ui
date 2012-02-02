package com.mydlp.ui.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service("solrService")
public class SolrServiceImpl implements SolrService {

	private static Logger logger = LoggerFactory.getLogger(SolrServiceImpl.class);
	protected static final String SOLR_URL = "http://127.0.0.1:8010/solr";
	protected static final String CATEGORY_ARCHIVE = "archive";
	protected SolrServer solrServer = null;

	@PostConstruct
	protected synchronized void init() {
		if (solrServer == null) {
			try {
				CommonsHttpSolrServer server = new CommonsHttpSolrServer(SOLR_URL);
				server.setSoTimeout(1000);
				server.setConnectionTimeout(100);
				server.setDefaultMaxConnectionsPerHost(100);
				server.setMaxTotalConnections(100);
				server.setFollowRedirects(false);
				server.setAllowCompression(true);
				server.setMaxRetries(1);
				solrServer = server;
			} catch (MalformedURLException e) {
				logger.error("Cannot connect solr : " + SOLR_URL , e);
			}
		}
	}

	protected  SolrServer getSolrServer() {
		if (solrServer == null) init();
		return solrServer;
	}
	
	public void addDocuments(List<SolrInputDocument> inputDocuments) {
		
		try {
			getSolrServer().add(inputDocuments);
		} catch (SolrServerException e) {
			logger.error("Error occured when adding documents", e);
		} catch (IOException e) {
			logger.error("Error occured when adding documents", e);
		}
	}
	
	public void addArchiveDocument(Integer id, String text)
	{
		try {
			SolrInputDocument doc = new SolrInputDocument();
			doc.addField("id", id);
			doc.addField("category", CATEGORY_ARCHIVE);
			doc.addField("content", text);
			getSolrServer().add(doc);
		} catch (SolrServerException e) {
			logger.error("Error occured when adding documents", e);
		} catch (IOException e) {
			logger.error("Error occured when adding documents", e);
		}
	}
	
	public void addArchiveDocument(Integer id, ByteBuffer buffer)
	{
		try {
			SolrInputDocument doc = new SolrInputDocument();
			doc.addField("id", id);
			doc.addField("category", CATEGORY_ARCHIVE);
			doc.addField("content", Charset.forName("UTF-8").decode(buffer));
			getSolrServer().add(doc);
		} catch (SolrServerException e) {
			logger.error("Error occured when adding documents", e);
		} catch (IOException e) {
			logger.error("Error occured when adding documents", e);
		} catch (RuntimeException e) {
			logger.error("Error occured when adding documents", e);
		}
	}
	
	public void commit() {
		
		try {
			getSolrServer().commit();
		} catch (SolrServerException e) {
			logger.error("Error occured when adding documents", e);
		} catch (IOException e) {
			logger.error("Error occured when adding documents", e);
		}
	}
	
	public Long queryContentCount(String string) {
		SolrQuery query = new SolrQuery();
		query.setQuery(string);
		query.addField("id");
		query.setRows(1);
		
		try {
			QueryResponse response = getSolrServer().query(query);
			SolrDocumentList result = response.getResults();
			return result.getNumFound();
		} catch (SolrServerException e) {
			logger.error("Error occurred when querying", e);
		}
		
		return 0L;
	}
	
	public List<Integer> queryContent(String string, Integer offset, Integer count)
	{
		List<Integer> returnList = new ArrayList<Integer>(count);
		SolrQuery query = new SolrQuery();
		query.setQuery(string);
		query.addField("id");
		query.setStart(offset);
		query.setRows(count);
		try {
			QueryResponse response = getSolrServer().query(query);
			SolrDocumentList result = response.getResults();
			
			for (SolrDocument solrDocument : result) 
				returnList.add(Integer.parseInt((String) solrDocument.get("id")));
			
		} catch (NumberFormatException e) {
			logger.error("Error occurred when formatting number", e);
		} catch (SolrServerException e) {
			logger.error("Error occurred when querying", e);
		}
		return returnList;
	}

}
