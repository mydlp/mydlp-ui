package com.mydlp.ui.mxml
{
	import flash.net.URLRequest;
	import flash.net.navigateToURL;
	
	import mx.rpc.events.FaultEvent;
	import mx.rpc.remoting.mxml.Operation;
	
	public class Operation extends mx.rpc.remoting.mxml.Operation
	{
		public function Operation(remoteObject:RemoteObject=null, name:String=null)
		{
			super(remoteObject, name);
			addEventListener(FaultEvent.FAULT, defaultFaultHandler);
		}
		
		protected function defaultFaultHandler(event:FaultEvent):void {
			if (event.fault.faultCode == "Client.Error.DeliveryInDoubt")
				navigateToURL(new URLRequest('j_spring_security_logout'), '_self');
		}

	}
}