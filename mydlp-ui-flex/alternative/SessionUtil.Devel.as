package com.mydlp.ui.util
{
	import com.mydlp.ui.domain.AuthUser;
	import com.mydlp.ui.domain.LicenseInformation;
	
	import flash.events.Event;
	
	import mx.controls.Alert;
	import mx.core.FlexGlobals;

	public class SessionUtil
	{
		
		public static function isEnterprise():Boolean {
			return false;
		}
		
		public static function isLicenseAvailable():Boolean {
			return true;
		}
		
		public static function isHardLimit():Boolean {
			return false;
		}
		
		public static function isSoftLimit():Boolean {
			return false;
		}
		
		public static function isExpirationDateNear():Boolean {
			return false;
		}
		
		public static function getDayInformation():Number
		{
			return 60;
		}
		
		public static function isDevelopment():Boolean {
			return true;
		}
		
		public static function hasRole(roleName:String):Boolean {
			return FlexGlobals.topLevelApplication.hasRole(roleName);
		}
	}
}