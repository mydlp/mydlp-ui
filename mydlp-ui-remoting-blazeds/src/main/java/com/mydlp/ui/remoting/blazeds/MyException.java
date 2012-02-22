package com.mydlp.ui.remoting.blazeds;

public class MyException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4969064284958471475L;

	public MyException(String message)
	{
		super(message);
	}
	
	public String getMyName()
	{
		return "Special Flex Exception";
	}

}
