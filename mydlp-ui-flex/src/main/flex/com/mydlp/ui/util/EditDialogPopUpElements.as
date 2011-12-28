package com.mydlp.ui.util
{
	import mx.containers.FormItem;
	import mx.core.IVisualElement;
	
	import spark.components.FormItem;

	public class EditDialogPopUpElements
	{
		public var order:Number;
		public var vs:spark.components.FormItem;
		
		public function EditDialogPopUpElements(order:Number, v:spark.components.FormItem):void
		{
			this.order = order;
			this.vs = v;
		}
	}
}