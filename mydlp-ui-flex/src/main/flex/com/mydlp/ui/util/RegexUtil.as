package com.mydlp.ui.util
{
	public class RegexUtil
	{
		public static function domainOrIpRegex(): String
		{
			return "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\-]*[a-zA-Z0-9])\.)*([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\-]*[a-zA-Z0-9])$";
		}
		
		public static function urlRegex(): String
		{
			return "^[a-zA-Z\-\/\ ]+$";
		}
	}
}