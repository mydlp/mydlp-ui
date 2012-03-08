package com.mydlp.ui.util
{
	// PagedList from Tour De Flex tutorial by James Ward
	// http://www.jamesward.com/2010/10/11/data-paging-in-flex-4/
	
	import flash.events.EventDispatcher;
	import flash.utils.Dictionary;
	
	import mx.collections.ArrayList;
	import mx.collections.IList;
	import mx.collections.ListCollectionView;
	import mx.collections.errors.ItemPendingError;
	import mx.events.CollectionEvent;
	
	[Event("collectionChange", type="mx.events.CollectionEvent")]
	public class PagedList extends EventDispatcher implements IList
	{
		private var _length:int;
		private var _list:ArrayList;
		private var _fetchedItems:Dictionary;
		
		public function PagedList(__length:int = 0)
		{
			_fetchedItems = new Dictionary();
			if (__length > 0) this.length = __length;
		}
		
		private function handleCollectionChangeEvent(event:CollectionEvent):void 
		{
			dispatchEvent(event);
		}
		
		public function get length():int 
		{
			return _length;
		}
		
		public function set length(_length:int):void 
		{
			this._length = _length;
			initList();
		}
		
		public function initList():void 
		{
			_list = new ArrayList(new Array(_length));
			_list.addEventListener(CollectionEvent.COLLECTION_CHANGE, handleCollectionChangeEvent, false, 0, true);
		}
		
		
		public function addItem(item:Object):void 
		{
			_list.addItem(item);
		}
		
		public function addItemAt(item:Object, index:int):void 
		{
			_list.addItemAt(item, index);
		}
		
		/**
		 * When pagedList throws ItemPendingError for list items that have 
		 * not yet been loaded, they get handled by the PagedList wrapper class, 
		 * AsyncListView. The wrapper class has a createPendingItemFunction handler
		 * that then attempts to load the required items. 
		 *   
		 * @param index int
		 * @param prefetch int
		 * @return Object
		 * 
		 * @throws mx.collections.errors.ItemPendingError if the data for that index needs to be 
		 *  loaded from a remote location.
		 * 
		 */        
		public function getItemAt(index:int, prefetch:int = 0):Object 
		{
			
			if (_fetchedItems[index] == undefined)
			{
				throw new ItemPendingError("itemPending");
			}
			
			return _list.getItemAt(index, prefetch);
		}
		
		public function getItemIndex(item:Object):int 
		{
			if (_list == null) return -1;
			return _list.getItemIndex(item);
		}
		
		public function itemUpdated(item:Object, property:Object = null, oldValue:Object = null, newValue:Object = null):void 
		{
			_list.itemUpdated(item, property, oldValue, newValue);
		}
		
		public function removeAll():void 
		{
			_list.removeAll();
		}
		
		public function reset():void 
		{
			initList();
			_fetchedItems = new Dictionary();
		}
		
		public function removeItemAt(index:int):Object 
		{
			return _list.removeItemAt(index);
		}
		
		public function setItemAt(item:Object, index:int):Object 
		{
			_fetchedItems[index] = true;
			return _list.setItemAt(item, index);
		}
		
		public function toArray():Array 
		{
			return _list.toArray();
		}
	}
}