package com.mydlp.ui.util
{
	import mx.containers.FormItem;
	import mx.core.IVisualElement;
	
	import spark.components.FormItem;

	public class EditDialagPopUpElements
	{
		public var order:Number;
		public var vs:spark.components.FormItem;
		
		public function EditDialagPopUpElements(order:Number, v:spark.components.FormItem):void
		{
			this.order = order;
			this.vs = v;
		}
	}
}