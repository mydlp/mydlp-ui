package com.mydlp.ui.framework.adapter;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dphibernate.collections.PaginatedCollection;
import org.dphibernate.core.HibernateProxyConstants;
import org.dphibernate.core.IHibernateProxy;
import org.dphibernate.persistence.state.IHibernateProxyDescriptor;
import org.dphibernate.serialization.AbstractSerializer;
import org.dphibernate.serialization.CollectionProxyResolver;
import org.dphibernate.serialization.DPHibernateCache;
import org.dphibernate.serialization.DiscriminatedCollectionProxyResolver;
import org.dphibernate.serialization.PropertyHelper;
import org.dphibernate.serialization.SerializerConfiguration;
import org.dphibernate.serialization.SingleTypeCollectionProxyResolver;
import org.dphibernate.serialization.TypeHelper;
import org.dphibernate.serialization.annotations.AggressivelyProxy;
import org.dphibernate.serialization.annotations.EagerlySerialize;
import org.dphibernate.serialization.annotations.NeverSerialize;
import org.dphibernate.serialization.annotations.NoLazyLoadOnSerialize;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.collection.AbstractPersistentCollection;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.collection.PersistentMap;
import org.hibernate.dialect.Dialect;
import org.hibernate.event.EventSource;
import org.hibernate.impl.SessionFactoryImpl;
import org.hibernate.persister.collection.AbstractCollectionPersister;
import org.hibernate.persister.collection.CollectionPersister;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import flex.messaging.io.amf.ASObject;

@SuppressWarnings("unchecked")
public class MyDLPHibernateSerializer extends AbstractSerializer {
	private static final Log log = LogFactory
			.getLog(MyDLPHibernateSerializer.class);
	private static final int DEFAULT_PAGESIZE = -1;
	public static Dialect dialect;

	public MyDLPHibernateSerializer(Object source, boolean useAggressiveProxying) {
		super(source);
		this.useAggressiveProxying = useAggressiveProxying;
		this.cache = new DPHibernateCache();
	}

	public MyDLPHibernateSerializer(Object source) {
		this(source, false);
	}

	public MyDLPHibernateSerializer(Object source,
			boolean useAggressiveProxying, DPHibernateCache cache,
			SessionFactory sessionFactory) {
		this(source, useAggressiveProxying);
		this.cache = cache;
		this.sessionFactory = sessionFactory;
	}

	private DPHibernateCache cache;

	@Autowired
	@Qualifier("policySessionFactory")
	private SessionFactory sessionFactory;

	private int pageSize = -1;

	private boolean useAggressiveProxying;
	private boolean permitAgressiveProxyingOnRoot;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	/**
	 * Serializes the source object.
	 * This is the public entry point into serialization
	 */
	public Object serialize() {
		return serialize(getSource());
	}

	/**
	 * Private serialization for members of getSource(). Called recursively
	 * during serialization
	 */
	private Object serialize(Object source) {
		return serialize(source, false);
	}

	/**
	 * Private serialization for members of getSource(). Called recursively
	 * during serialization
	 */

	private Object serialize(Object objectToSerialize, boolean eagerlySerialize) {
		if (objectToSerialize == null) {
			return null;
		}

		Object result = null;

		Object key = cache.getCacheKey(objectToSerialize);

		if (cache.contains(key)) {
			return cache.get(key);
		}
		result = writeBean(objectToSerialize, key, eagerlySerialize);

		return result;
	}

	private boolean isLazyProxy(Object obj) {
		return obj instanceof HibernateProxy
				&& (((HibernateProxy) obj).getHibernateLazyInitializer()
						.isUninitialized());
	}

	private Object writeBean(Object objectToSerialize, Object cacheKey,
			boolean eagerlySerialize) {
		Object result = null;
		// TODO : This should use a strategy pattern.
		if (isLazyProxy(objectToSerialize) && !eagerlySerialize) {
			result = writeHibernateProxy((HibernateProxy) objectToSerialize,
					cacheKey);
		} else if (shouldAggressivelyProxy(objectToSerialize, eagerlySerialize)) {
			Object proxyKey = ((IHibernateProxy) objectToSerialize)
					.getProxyKey();
			result = generateDpHibernateProxy(objectToSerialize, proxyKey,
					cacheKey);
		} else if (objectToSerialize instanceof PersistentMap) {
			result = writePersistantMap(objectToSerialize, result, cacheKey);
		} else if (objectToSerialize instanceof AbstractPersistentCollection) {
			result = writeAbstractPersistentCollection(
					(AbstractPersistentCollection) objectToSerialize, cacheKey,
					eagerlySerialize);
		} else if (objectToSerialize instanceof byte[]) {
			cache.store(cacheKey, objectToSerialize);
			result = objectToSerialize;
		} else if (objectToSerialize.getClass().isArray()) {
			result = writeArray((Object[]) objectToSerialize, cacheKey);
		} else if (objectToSerialize instanceof Collection) {
			result = writeCollection((Collection<?>) objectToSerialize,
					cacheKey);
		} else if (objectToSerialize instanceof Map) {
			result = writeMap(objectToSerialize, cacheKey);
		} else if (objectToSerialize instanceof IHibernateProxy) {
			result = writeBean(objectToSerialize, cacheKey);
		} else if (objectToSerialize instanceof Object
				&& (!TypeHelper.isSimple(objectToSerialize))
				&& !(objectToSerialize instanceof ASObject)) {
			result = writeBean(objectToSerialize, cacheKey);
		} else {
			cache.store(cacheKey, objectToSerialize);
			result = objectToSerialize;
		}
		return result;
	}

	private boolean shouldAggressivelyProxy(Object objectToSerialize,
			boolean eagerlySerialize) {
		if (eagerlySerialize)
			return false;
		if (hasAnnotation(objectToSerialize, AggressivelyProxy.class))
			return true;
		if (!useAggressiveProxying)
			return false;
		return !sourceContainsProperty(objectToSerialize)
				&& canBeAgressivelyProxied(objectToSerialize);
	}

	private boolean hasAnnotation(Object objectToSerialize,
			Class<? extends Annotation> annotation) {
		return Object.class.getAnnotation(annotation) != null;
	}

	private boolean canBeAgressivelyProxied(Object objectToSerialize) {
		if (!(objectToSerialize instanceof IHibernateProxy))
			return false;

		if (isRootObject(objectToSerialize)) {
			return permitAgressiveProxyingOnRoot;
		}

		return true;
	}

	private boolean isRootObject(Object objectToSerialize) {
		return objectToSerialize == getSource();
	}

	private ASObject writeBean(Object obj, Object cacheKey) {
		String propName;
		ASObject asObject = new ASObject();
		try {
			cache.store(cacheKey, asObject);

			asObject.setType(getClassName(obj));
			if (obj instanceof IHibernateProxy) {
				asObject.put(HibernateProxyConstants.UID, UUID.randomUUID()
						.toString());
				asObject.put(HibernateProxyConstants.PROXYINITIALIZED, true);
				Object primaryKey = ((IHibernateProxy) obj).getProxyKey();
				asObject.put(HibernateProxyConstants.PKEY, primaryKey);
			}

			// TODO : This chunk of code is being progressively moved to
			// PropertyHelper.java
			// However, we need better test coverage of this method before I'm
			// comfortable just ripping it out
			BeanInfo info = Introspector.getBeanInfo(obj.getClass());
			for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
				propName = pd.getName();
				Method readMethod = pd.getReadMethod();
				if (readMethod == null)
					continue;
				boolean explicitlyFetch = PropertyHelper.methodHasAnnotation(
						readMethod, NoLazyLoadOnSerialize.class);
				if (explicitlyFetch)
					continue;
				if (PropertyHelper.propertyNameIsExcluded(propName)) {
					continue;
				}
				try {
					Object val = readMethod.invoke(obj, null);
					boolean neverSerialize = (readMethod
							.getAnnotation(NeverSerialize.class) != null);
					if (neverSerialize) {
						continue;
					}
					Object serializedValue;
					if (PropertyHelper.methodHasAnnotation(readMethod,
							AggressivelyProxy.class)) {
						MyDLPHibernateSerializer aggressiveSerializer = new MyDLPHibernateSerializer(
								val, true, cache, sessionFactory);
						aggressiveSerializer.permitAgressiveProxyingOnRoot = true;
						serializedValue = aggressiveSerializer.serialize();
					} else {
						boolean eagerlySerialize = PropertyHelper
								.methodHasAnnotation(readMethod,
										EagerlySerialize.class);
						serializedValue = serialize(val, eagerlySerialize);
					}
					asObject.put(propName, serializedValue);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return asObject;
	}

	private Object writeCollection(Collection<?> collection, Object key) {
		List list = new ArrayList();
		boolean isPaginated = false;
		for (Object collectionMemeber : collection) {
			Object translatedCollectionMember;
			Object collectionMemeberCacheKey = cache
					.getCacheKey(collectionMemeber);
			if (pageSize != DEFAULT_PAGESIZE && list.size() > pageSize) {
				translatedCollectionMember = getPagedCollectionProxy(
						collectionMemeber, collectionMemeberCacheKey);
				isPaginated = true;
			} else {
				translatedCollectionMember = serialize(collectionMemeber);
			}
			list.add(translatedCollectionMember);
		}
		if (isPaginated) {
			// list = convertToPaginatedList(list);
		}
		return list;
	}

	private List convertToPaginatedList(List list) {
		return new PaginatedCollection(list);
	}

	private Object getPagedCollectionProxy(Object collectionMemeber,
			Object cacheKey) {
		if (isLazyProxy(collectionMemeber)) {
			return writeHibernateProxy((HibernateProxy) collectionMemeber,
					cacheKey);
		} else if (collectionMemeber instanceof IHibernateProxy) {
			return generateDpHibernateProxy(collectionMemeber,
					((IHibernateProxy) collectionMemeber).getProxyKey(),
					cacheKey);
		} else { // Default... we can't provide a proxy for this item, so
					// translate it.
			return serialize(collectionMemeber);
		}

	}

	private Object writeArray(Object[] obj, Object key) {
		Object result;
		ArrayList list = new ArrayList();
		for (Object member : obj) {
			result = serialize(member);
			list.add(result);
		}
		return list.toArray();
	}

	private ASObject writeMap(Object obj, Object key) {
		if (obj instanceof ASObject) {
			return (ASObject) obj;
		}

		ASObject asObj = new ASObject();
		asObj.setType(getClassName(obj));

		cache.store(key, asObj);

		Set keys = ((Map) obj).keySet();
		Iterator keysItr = keys.iterator();
		while (keysItr.hasNext()) {
			Object thisKey = keysItr.next();
			Object o = ((Map) obj).get(thisKey);
			asObj.put(thisKey, serialize(o));
		}
		return asObj;
	}

	private Object writeAbstractPersistentCollection(
			AbstractPersistentCollection collection, Object key,
			boolean eagerlySerialize) {
		Object result;
		if (!collection.wasInitialized() && !eagerlySerialize) {
			// go load our Collection of dpHibernateProxy objects
			List proxies = getCollectionProxies(collection);

			proxies = (List) serialize(proxies);
			result = proxies;

			cache.store(key, proxies);
			// return proxies;
		} else {
			if (!collection.wasInitialized()) {
				collection.forceInitialization();
			}
			Object c = collection.getValue();
			List items = new ArrayList();
			cache.store(key, items);

			Iterator itr = collection.entries(null);
			while (itr.hasNext()) {
				Object next = itr.next();
				Object newObj = serialize(next);
				items.add(newObj);
			}

			result = items;
		}
		// DEBUGGING .. Remove
		if (collection.getRole().contains("users") && result == null) {
			result = writeAbstractPersistentCollection(collection, key,
					eagerlySerialize);
		}
		return result;
	}

	private Object writePersistantMap(Object obj, Object result, Object key) {
		if (((PersistentMap) obj).wasInitialized()) {
			HashMap map = new HashMap();
			// Set entries = ((PersistentMap)obj).entrySet();
			Set keys = ((PersistentMap) obj).keySet();

			Iterator keyItr = keys.iterator();
			while (keyItr.hasNext()) {
				Object mapKey = keyItr.next();
				map.put(mapKey, ((PersistentMap) obj).get(mapKey));
			}

			cache.store(key, map);
			result = map;
		} else {
			// todo
			throw new RuntimeException("Lazy loaded maps not implimented yet.");
		}
		return result;
	}

	private ASObject writeHibernateProxy(HibernateProxy obj, Object key) {
		Object primaryKey = obj.getHibernateLazyInitializer().getIdentifier();
		return generateDpHibernateProxy(obj, primaryKey, key);
	}

	private ASObject generateDpHibernateProxy(Object obj,
			Object objectIdentifier, Object cacheKey) {
		ASObject as = new ASObject();
		as.setType(getClassName(obj));
		as.put(HibernateProxyConstants.UID, UUID.randomUUID().toString());
		as.put(HibernateProxyConstants.PKEY, objectIdentifier);
		as.put(HibernateProxyConstants.PROXYINITIALIZED, false);// !hibProxy.getHibernateLazyInitializer().isUninitialized());

		cache.store(cacheKey, as);
		return as;
	}

	private String getClassName(Object obj) {
		if (obj instanceof ASObject) {
			return ((ASObject) obj).getType();
		} else if (obj instanceof HibernateProxy) {
			return ((HibernateProxy) obj).getHibernateLazyInitializer()
					.getPersistentClass().getName().toString();
		} else {
			return obj.getClass().getName();
		}
	}

	List getCollectionProxies(PersistentCollection collection) {
		try {
			EventSource session = (EventSource) getSessionFactory()
					.getCurrentSession();

			CollectionPersister persister = session.getFactory()
					.getCollectionPersister(collection.getRole());
			if (persister instanceof AbstractCollectionPersister) {
				AbstractCollectionPersister collectionPersister = (AbstractCollectionPersister) persister;
				String className = collectionPersister.getElementType()
						.getName();
				EntityPersister entityPersister = session.getFactory()
						.getEntityPersister(className);
				CollectionProxyResolver collectionProxyResolver = getCollectionProxyResolver(
						collectionPersister, entityPersister);
				if (session instanceof Session) {
					List<IHibernateProxyDescriptor> proxyDescriptors = collectionProxyResolver
							.getCollectionProxies(session, collection);

					// create a new HibernateProxy for each id.
					List<ASObject> proxies = new ArrayList<ASObject>();
					for (IHibernateProxyDescriptor proxyDescriptor : proxyDescriptors) {
						// create flex object to represent the
						// PersistanceProxy
						ASObject as = new ASObject();// new
						// ExternalASObject();
						as.setType(proxyDescriptor.getRemoteClassName());
						as.put(HibernateProxyConstants.UID, UUID.randomUUID()
								.toString());
						as.put(HibernateProxyConstants.PKEY,
								proxyDescriptor.getProxyId());
						as.put(HibernateProxyConstants.PROXYINITIALIZED, false);
						proxies.add(as);
					}
					return proxies;
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private CollectionProxyResolver getCollectionProxyResolver(
			AbstractCollectionPersister collectionPersister,
			EntityPersister entityPersister) {
		if (entityPersister instanceof SingleTableEntityPersister) {
			if (((SingleTableEntityPersister) entityPersister)
					.getDiscriminatorColumnName() != null) {
				return new DiscriminatedCollectionProxyResolver(getDialect(),
						(SingleTableEntityPersister) entityPersister,
						collectionPersister);
			}
		}
		return new SingleTypeCollectionProxyResolver(getDialect(),
				collectionPersister);
	}

	private Dialect getDialect() {
		if (MyDLPHibernateSerializer.dialect == null) {
			try {
				MyDLPHibernateSerializer.dialect = Dialect.getDialect();
			} catch (Throwable t) {
				SessionFactoryImpl sfi = (SessionFactoryImpl) sessionFactory;
				MyDLPHibernateSerializer.dialect = sfi.getDialect();
			}
			if (MyDLPHibernateSerializer.dialect == null) {
				throw new RuntimeException("Could not determine dialect");
			}
		}
		return MyDLPHibernateSerializer.dialect;

	}

	boolean sourceContainsProperty(Object member) {
		for (Object propertyValue : getSourcePropertyValues()) {
			if (propertyValue == member)
				return true;
		}
		return false;
	}

	private List<Object> sourcePropertyValues;

	private List<Object> getSourcePropertyValues() {
		if (sourcePropertyValues == null) {
			sourcePropertyValues = getPropertyValues(getSource());
		}
		return sourcePropertyValues;
	}

	private List<Object> getPropertyValues(Object member) {
		List<Object> result = new ArrayList<Object>();
		BeanInfo info;
		try {
			info = Introspector.getBeanInfo(member.getClass());

			for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
				String propName = pd.getName();
				Method readMethod = pd.getReadMethod();
				if (readMethod == null)
					continue;
				if (PropertyHelper.propertyNameIsExcluded(propName)) {
					continue;
				}
				Object val = readMethod.invoke(member, null);
				if (val != null)
					result.add(val);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	public void setCache(DPHibernateCache cache) {
		this.cache = cache;
	}

	public DPHibernateCache getCache() {
		return cache;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setUseAggressiveProxying(boolean useAggressiveProxying) {
		this.useAggressiveProxying = useAggressiveProxying;
	}

	public boolean isUsingAggressiveProxying() {
		return useAggressiveProxying;
	}

	@Override
	public void configure(SerializerConfiguration configuration) {
		if (pageSize == DEFAULT_PAGESIZE)
			this.pageSize = configuration.getPageSize();
	}
}
