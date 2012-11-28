package com.mydlp.ui.validators
{
	import com.mydlp.ui.util.LangUtil;
	
	import mx.validators.ValidationResult;
	import mx.validators.Validator;
	
	public class NullValidator extends Validator {
		
		// Define Array for the return value of doValidation().
		private var results:Array;
		
		public function NullValidator() {
			super();
		}
		
		override protected function doValidation(value:Object):Array {
			
			// Clear results Array.
			results = [];
			
			// Call base class doValidation().
			results = super.doValidation(value);        
			// Return if there are errors.
			if (results.length > 0)
				return results;
			
			// Check first name field. 
			if (value == null) {
				results.push(new ValidationResult(true, 
					"", "isNull", LangUtil.getString("messages", "validators.isNull")));
			}
			
			return results;
		}
	}
}