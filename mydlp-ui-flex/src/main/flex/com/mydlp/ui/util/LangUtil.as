package com.mydlp.ui.util
{
	import mx.resources.ResourceManager;

	[ResourceBundle("messages")]
	public class LangUtil
	{
		public static function getString(module:String, key:String): String
		{
			var value:String = ResourceManager.getInstance().getString( module, key );
			
			return value ? value : key;
		}
	}
}