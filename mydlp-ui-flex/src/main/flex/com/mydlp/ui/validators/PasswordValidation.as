package com.mydlp.ui.validators
{
	import mx.validators.ValidationResult;
	import mx.validators.Validator;
	import com.mydlp.ui.util.LangUtil;

	public class PasswordValidation extends Validator
	{
		private var results:Array;
		
		public var confirmationSource:Object;
		public var confirmationProperty:String;
		
		public function PasswordValidation()
		{
			super();
		}
		
		override protected function doValidation(value:Object):Array
		{
			var results:Array = super.doValidation(value.password);
			
			if(value.password != value.confirmation)
			{
				results.push(new ValidationResult(true, null, "Mismatch", LangUtil.getString("messages", "setPassword.passwordValidation.retype.message")));
			}
			
			return results;
		}
		
		override protected function getValueFromSource():Object
		{
			var value:Object = {};
			value.password = super.getValueFromSource();
			if(confirmationSource && confirmationProperty)
			{
				value.confirmation = confirmationSource[confirmationProperty];
			}
			return value;
		}
	}
}