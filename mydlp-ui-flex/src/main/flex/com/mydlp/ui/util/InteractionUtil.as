// ActionScript file
package com.mydlp.ui.util
{
	import com.mydlp.ui.widget.general.GenericDeleteDialog;
	import com.mydlp.ui.widget.general.GenericEditDialog;
	
	import flash.display.DisplayObject;
	
	import mx.core.Application;
	import mx.core.FlexGlobals;
	import mx.core.IFlexDisplayObject;
	import mx.managers.PopUpManager;
	
	public class InteractionUtil
	{
		
		public static function newPopup(className:Class): void
		{
			var popup:IFlexDisplayObject = PopUpManager.createPopUp(FlexGlobals.topLevelApplication as DisplayObject , className, true);
			PopUpManager.centerPopUp(popup);
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
