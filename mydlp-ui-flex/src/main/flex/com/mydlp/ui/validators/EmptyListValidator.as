package com.mydlp.ui.validators
{
	import com.mydlp.ui.util.LangUtil;
	
	import mx.collections.ListCollectionView;
	import mx.validators.ValidationResult;
	import mx.validators.Validator;
	
	public class EmptyListValidator extends Validator {
		
		// Define Array for the return value of doValidation().
		private var results:Array;
		
		public function EmptyListValidator() {
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
			if ( value == null ||
					(! value is ListCollectionView) ||
					(value as ListCollectionView).length <= 0
				) {
				results.push(new ValidationResult(true, 
					"", "emptyList", LangUtil.getString("messages", "validators.emptyList")));
			}
			
			return results;
		}
	}
}