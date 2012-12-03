package com.mydlp.ui.tools.uploader  
{
	import flash.filesystem.File;
	import flash.filesystem.FileStream;
	import flash.utils.ByteArray;
	
	public class UploadStatus
	{
		public function UploadStatus()
		{
		}
		
		public var file:File = null;
		
		[Bindable]
		public var name:String = null;
		
		[Bindable]
		public var bytesTotal:Number = 0;
		
		[Bindable]
		public var viewState:String = "init";
		
		[Bindable]
		public var serverReason:String = "";
		
		public function resetData(): void
		{
			file = null;
		}
				
	}
}