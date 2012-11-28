package com.mydlp.ui.validators
{
	import com.mydlp.ui.util.LangUtil;
	
	import spark.validators.NumberValidator;
	
	public class MyNumberValidator extends NumberValidator
	{
		public function MyNumberValidator()
		{
			super();
			this.domain = "int";
			this.property = "text";
			this.parseError = LangUtil.getString("messages", "validators.numberValidator.parseError.message");
		}
	}
}