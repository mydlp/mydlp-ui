package com.mydlp.ui.flex.api
{
	import flash.utils.flash_proxy;
	
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	
	import org.dphibernate.rpc.HibernateRemoteObject;
	import org.dphibernate.rpc.IOperationBufferFactory;
	
	use namespace flash_proxy;
	
	dynamic public class RemoteEntity extends HibernateRemoteObject
	{
		
		public function RemoteEntity(destination:String=null, operationBufferFactory:IOperationBufferFactory=null)
		{
			super(destination, operationBufferFactory);
			addEventListener(FaultEvent.FAULT, faultHandler);
		}
		
		protected function faultHandler(event:FaultEvent):void
		{
			Alert.show("Fault when loading data: " + event.fault.faultString);
		}
		
		override flash_proxy function callProperty(name:*, ... args:Array):*
		{
			return super.callProperty(name, args);
		}
		
	}
}