package com.mydlp.ui.thrift;

import java.nio.ByteBuffer;
import java.util.List;

public interface MyDLPUIThriftService {
	
	public void compileFilter(Integer filterId);
	
	public ByteBuffer getRuletable(String ipAddress, String revisionId);
	
    public String receiveBegin(String ipAddress);

    public String receiveChunk(String ipAddress, long itemId, 
    			ByteBuffer chunkData, int chunkNum, int chunkNumTotal);
    
    public List<Long> getFingerprints(String filename, ByteBuffer data);
    
}
