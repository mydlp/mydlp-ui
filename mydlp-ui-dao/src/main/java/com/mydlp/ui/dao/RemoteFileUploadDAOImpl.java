package com.mydlp.ui.dao;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Repository;


@Repository("remoteFileUploadDAO")
public class RemoteFileUploadDAOImpl extends AbstractPolicyDAO implements RemoteFileUploadDAO {

	@Override
	public String doUpload(byte[] bytes, String fileName) throws Exception {
		
		fileName = "C:\\users\\usermydlp\\"  + fileName;  
		File f = new File(fileName);  
		FileOutputStream fos = new FileOutputStream(f);  
		fos.write(bytes);  
		fos.close();  
		return "success"; 
	
	}

}
