package com.mydlp.ui.util
{
	import com.mydlp.ui.domain.InventoryCategory;
	
	import flash.utils.describeType;
	import flash.utils.getDefinitionByName;
	
	import mx.collections.ArrayList;
	import mx.controls.Alert;

	public class ReflectionUtil
	{
		public static function getClassMembers(className:Class): Array
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
		
	}
}