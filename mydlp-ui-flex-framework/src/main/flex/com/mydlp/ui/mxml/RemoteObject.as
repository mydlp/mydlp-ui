package com.mydlp.ui.mxml
{
	import flash.net.URLRequest;
	import flash.net.navigateToURL;
	import flash.utils.flash_proxy;
	
	import mx.core.FlexGlobals;
	import mx.rpc.AbstractOperation;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.InvokeEvent;
	import mx.rpc.events.ResultEvent;
	
	import org.dphibernate.rpc.HibernateRemoteObject;
	import org.dphibernate.rpc.IOperationBufferFactory;
	import org.dphibernate.rpc.LoadDPProxyOperationBufferFactory;
	
	use namespace flash_proxy;
	
	[DefaultProperty("operationList")]

	public dynamic class RemoteObject extends HibernateRemoteObject
	{
		protected static var operationCounter:uint = 0;
		
		public function RemoteObject(destination:String=null, operationBufferFactory:IOperationBufferFactory=null)
		{
			super(destination, operationBufferFactory);
			//this.bufferProxyLoadRequests = true;
			this.showBusyCursor=true;
			this.operationBufferFactory = new LoadDPProxyOperationBufferFactory();
			addEventListener(InvokeEvent.INVOKE, screenInvokeHandler);
			addEventListener(ResultEvent.RESULT, screenResultHandler);
			addEventListener(FaultEvent.FAULT, screenFaultHandler);
			addEventListener(FaultEvent.FAULT, defaultFaultHandler);
		}
		
		protected function defaultFaultHandler(event:FaultEvent):void {
			if (event.fault.faultCode == "Client.Error.DeliveryInDoubt")
				navigateToURL(new URLRequest('j_spring_security_logout'), '_self');
		}
		
		protected function screenInvokeHandler(event:InvokeEvent): void
		{
			disableScreen();
		}
		
		protected function screenResultHandler(event:ResultEvent): void
		{
			enableScreen();
		}
		
		protected function screenFaultHandler(event:FaultEvent): void
		{
			enableScreen();
		}
		
		public static function disableScreen(): void
		{
			if (RemoteObject.operationCounter <= 0)
			{
				FlexGlobals.topLevelApplication.disableScreen();
				RemoteObject.operationCounter = 0;
			}
			RemoteObject.operationCounter++;
		}
		
		public static function enableScreen(): void
		{
			RemoteObject.operationCounter--;
			if (RemoteObject.operationCounter <= 0)
			{
				FlexGlobals.topLevelApplication.enableScreen();
				RemoteObject.operationCounter = 0;
			}
		}
		

		/*
		override flash_proxy function callProperty(name:*, ... args:Array):*
		{
			return super.callProperty(name, args);
		}
		
		override flash_proxy function callProperty(name:*):*
		{
			return super.callProperty(name);
		}
		*/
		
		/**
		 * This serves as the default property for this instance so that we can
		 * define a set of operations as direct children of the RemoteObject
		 * tag in MXML.
		 */
		public function set operationList(ol:Array):void
		{
			if (ol == null)
				operations = null;
			else
			{
				var op:AbstractOperation;
				var ops:Object = new Object();
				for (var i:int = 0; i < ol.length; i++)
				{
					op = AbstractOperation(ol[i]);
					var name:String = op.name;
					if (!name)
						throw new ArgumentError("Operations must have a name property value for MyDLPRemoteObject");
					ops[name] = op;
				}
				operations = ops;
			}
		}
		
		public function get operationList():Array
		{
			// Note: does not preserve order of the elements
			if (operations == null)
				return null;
			var ol:Array = new Array();
			for (var i:String in operations)
			{
				var op:AbstractOperation = operations[i];
				ol.push(op);
			}
			return ol
		}
	}
}