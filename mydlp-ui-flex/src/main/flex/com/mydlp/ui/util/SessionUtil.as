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
			var license:LicenseInformation = FlexGlobals.topLevelApplication.license as LicenseInformation;
			if(license.licenseType == LicenseInformation.ENTERPRISE_LICENSE)
				return true;
			else return false;
		}
		
		public static function isHardLimit():Boolean {
			var nDiffDays:Number = getDayInformation();
			if(nDiffDays > 30)
				return true;
			else return false;
		}
		
		public static function isSoftLimit():Boolean {
			var nDiffDays:Number = getDayInformation();
			if(nDiffDays > 0)
				return true;
			else return false;
		}
		
		public static function isExpirationDateNear():Boolean {
			var nDiffDays:Number = getDayInformation();
			if(nDiffDays < 0 && nDiffDays > -30)
				return true;
			else return false;
		}
		
		public static function getDayInformation():Number
		{
			var license:LicenseInformation = FlexGlobals.topLevelApplication.license as LicenseInformation;
			if(license == null) 
				return -1;
			var currentDate:Date = new Date();
			var expirationDate:Date = license.expirationDate;
			var nDiffDays:int = Math.floor((currentDate.getTime() - expirationDate.getTime())/86400000);
			return nDiffDays;
		}
	}
}