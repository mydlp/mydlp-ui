package com.mydlp.ui.util
{
	import com.mydlp.ui.domain.InventoryCategory;
	
	import flash.utils.describeType;
	import flash.utils.getDefinitionByName;
	
	import mx.collections.ArrayList;
	import mx.controls.Alert;

	public class ReflectionUtil
	{
		public static function getClassMembers(className:*): Array
		{
			var classMembers:ArrayList = new ArrayList();
			var description:XML = describeType(className);
			
			for each (var i:XML in description..accessor){
				var memberName:String = i.@name;
				if (
					memberName == "prototype" ||
					memberName == "proxyInitialized" ||
					memberName == "proxyKey" ||
					memberName == "stateManaged" ||
					memberName == "uid" ||
					memberName == "label"
					)
					continue;
				var classMember:ClassMember = new ClassMember();
				classMember.name = memberName;
				var memberType:String = i.@type;
				classMember.type = getDefinitionByName(memberType) as Class;
				classMembers.addItem(classMember);
			}
			return classMembers.toArray();
		}
		
		public static function hasMember(className:*, memberName:String): Boolean
		{
			var classMembers:Array = ReflectionUtil.getClassMembers(className);
			for each (var classMember:ClassMember in classMembers)
				if (classMember.name == memberName)
					return true;
			return false;
		}
		
		public static function hasMemberOfType(className:*, memberName:String, memberType:Class): Boolean
		{
			var classMembers:Array = ReflectionUtil.getClassMembers(className);
			for each (var classMember:ClassMember in classMembers)
			if (classMember.name == memberName && classMember.type == memberType)
				return true;
			return false;
		}
		
		public static function hasMemberType(className:*, memberType:Class): Boolean
		{
			var classMembers:Array = ReflectionUtil.getClassMembers(className);
			for each (var classMember:ClassMember in classMembers)
			if (classMember.type == memberType)
				return true;
			return false;
		}
	}
}