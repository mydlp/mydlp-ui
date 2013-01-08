package com.mydlp.ui.service;

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

public interface EncryptionService {

	public ByteBuffer decrypt(String secret, ByteBuffer data) 
			throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, 
			NoSuchProviderException, IllegalBlockSizeException, BadPaddingException, ShortBufferException;
	
	public ByteBuffer encrypt(String secret, ByteBuffer data) 
			throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, 
			NoSuchProviderException, IllegalBlockSizeException, BadPaddingException, ShortBufferException;
	
}
