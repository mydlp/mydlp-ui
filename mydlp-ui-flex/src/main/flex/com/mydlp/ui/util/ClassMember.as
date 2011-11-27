package com.mydlp.ui.util
{
	public class ClassMember
	{
		private var _name:String;
		private var _type:Class;
		
		public function set name(value:String):void {
			_name = value;
		}
		public function get name():String {
			return _name;
		}
		public function set type(value:Class):void {
			_type = value;
		}
		public function get type():Class{
			return _type;
		}
	}
}