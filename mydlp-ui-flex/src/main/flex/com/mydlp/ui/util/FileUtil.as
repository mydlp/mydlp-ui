package com.mydlp.ui.util
{	
	import mx.collections.ArrayCollection;

	public class FileUtil
	{
		public static function getHumanReadableSize(size:Number): String
		{			
			if(size < 1024)
				return size + " B";
			
			size /= 1024;
			if(size < 1024)
				return size.toFixed(2) + " KB";
			
			size /= 1024;
			if(size < 1024)
				return size.toFixed(2) + " MB";
			
			size /= 1024;
			if(size < 1024)
				return size.toFixed(2) + " GB";
			
			else
				return size.toFixed(2) + " GB";

		}
		
		public static function splitString(str:String):ArrayCollection
		{
			var result:ArrayCollection = new ArrayCollection;
			var temp:Array;
			temp = str.split("\n");
			for each(var mem:String in temp)
			{
				var keywords:Array;
				keywords = mem.split(" ");
				for each(var keyword:String in keywords)
					result.addItem(keyword);
			}
			return result;
		}
	}
}