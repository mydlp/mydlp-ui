package com.mydlp.ui.validators
{
	import mx.states.OverrideBase;
	import mx.validators.RegExpValidator;
	
		
	public class MyRegExValidator extends RegExpValidator
	{
		protected var DOMAIN_IP:String = "domain_ip";
		protected var IP:String = "ip";
		protected var URL:String = "url";
		
		protected var _type:String;
		
		public function MyRegExValidator()
		{
			super();
			property = "text";
		}
		
		public function get type():String {
			return _type;
		}
		
		public function set type(value:String):void {
			_type = value;
			if(_type == DOMAIN_IP)
			{
				this.noMatchError = "This is not a valid domain or ip";
			}
			else if(_type == IP)
			{
				this.noMatchError = "This is not a valid ip";
			}
			else if(_type == URL)
			{
				this.noMatchError = "This is not a valid url";
			}
		}
		
		public static function  domainNameOrIpRegex():String {
			return "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\-]*[a-zA-Z0-9])\.)*([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\-]*[a-zA-Z0-9])$"; 
		}
		
		public static function  uriRegex():String {
			return "^(http(s?)://[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{1,6}(/?|(/[\w-]+)*)(/?|/\w+\.[a-zA-Z]{2,4}(/?[\w]+\=[\w\-]+)?)?(?:\&[\w]+=[\w-]+)*)$"; 
		}
		
		public static function  ipRegex():String {
			return "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
		}
		
		public static function  urlRegex():String {
			return "^[a-zA-Z\-\/\ ]+$";
		}
		
		public static function domain_ip():String {
			return "domain_ip";
		}
		
		public static function ip():String {
			return "ip";
		}
		
		public static function url():String {
			return "url";
		}
	}
}