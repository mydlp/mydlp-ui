// ActionScript file
package com.mydlp.ui.util
{
	import flash.display.DisplayObject;
	
	import mx.core.IFlexDisplayObject;
	import mx.managers.PopUpManager;
	
	public class InteractionUtil
	{
		
		public static function newPopup(parent:Object, className:Class): void
		{
			var popup:IFlexDisplayObject = PopUpManager.createPopUp(parent as DisplayObject , className, true);
			PopUpManager.centerPopUp(popup);
		}
		
		public static function closePopup(popup:Object): void
		{
			PopUpManager.removePopUp(popup as IFlexDisplayObject);
		}
		
	}
	}
