package com.mydlp.ui.util
{
	import mx.controls.Alert;

	public class SpecialCharacterUtil
	{
		public static var regex:String = "REGEX";
		public static var solr:String = "SOLR";
		
		public static function escapeSpecialCharacters(string:String, category:String ) : String
		{
			var resultString:String = new String;
			if(category == solr)
			{
				for(var i:int = 0; i < string.length;)
				{
					if(string.charAt(i) == '^' || string.charAt(i) == '\"' || string.charAt(i) == '~' || string.charAt(i) == '*' ||
						string.charAt(i) == '?' || string.charAt(i) == ':' || string.charAt(i) == '\\' || string.charAt(i) == '+' ||
						string.charAt(i) == '-' || string.charAt(i) == '!' || string.charAt(i) == '(' || string.charAt(i) == ')' ||
						string.charAt(i) == '{' || string.charAt(i) == '}' || string.charAt(i) == '[' || string.charAt(i) == ']')
					{
						resultString = resultString.concat('\\');
						resultString = resultString.concat(string.charAt(i));
						i++;
					}
					else if(string.charAt(i) == '&')
					{
						if(string.length - 1 == i)
						{
							resultString = resultString.concat(string.charAt(i));
							i++;
						}
						else if(string.charAt(i+1) == '&')
						{
							resultString = resultString.concat('\\');
							resultString = resultString.concat(string.charAt(i));
							resultString = resultString.concat('\\');
							resultString = resultString.concat(string.charAt(i+1));
							i += 2;
						}
						else
						{
							resultString = resultString.concat(string.charAt(i));
							i++;
						}
					}
					else if(string.charAt(i) == '|')
					{
						if(string.length - 1 == i)
						{
							resultString = resultString.concat(string.charAt(i));
							i++;
						}
						else if(string.charAt(i+1) == '|')
						{
							resultString = resultString.concat('\\');
							resultString = resultString.concat(string.charAt(i));
							resultString = resultString.concat('\\');
							resultString = resultString.concat(string.charAt(i+1));
							i += 2;
						}
						else
						{
							resultString = resultString.concat(string.charAt(i));
							i++;
						}
					}
					else
					{
						resultString = resultString.concat(string.charAt(i));
						i++;
					}
				}
			}
			else if(category == regex)
			{
				for(var j:int = 0; j < string.length; j++)
				{
					if(string.charAt(j) == '\\' || string.charAt(j) == '^' || string.charAt(j) == '$' || string.charAt(j) == '.' ||
						string.charAt(j) == '[' || string.charAt(j) == '|' || string.charAt(j) == '(' || string.charAt(j) == ')' ||
						string.charAt(j) == '?' || string.charAt(j) == '*' || string.charAt(j) == '+' || string.charAt(j) == '{')
					{
						resultString = resultString.concat('\\');
						resultString = resultString.concat(string.charAt(j));
					}
					else
						resultString = resultString.concat(string.charAt(j));
				}
			}
			return resultString;
		}
	}
}