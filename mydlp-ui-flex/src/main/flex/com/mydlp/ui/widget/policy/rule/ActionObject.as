package com.mydlp.ui.widget.policy.rule
{
	import com.mydlp.ui.domain.CustomAction;
	import com.mydlp.ui.domain.Rule;
	
	import mx.core.IUID;

	public class ActionObject implements IUID
	{
		public var actionKey:String;
		public var customAction:CustomAction;
		
		public function ActionObject(_actionKey:String, _customAction:CustomAction = null)
		{
			if (_actionKey == Rule.ACTION_CUSTOM && (_customAction == null || isNaN(_customAction.id)))
				throw new Error("Can not nullify custom object of custom action");
			this.actionKey = _actionKey;
			this.customAction = _customAction;
		}
		
		private var _uid:String = null;
		
		private function generate_uid(): void
		{
			if (actionKey == Rule.ACTION_CUSTOM)
				_uid = actionKey + ":" + customAction.id;
			else
				_uid = actionKey;
		}
		
		public function get uid():String
		{
			if (_uid == null)
				generate_uid();
			return _uid;
		}
		
		public function set uid(value:String):void
		{
		}
	}
}