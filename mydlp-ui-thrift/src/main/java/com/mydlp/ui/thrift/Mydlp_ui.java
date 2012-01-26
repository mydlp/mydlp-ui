/**
 *  Mock mydlp_ui thrift client
 */
package com.mydlp.ui.thrift;

import java.nio.ByteBuffer;

public class Mydlp_ui {

	public static class Client {

		public Client(org.apache.thrift.protocol.TProtocol prot) {
		}

		public void compileCustomer(int Customerid)
				throws org.apache.thrift.TException {
		}
		
	    public ByteBuffer getRuletable(String Ipaddress, String Revisionid)
	    		throws org.apache.thrift.TException {
	    	return null;
	    }

	    public String receiveBegin(String Ipaddress) 
	    		throws org.apache.thrift.TException {
	    	return null;
	    }

	    public String receiveChunk(String Ipaddress, long Itemid, 
	    		ByteBuffer Chunkdata, int Chunknum, int Chunknumtotal) 
	    				throws org.apache.thrift.TException {
	    	return null;
	    }

	}
}
