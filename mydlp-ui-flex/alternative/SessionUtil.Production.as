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
			if (FlexGlobals.topLevelApplication.license == null)
				return false;
			var license:LicenseInformation = FlexGlobals.topLevelApplication.license as LicenseInformation;
			if(license.licenseType == LicenseInformation.ENTERPRISE_LICENSE || license.licenseType == LicenseInformation.TRIAL_LICENSE)
				return true;
			else return false;
		}
		
		public static function isLicenseAvailable():Boolean {
			if(FlexGlobals.topLevelApplication.license == null)
				return false;
			else return true;
		}
		
		public static function isHardLimit():Boolean {
			if (FlexGlobals.topLevelApplication.license != null &&
				FlexGlobals.topLevelApplication.license.expirationDate == 0)
				return true;
			var nDiffDays:Number = getDayInformation();
			if(nDiffDays > 30)
				return true;
			else return false;
		}
		
		public static function isSoftLimit():Boolean {
			if (FlexGlobals.topLevelApplication.license != null &&
				FlexGlobals.topLevelApplication.license.expirationDate == 0)
				return true;
			var nDiffDays:Number = getDayInformation();
			if(nDiffDays > 0)
				return true;
			else return false;
		}
		
		public static function isExpirationDateNear():Boolean {
			if (FlexGlobals.topLevelApplication.license != null &&
				FlexGlobals.topLevelApplication.license.expirationDate == 0)
				return false;
			var nDiffDays:Number = getDayInformation();
			if(nDiffDays < 0 && nDiffDays > -30)
				return true;
			else return false;
		}
		
		public static function getDayInformation():Number
		{
			if (FlexGlobals.topLevelApplication.license == null)
				return -60;
			var license:LicenseInformation = FlexGlobals.topLevelApplication.license as LicenseInformation;
			if(license == null) 
				return Number.NaN;
			if (license.expirationDate == 0)
				return Number.NaN;
			var currentDate:Date = new Date();
			var nDiffDays:int = Math.floor((currentDate.getTime() - (license.expirationDate*1000))/86400000);
			return nDiffDays;
		}
	}
}