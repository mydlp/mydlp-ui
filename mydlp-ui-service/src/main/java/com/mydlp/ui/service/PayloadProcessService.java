package com.mydlp.ui.service;

import java.nio.ByteBuffer;


public interface PayloadProcessService {
	
	public SyncObject toSyncObject(ByteBuffer data) throws ImproperPayloadEncapsulationException;

	public ByteBuffer toByteBuffer(SyncObject object) throws ImproperPayloadEncapsulationException;
	
	public class SyncObject {
		
		protected String endpointId;
		protected Integer size;
		protected String md5HashHex;
		protected ByteBuffer payload;
		
		public String getEndpointId() {
			return endpointId;
		}
		public void setEndpointId(String endpointId) {
			this.endpointId = endpointId;
		}
		public ByteBuffer getPayload() {
			return payload;
		}
		public void setPayload(ByteBuffer payload) {
			this.payload = payload;
		}
	}
	
	public class ImproperPayloadEncapsulationException extends Throwable {
		private static final long serialVersionUID = -6291315831752827048L;
		public ImproperPayloadEncapsulationException() {
			super();
		}
		public ImproperPayloadEncapsulationException(String arg0,
				Throwable arg1, boolean arg2, boolean arg3) {
			super(arg0, arg1, arg2, arg3);
		}
		public ImproperPayloadEncapsulationException(String arg0, Throwable arg1) {
			super(arg0, arg1);
		}
		public ImproperPayloadEncapsulationException(String arg0) {
			super(arg0);
		}
		public ImproperPayloadEncapsulationException(Throwable arg0) {
			super(arg0);
		}
	}
	
}
