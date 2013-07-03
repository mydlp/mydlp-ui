package com.mydlp.ui.thrift;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

public interface MyDLPUIThriftService {
	
	public void compileFilter(Integer filterId);
	
	public String getCompileStatus();
	
	public ByteBuffer getRuletable(String endpointId, String revisionId);
	
    public String receiveBegin(String endpointId);

    public String receiveChunk(String endpointId, long itemId, 
    			ByteBuffer chunkData, int chunkNum, int chunkNumTotal);
    
    public void generateFingerprints(long documentId, String filename, ByteBuffer data);
    
    public void generateFingerprintsWithFile(long documentId, String filename, String filepath);
    
    public void requeueIncident(Integer incidentId);
    
    public Map<String,String> registerUserAddress(String endpointId, String ipAddress, String userH, ByteBuffer payload);
    
    public String saveLicenseKey(String licenseKey);

    public LicenseObject getLicense();
    
    public String apiQuery(String ipAddress, String filename, String user, ByteBuffer data);

    public void startDiscoveryOnDemand(int ruleId);
    
    public void stopDiscoveryOnDemand(int ruleId);
    
    public void pauseDiscoveryOnDemand(int ruleId);
    
    public List<String> getRemoteStorageDir(int id);
    
    public void startFingerprinting(int id);
    
    public void stopFingerprinting(int id);
    
    public String testConnection(Map<String, String> remoteStorage);
    
    public String testWebServer(String remoteStorage);
    
    public void stopReportBeforeRemoveRule(int id);

}
