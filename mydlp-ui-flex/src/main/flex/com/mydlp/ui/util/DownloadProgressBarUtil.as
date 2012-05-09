package com.mydlp.ui.util
{
	import flash.events.ProgressEvent;
	
	import mx.preloaders.SparkDownloadProgressBar;

	public class DownloadProgressBarUtil extends SparkDownloadProgressBar
	{
		public function DownloadProgressBarUtil()
		{
			super();
		}
		
		[Embed(source="/assets/images/logo_314x124_withSpace.png")]
		[Bindable]
		public var imgClass:Class
		
		override public function get backgroundImage():Object
		{
			return imgClass;
		}
		
		override public function get backgroundSize():String
		{
			return "20%";
		}
		
		override protected function showDisplayForInit(elapsedTime:int, count:int):Boolean
		{
			return true;
		}
		
		override protected function showDisplayForDownloading(elapsedTime:int, event:ProgressEvent):Boolean
		{
			return true;
		}
	}
}