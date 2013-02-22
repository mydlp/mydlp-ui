package com.mydlp.ui.util
{
	import com.mydlp.ui.domain.InventoryBase;
	import com.mydlp.ui.domain.InventoryCategory;
	import com.mydlp.ui.domain.InventoryItem;
	import com.mydlp.ui.skin.FolderBitmapLabelButton16Skin;
	import com.mydlp.ui.widget.policy.inventory.InventoryItemRenderer;
	
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ListCollectionView;
	import mx.controls.Alert;
	import mx.controls.Tree;
	import mx.controls.listClasses.IListItemRenderer;
	import mx.core.ClassFactory;
	import mx.core.FlexGlobals;
	import mx.events.DragEvent;
	import mx.events.ListEvent;
	import mx.managers.DragManager;
	import mx.rpc.AsyncResponder;
	import mx.rpc.AsyncToken;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	public class InventoryTree extends Tree
	{
		protected var _getInventoryFunction:Function = null;
		
		protected var _saveAllFunction:Function = null;
		
		[Bindable] 
		protected var inventory:ListCollectionView;
		
		protected var tempOpenItems:Object;
		
		protected var afterRefreshSelectedIndices:Array = [];
		
		public var isDiscoveryTab:Boolean;
		
		protected var tempVerticalScrollPosition:Number = 0;
		
		protected var previousSelectedItem:Object = null;
		
		[Embed(source="/assets/icons/16x16/folder.png")] 
		public var itemIcon:Class; 
		
		public function InventoryTree()
		{	
			this.dragEnabled = true;
			this.dropEnabled = true;
			this.variableRowHeight = true;
			this.allowMultipleSelection = true;
			this.labelField = "label";
			this.showRoot = false;
			
			this.addEventListener(ListEvent.ITEM_CLICK, itemClickHandler);
			this.addEventListener(ListEvent.CHANGE, onChangeHandler);
			this.addEventListener(DragEvent.DRAG_DROP, onDragDrop);
			this.addEventListener(DragEvent.DRAG_EXIT, onDragExit);
			super();
		}
		
		public function populate():void
		{
			getInventoryHandler(null, null);
		}
		
		protected function getInventoryHandler(event:ResultEvent, token:Object):void
		{
			
			invalidateList();
			invalidateDisplayList();
			invalidateSize();
			
		//	inventory = event.result as ArrayCollection;
		//	this.dataProvider = inventory;
			var ir:ClassFactory = new ClassFactory(InventoryItemRenderer);
			ir.properties = {isDiscoveryTab: isDiscoveryTab};
			this.itemRenderer = ir;
			
			validateNow();
			callLater(afterRefreshOps);
		}
		
		public function afterRefreshOps(): void
		{
			openItems = tempOpenItems;
			verticalScrollPosition = tempVerticalScrollPosition;
			this.selectedIndices = afterRefreshSelectedIndices;
		}
		
		protected function faultHandler(event:FaultEvent):void
		{
			trace(event.fault.faultString);
		}
		
		public function displayItem(item:InventoryItem):void
		{
			var ii:InventoryItem;
			for(var i:int = 0; i < dataProvider.length; i++)
			{
				ii = traverse(dataProvider[i], item);
				if(ii != null)
					break
			}
			expandCategories(ii);
			this.selectedItem = ii;
		}
		
		
		protected function traverse(iitem:*, searcehedItem:InventoryItem):InventoryItem
		{
			if(iitem is InventoryCategory)
			{
				for(var i:int = 0; i < iitem.children.length;i++)
				{
					var retItem:InventoryItem = traverse(iitem.children[i], searcehedItem);
					if(retItem != null)
						return retItem;
				}
				return null;
			}
			else if(iitem is InventoryItem && (iitem.uid == searcehedItem.uid))
			{
				return iitem;
			}
			else
				return null;
		}
		
		protected function expandCategories(item:*):void
		{
			if(item.category != null)
			{
				if(!isItemOpen(item.category))
					expandItem(item.category, true);
				expandCategories(item.category);
			}
		}
		
		protected function itemClickHandler(event:ListEvent):void
		{
			if (dataDescriptor.isBranch(selectedItem) && 
				(!isItemOpen(selectedItem) || selectedItem == previousSelectedItem)
			) {
				expandItem(selectedItem, !isItemOpen(selectedItem), true);
			}
			previousSelectedItem = selectedItem;
		}
		
		protected function onDragDrop(event:DragEvent):void
		{
			event.preventDefault();
			hideDropFeedback(event);
			
			// TODO: This should be reflected to feedbacks
			if (!(isSourceDragable(event) && isTargetDropable(event))) return;
			
			var dropIndex:int = calculateDropIndex();
			var renderer:IListItemRenderer = indexToItemRenderer(dropIndex);
			var parentCategory:InventoryCategory = null;
			if (renderer == null)
			{
				parentCategory = null;
			}
			else
			{
				var dropTarget:InventoryBase = renderer.data as InventoryBase;
				if (renderer.data is InventoryCategory)
				{
					parentCategory = dropTarget as InventoryCategory;
					expandItem(parentCategory, true);
				}
				else
					parentCategory = dropTarget.category;
			}
			
			var itemsToSave:ListCollectionView = new ArrayCollection();
			if (event.dragSource.hasFormat("treeItems"))
				for each (var item:Object in event.dragSource.dataForFormat('treeItems') as Array)
			{
				var dragSource:InventoryBase = item as InventoryBase;
				if (dragSource != parentCategory && 
					dragSource.category != parentCategory &&
					isValidTargetCategory(dragSource, parentCategory)
				)
				{
					dragSource.category = parentCategory;
					itemsToSave.addItem(dragSource);
				}
			}
			
			if (itemsToSave.length > 0)
			{
				var asyncToken:AsyncToken = _saveAllFunction(itemsToSave);
				asyncToken.addResponder(new AsyncResponder(saveAllHandler, faultHandler, null));
			}
		}
		
		protected function saveAllHandler(event:ResultEvent, token:Object):void
		{
			FlexGlobals.topLevelApplication.getInventoryFunction();
		}
		
		protected function onDragExit(event:DragEvent):void
		{
			DragManager.showFeedback(DragManager.NONE);	
		}
		
		protected function isSourceDragable(event:DragEvent): Boolean
		{
			if (event.dragSource.hasFormat("treeItems"))
			{
				for each (var item:Object in event.dragSource.dataForFormat('treeItems') as Array)
				{
					var dragSource:InventoryBase = item as InventoryBase;
					if (!InventoryItemRenderer.isEditEnabled(dragSource))
						return false;
				}
				return true;
			}
			return false;
		}
		
		protected function isTargetDropable(event:DragEvent): Boolean
		{
			var dropIndex:int = calculateDropIndex(event);
			var renderer:IListItemRenderer = indexToItemRenderer(dropIndex);
			var parentCategory:InventoryCategory = null;
			if (renderer == null)
			{
				return false;
			}
			else
			{
				if(renderer.data.nameKey != null)
					trace("name: " + renderer.data.nameKey);
				else
					trace("name: " + renderer.data.name);
				var dropTarget:InventoryBase = renderer.data as InventoryBase;
				if (renderer.data is InventoryCategory)
				{
					parentCategory = dropTarget as InventoryCategory;
					expandItem(parentCategory, true);
				}
				else
					parentCategory = dropTarget.category;
				
				if (InventoryItemRenderer.isAddEnabled(parentCategory))
					return true;
				else
					return false;
			}
			return false;
		}
		
		protected function isValidTargetCategory(dragSource:InventoryBase, targetCategory:InventoryCategory): Boolean
		{
			if (targetCategory == null)
				return true;
			if (dragSource == targetCategory)
				return false;
			return isValidTargetCategory(dragSource, targetCategory.category);
		}
		
		protected function onChangeHandler(event:ListEvent):void
		{
			afterRefreshSelectedIndices = this.selectedIndices;
		}
		
		
		public function refreshTree():void
		{
			tempOpenItems = openItems;
			tempVerticalScrollPosition = verticalScrollPosition;
			afterRefreshSelectedIndices = this.selectedIndices;
		}
		
		public function get getInventoryFunction():Function
		{
			return _getInventoryFunction;
		}
		
		public function set getInventoryFunction(value:Function):void
		{
			_getInventoryFunction = value;
		}
		
		public function get saveAllFunction():Function
		{
			return _saveAllFunction;
		}
		
		public function set saveAllFunction(value:Function):void
		{
			_saveAllFunction = value;
		}
		
	}
}