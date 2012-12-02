package com.mydlp.ui.tools.uploader  
{
	import flash.filesystem.File;
	import flash.utils.ByteArray;
	
	[Bindable]
	public class UploadStatus
	{
		public function UploadStatus()
		{
		}
		
		public var isStarted:Boolean = false;
		
		public var isUploaded:Boolean = false;
		
		public var file:File = null;
		
		public var name:String = null;
		
		public var size:Number = 0;
		
		public var viewState:String = "init";
		
	}
}