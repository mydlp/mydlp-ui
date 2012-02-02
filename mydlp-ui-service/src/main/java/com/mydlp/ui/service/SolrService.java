package com.mydlp.ui.service;

import java.nio.ByteBuffer;
import java.util.List;

import org.apache.solr.common.SolrInputDocument;

public interface SolrService {

	public void addDocuments(List<SolrInputDocument> inputDocuments);
	
	public void addArchiveDocument(Integer id, String text);
	
	public void addArchiveDocument(Integer id, ByteBuffer buffer);
	
	public void commit();
	
	public Long queryContentCount(String string);
	
	public List<Integer> queryContent(String string, Integer offset, Integer count);

}
