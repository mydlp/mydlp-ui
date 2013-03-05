package com.mydlp.ui.thrift;

import java.nio.ByteBuffer;
import java.util.Map;

public interface MyDLPUIThriftService {
	
	public void compileFilter(Integer filterId);
	
	public String getCompileStatus();
	
	public ByteBuffer getRuletable(String endpointId, String ipAddress, String userH, String revisionId);
	
    public String receiveBegin(String ipAddress);

    public String receiveChunk(String ipAddress, long itemId, 
    			ByteBuffer chunkData, int chunkNum, int chunkNumTotal);
    
    public void generateFingerprints(long documentId, String filename, ByteBuffer data);
    
    public void generateFingerprintsWithFile(long documentId, String filename, String filepath);
    
    public void requeueIncident(Integer incidentId);
    
    public Map<String,String> registerUserAddress(String ipAddress, String userH, ByteBuffer payload);
    
    public String saveLicenseKey(String licenseKey);

    public LicenseObject getLicense();
    
    public String apiQuery(String ipAddress, String filename, ByteBuffer data);
    
    public void registerCommand(String endpointId, String command);
    
    public void startDiscoveryOnDemand(int ruleId);
    
    public void stopDiscoveryOnDemand(int ruleId);
    
    public void pauseDiscoveryOnDemand(int ruleId);

}
