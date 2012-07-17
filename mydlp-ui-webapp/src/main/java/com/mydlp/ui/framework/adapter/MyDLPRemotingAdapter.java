package com.mydlp.ui.framework.adapter;


import java.util.Date;
import java.util.HashMap;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dphibernate.adapters.AdapterBuilder;
import org.dphibernate.adapters.IAdapter;
import org.dphibernate.operations.AdapterOperation;
import org.dphibernate.persistence.operations.SaveDPProxyOperation;
import org.dphibernate.serialization.IDeserializer;
import org.dphibernate.serialization.ISerializer;
import org.dphibernate.serialization.ISerializerFactory;
import org.dphibernate.serialization.SerializerConfiguration;
import org.dphibernate.serialization.SimpleSerializationFactory;
import org.dphibernate.serialization.operations.LoadDPProxyBatchOperation;
import org.dphibernate.serialization.operations.LoadDPProxyOperation;
import org.dphibernate.utils.HibernateUtil;

import flex.messaging.Destination;
import flex.messaging.config.ConfigMap;
import flex.messaging.messages.Message;
import flex.messaging.messages.RemotingMessage;
import flex.messaging.services.remoting.adapters.JavaAdapter;

@SuppressWarnings("unchecked")
public class MyDLPRemotingAdapter extends JavaAdapter implements IAdapter
{
	private static final Log log = LogFactory.getLog(MyDLPRemotingAdapter.class);
	
	protected Destination destination;

	private HashMap<Class<? extends AdapterOperation>,AdapterOperation> operations;
	private ISerializerFactory serializerFactory;
	
	private final AdapterBuilder builder;
	public MyDLPRemotingAdapter()
	{
		super();
		builder = new AdapterBuilder(this);
	}

	/**
	 * Initialize the adapter properties from the flex services-config.xml file
	 */
	public void initialize(String id, ConfigMap properties)
	{
		super.initialize(id, properties);
		builder.build(properties);
	}
	
	public Object superInvoke(Message message)
	{
		return super.invoke(message);
	}


	/**
	 * Invoke the Object.method() called through FlashRemoting
	 */
	public Object invoke(Message message)
	{
		Object results = null;

		if (message instanceof RemotingMessage)
		{
			// RemotingDestinationControl remotingDestination =
			// (RemotingDestinationControl)this.getControl().getParentControl();//destination;
			RemotingMessage remotingMessage = (RemotingMessage) message;
			
			for (AdapterOperation operation : operations.values())
			{
				if (operation.appliesForMessage(remotingMessage))
				{
					operation.execute(remotingMessage);
				}
			}

			// invoke the user class.method()
			results = super.invoke(remotingMessage);
			log.debug("{invoke}");

			// serialize the result out
			try
			{
				ISerializer serializer = serializerFactory.getSerializer(results);
				results = serializer.serialize();
				log.debug("{serialize}");
			} catch (Exception ex)
			{
				ex.printStackTrace();
				// throw error back to flex
				// todo: replace with custom exception
				RuntimeException re = new RuntimeException(ex.getMessage());
				re.setStackTrace(ex.getStackTrace());
				throw re;
			}
		}

		return results;
	}

	protected IDeserializer getDeserializer()
	{
		return serializerFactory.getDeserializer();
	}

	public <T extends AdapterOperation> T getOperation(Class<T> operationClass)
	{
		return (T) operations.get(operationClass);
	}
	
	@Override
	public void putOperation(AdapterOperation operation)
	{
		if (operations == null)
		{
			operations = new HashMap<Class<? extends AdapterOperation>, AdapterOperation>();
		}
		operations.put(operation.getClass(), operation);
	}

	@Override
	public ISerializerFactory getSerializerFactory()
	{
		return serializerFactory;
	}

	@Override
	public void setSerializerFactory(ISerializerFactory serializerFactory)
	{
		this.serializerFactory = serializerFactory;
	}
}
