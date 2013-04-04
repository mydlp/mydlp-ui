package com.mydlp.ui.util
{
	import com.mydlp.ui.domain.InventoryCategory;
	import com.mydlp.ui.domain.InventoryItem;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ICollectionView;
	import mx.collections.IViewCursor;
	import mx.controls.Alert;
	import mx.controls.treeClasses.DefaultDataDescriptor;
	import mx.controls.treeClasses.ITreeDataDescriptor;

	public class FilterDataDescriptor extends DefaultDataDescriptor
	{
		
		protected var _filterFunction:Function = null;
		
		public function FilterDataDescriptor(filterFunction:Function)
		{
			this._filterFunction = filterFunction;
			super();
		}
		
		override public function getChildren(node:Object, model:Object=null):ICollectionView
		{
			var children:ArrayCollection = new ArrayCollection([]);
			if(_filterFunction == null)
			{
				children = node.children;
			}
			else if(node is InventoryCategory)
			{
				for(var i:int = 0; i < node.children.length; i++)
				{
					if(_filterFunction(node.children[i]))
						children.addItem(node.children[i]);
				}
			}
			return children;
		}
		
		
	/*	public function hasChildren(node:Object, model:Object=null):Boolean
		{
			if(node is InventoryItem)
				return false;
			else if(node is InventoryCategory)
				return node.children.length > 0;
			return false;
		}
		
		public function isBranch(node:Object, model:Object=null):Boolean
		{
			return node is InventoryCategory;
		}*/

		public function get filterFunction():Function
		{
			return _filterFunction;
		}

		public function set filterFunction(value:Function):void
		{
			_filterFunction = value;
		}
		
		
		
	}
}