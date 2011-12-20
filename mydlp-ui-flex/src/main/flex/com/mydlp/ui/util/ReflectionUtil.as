package com.mydlp.ui.util
{
	import avmplus.getQualifiedClassName;
	
	import com.mydlp.ui.domain.AbstractEntity;
	import com.mydlp.ui.domain.InventoryCategory;
	import com.mydlp.ui.domain.InventoryItem;
	import com.mydlp.ui.domain.Item;
	
	import flash.utils.describeType;
	import flash.utils.getDefinitionByName;
	
	import mx.collections.ArrayList;
	import mx.collections.ListCollectionView;
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
					memberName == "icon" ||
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
		
		
		public static function cloneDomainObject(sourceObject:Object): *
		{
			var targetObject:*;
			
			if (sourceObject is AbstractEntity)
			{
				var targetClass:Class = getDefinitionByName(getQualifiedClassName(sourceObject)) as Class;
				targetObject = new targetClass();
				
				var classMembers:Array = ReflectionUtil.getClassMembers(sourceObject as Object);
				for each (var classMember:ClassMember in classMembers)
					if (classMember.name == "id" )
					{
						continue;
					}
					else if (
							(classMember.name == "children" && classMember.type == ListCollectionView) ||
							(classMember.name == "coupledInventoryItem" && classMember.type == InventoryItem)
						)
					{
						continue;
					}
					else if (classMember.name == "name" && classMember.type == String)
					{
						targetObject.name =
							LangUtil.getString("messages", "clone.name.cloneof") + 
							sourceObject.name;
					}
					else if (sourceObject is InventoryItem && classMember.name == "item")
					{
						targetObject.item = cloneDomainObject(sourceObject.item);
						(targetObject.item as Item).coupledInventoryItem = targetObject as InventoryItem;
					}
					else
					{
						targetObject[classMember.name] = sourceObject[classMember.name];
					}
			}
			return targetObject;
		}
	}
}