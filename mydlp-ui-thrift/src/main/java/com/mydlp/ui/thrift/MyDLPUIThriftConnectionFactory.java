package com.mydlp.ui.thrift;

import org.apache.commons.pool.PoolableObjectFactory;

public class MyDLPUIThriftConnectionFactory implements PoolableObjectFactory<MyDLPUIThriftConnection> {

	

	@Override
	public void destroyObject(MyDLPUIThriftConnection arg0) throws Exception {
		arg0.destroy();
	}

	@Override
	public MyDLPUIThriftConnection makeObject() throws Exception {
		MyDLPUIThriftConnection c = new MyDLPUIThriftConnection();
		c.init();
		return c;
	}

	@Override
	public boolean validateObject(MyDLPUIThriftConnection arg0) {
		return arg0.isOpen();
	}

	@Override
	public void activateObject(MyDLPUIThriftConnection arg0) throws Exception {
		arg0.ensureOpen();
	}

	@Override
	public void passivateObject(MyDLPUIThriftConnection arg0) throws Exception {
	}

}
