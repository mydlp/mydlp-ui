package com.mydlp.ui.service;

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("encryptionService")
public class EncryptionServiceImpl implements EncryptionService {

	//private static Logger logger = LoggerFactory.getLogger(EncryptionServiceImpl.class);
	
	private static String ALGORITHM = "Blowfish";
	private static String MODE = "/ECB/NoPadding";
	private static String PROVIDER = "SunJCE";
	
	@Override
	public ByteBuffer decrypt(String secret, ByteBuffer data) 
			throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, 
			NoSuchProviderException, IllegalBlockSizeException, BadPaddingException, ShortBufferException {
        Cipher cipher = Cipher.getInstance(ALGORITHM + MODE, PROVIDER);
        SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        ByteBuffer output = ByteBuffer.allocate(data.remaining());
        cipher.doFinal(data, output);
        output.flip();
        return output;
	}
	

	@Override
	public ByteBuffer encrypt(String secret, ByteBuffer data) 
			throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, 
			NoSuchProviderException, IllegalBlockSizeException, BadPaddingException, ShortBufferException {
        Cipher cipher = Cipher.getInstance(ALGORITHM + MODE, PROVIDER);
        SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        ByteBuffer output = ByteBuffer.allocate(data.remaining());
        cipher.doFinal(data, output);
        output.flip();
		return output;
	}


}
