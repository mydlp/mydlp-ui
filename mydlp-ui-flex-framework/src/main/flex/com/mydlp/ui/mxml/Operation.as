package com.mydlp.ui.mxml
{
	import flash.net.URLRequest;
	import flash.net.navigateToURL;
	
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.mxml.Operation;
	
	public class Operation extends mx.rpc.remoting.mxml.Operation
	{
		public function Operation(remoteObject:RemoteObject=null, name:String=null)
		{
			super(remoteObject, name);
			addEventListener(ResultEvent.RESULT, screenResultHandler);
			addEventListener(FaultEvent.FAULT, screenFaultHandler);
			addEventListener(FaultEvent.FAULT, defaultFaultHandlerInt);
		}
		
		protected function defaultFaultHandlerInt(event:FaultEvent):void {
			RemoteObject.defaultFaultHandler(event);
		}
		
		protected function screenResultHandler(event:ResultEvent): void
		{
			RemoteObject.enableScreen();
		}
		
		protected function screenFaultHandler(event:FaultEvent): void
		{
			RemoteObject.enableScreen();
		}

	}
}