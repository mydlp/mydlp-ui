package com.mydlp.ui.service;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.EndpointDAO;

@Service("payloadProcessService")
public class PayloadProcessServiceImpl implements PayloadProcessService {

	private static Logger logger = LoggerFactory.getLogger(PayloadProcessServiceImpl.class);

	private static int PROTO_MIN_CHUNK_SIZE = 78;
	private static int PROTO_SIZE_PART_SIZE = 17;
	private static String PROTO_HEAD = "MyDLPEPSync_";
	private static String PROTO_PAYLOAD_HEAD = "MyDLPEPPayload_";
	
	@Autowired
	protected EndpointDAO endpointDAO;
	
	@Autowired
	protected EncryptionService encryptionService;

	@Override
	public SyncObject toSyncObject(ByteBuffer chunk)
			throws ImproperPayloadEncapsulationException {
		try {
			SyncObject object = new SyncObject();
			byte[] buf = null;
			
			if (chunk.remaining() < PROTO_MIN_CHUNK_SIZE)
				throw new ImproperPayloadEncapsulationException("Chunk is too small");
			
			buf = new byte[12];
			chunk.get(buf);
			if (! PROTO_HEAD.equals(new String(buf)))
				throw new ImproperPayloadEncapsulationException("Chunk does not start with " + PROTO_HEAD);
			
			buf = new byte[32];
			chunk.get(buf);
			object.setEndpointId(new String(buf));
			String endpointSecret = endpointDAO.getEndpointSecret(object.getEndpointId());
			if (endpointSecret == null)
				throw new ImproperPayloadEncapsulationException("Can not find secret for endpointId");
			
			if (chunk.get() != '_')
				throw new ImproperPayloadEncapsulationException("Expecting _");
			
			buf = new byte[16];
			chunk.get(buf);
			Integer payloadSize = Integer.parseInt(new String(buf));
			
			if (chunk.get() != '_')
				throw new ImproperPayloadEncapsulationException("Expecting _");
			
			ByteBuffer payloadChunk = encryptionService.decrypt(endpointSecret, chunk);
			
			logger.error(payloadChunk.position() + "");
			logger.error(payloadChunk.remaining() + "");
			logger.error(payloadSize.toString());
			payloadChunk.limit(payloadSize);
			logger.error(payloadChunk.position() + "");
			logger.error(payloadChunk.remaining() + "");
			
			buf = new byte[15];
			payloadChunk.get(buf);
			
			if (! PROTO_PAYLOAD_HEAD.equals(new String(buf)))
				throw new ImproperPayloadEncapsulationException("Paylaod chunk does not start with " + PROTO_PAYLOAD_HEAD);
			
			payloadChunk.compact();
			object.setPayload(payloadChunk);
			return object;
		} catch (ImproperPayloadEncapsulationException e) {
			logger.error("An error occurred", e);
			throw e;
		} catch (Throwable e) {
			logger.error("An error occurred", e);
			throw new ImproperPayloadEncapsulationException(e);
		}
		
	}

	@Override
	public ByteBuffer toByteBuffer(SyncObject object)
			throws ImproperPayloadEncapsulationException {
		try {
			String endpointSecret = endpointDAO.getEndpointSecret(object.getEndpointId());
			if (endpointSecret == null)
				throw new ImproperPayloadEncapsulationException("Can not find secret for endpointId");
			Integer endpointSize = object.getPayload().remaining();
			endpointSize += PROTO_PAYLOAD_HEAD.length();
			Integer zeroLength = 0;
			if (endpointSize % 8 != 0)
			{
				zeroLength = (8 - (endpointSize % 8));
			}
			
			ByteBuffer payloadChunk = ByteBuffer.allocate(endpointSize + zeroLength);
			payloadChunk.put(PROTO_PAYLOAD_HEAD.getBytes());
			payloadChunk.put(object.getPayload());
			for (int i = 0 ; i < zeroLength ; i++)
			{
				payloadChunk.put((byte)0);
			}
			payloadChunk.flip();
			payloadChunk.compact();
			
			ByteBuffer cipher = encryptionService.encrypt(endpointSecret, payloadChunk);
			
			Integer byteBufferSize = PROTO_HEAD.length() + PROTO_SIZE_PART_SIZE + cipher.remaining();
			ByteBuffer byteBuffer = ByteBuffer.allocate(byteBufferSize);
			byteBuffer.put(PROTO_HEAD.getBytes());
			byteBuffer.put(String.format("%016d", endpointSize).getBytes());
			byteBuffer.put((byte)'_');
			byteBuffer.put(cipher);
			byteBuffer.flip();
			byteBuffer.compact();
			return byteBuffer;
		} catch (ImproperPayloadEncapsulationException e) {
			logger.error("An error occurred", e);
			throw e;
		} catch (Throwable e) {
			logger.error("An error occurred", e);
			throw new ImproperPayloadEncapsulationException(e);
		}
	}

}
