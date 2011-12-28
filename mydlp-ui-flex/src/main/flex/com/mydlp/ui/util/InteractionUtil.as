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
	import mx.managers.PopUpManager;
	
	public class InteractionUtil
	{
		
		public static function newPopup(className:Class): void
		{
			var popup:IFlexDisplayObject = PopUpManager.createPopUp(FlexGlobals.topLevelApplication as DisplayObject , className, true);
			popup.move(FlexGlobals.topLevelApplication.mouseX + 10, FlexGlobals.topLevelApplication.mouseY + 10);
		}
		
		public static function closePopup(popup:Object): void
		{
			PopUpManager.removePopUp(popup as IFlexDisplayObject);
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
