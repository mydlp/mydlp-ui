package com.mydlp.ui.widget.general.input
{
	import com.mydlp.ui.util.LangUtil;

	public class MatcherDefinition
	{
		private var _data:String;
		
		public function get data(): String
		{
			return _data;
		}
		
		public function set data(value:String): void
		{
			_data = value;
		}
		
		public function get label(): String
		{
			return LangUtil.getString("messages", "matchers." + data + ".label");
		}
	}
}