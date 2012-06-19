package com.mydlp.ui.thrift;

import java.nio.ByteBuffer;

public interface MyDLPUIThriftService {
	
	public void compileFilter(Integer filterId);
	
	public ByteBuffer getRuletable(String ipAddress, String userH, String revisionId);
	
    public String receiveBegin(String ipAddress);

    public String receiveChunk(String ipAddress, long itemId, 
    			ByteBuffer chunkData, int chunkNum, int chunkNumTotal);
    
    public void generateFingerprints(long documentId, String filename, ByteBuffer data);
    
    public void requeueIncident(Integer incidentId);
    
    public String registerUserAddress(String ipAddress, String userH, ByteBuffer payload);
    
    public void saveLicenseKey(String licenseKey);

    public LicenseObject getLicense();

}
