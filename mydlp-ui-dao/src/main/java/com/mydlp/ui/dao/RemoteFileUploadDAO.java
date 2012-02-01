package com.mydlp.ui.dao;

public interface RemoteFileUploadDAO  {
	
	public String doUpload(byte[] bytes, String fileName) throws Exception;
}
