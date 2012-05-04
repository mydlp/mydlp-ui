// ActionScript file
package com.mydlp.ui.util
{
	import com.mydlp.ui.widget.general.popup.GenericDeleteDialog;
	import com.mydlp.ui.widget.general.popup.GenericEditDialog;
	
	import flash.display.DisplayObject;
	import flash.ui.Mouse;
	import flash.ui.MouseCursor;
	import flash.ui.MouseCursorData;
	
	import mx.core.Application;
	import mx.core.FlexGlobals;
	import mx.core.IFlexDisplayObject;
	import mx.events.CloseEvent;
	import mx.managers.PopUpManager;
	
	public class InteractionUtil
	{
		
		public static function newPopup(className:Class): IFlexDisplayObject
		{
			var popup:IFlexDisplayObject = PopUpManager.createPopUp(FlexGlobals.topLevelApplication as DisplayObject , className, true);
			PopUpManager.centerPopUp(popup);
			popup.addEventListener(CloseEvent.CLOSE, closeHandlerFunction);
			return popup;
		}
		
		public static function closeHandlerFunction(event:CloseEvent):void
		{
			ErrorTipManager.hideAllErrorTips();
		}
		
		public static function closePopup(popup:Object): void
		{
			PopUpManager.removePopUp(popup as IFlexDisplayObject);
		}
		
		public static function closeCurrentPopup(): void
		{
			if (FlexGlobals.topLevelApplication.currentPopup != null)
			{
				closePopup(FlexGlobals.topLevelApplication.currentPopup);
				FlexGlobals.topLevelApplication.currentPopup = null;
			}
		}
		
		public static function newCRUDDialog(object:Object, dialog:Class): void
		{
			newPopup(dialog);
			FlexGlobals.topLevelApplication.currentPopup.formObject = object;
			FlexGlobals.topLevelApplication.currentPopup.populate();
		}
		
		public static function newEditDialog(object:Object): void
		{
			newCRUDDialog(object, GenericEditDialog);
		}
		
		public static function newDeleteDialog(object:Object): void
		{
			newCRUDDialog(object, GenericDeleteDialog);
		}
		
	}
}
