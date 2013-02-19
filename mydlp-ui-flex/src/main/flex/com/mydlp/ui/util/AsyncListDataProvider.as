package com.mydlp.ui.util
{
	import flash.external.ExternalInterface;
	import flash.utils.Dictionary;
	
	import mx.collections.AsyncListView;
	import mx.collections.errors.ItemPendingError;
	import mx.rpc.AsyncResponder;
	import mx.rpc.AsyncToken;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;

	[Bindable]
	public class AsyncListDataProvider
	{
		protected var _asyncListView:AsyncListView = null;
		
		protected var _countFunction:Function = null;
		protected var _fetchFunction:Function = null;
		protected var _pendingObject:Object = null;
		
		protected const PAGE_SIZE:int = 25;
		protected const MAX_FETCHED_PAGE:int = 20;
		
		protected var _pagedList:PagedList = null;
		
		protected var _numDBItems:Number = Number.NaN;
		
		protected var _fetchedPages:Dictionary = new Dictionary();
		protected var _numFetchedPages:int = 0;
		
		
		public function AsyncListDataProvider()
		{
			_asyncListView = new AsyncListView();
			_asyncListView.list = _pagedList;
			_asyncListView.createPendingItemFunction = handleCreatePendingItemFunction;
		}
		
		protected function handleCreatePendingItemFunction(index:int, ipe:ItemPendingError):Object 
		{
			if (isNaN(_numDBItems)) return pendingObject;
			if (_fetchedPages == null) return pendingObject;
			
			if (_numFetchedPages > MAX_FETCHED_PAGE) reset();
			
			var page:uint = Math.floor(index / PAGE_SIZE);
			
			if (_fetchedPages[page] == undefined)
			{
				var numItemsToFetch:uint = PAGE_SIZE;
				var startIndex:uint = PAGE_SIZE * page;
				var endIndex:uint = startIndex + PAGE_SIZE - 1;
				
				if (endIndex > _pagedList.length)
				{
					numItemsToFetch = _pagedList.length - startIndex;
				}
				
				var asyncToken:AsyncToken = _fetchFunction(startIndex, numItemsToFetch);
				
				asyncToken.addResponder(new AsyncResponder(
					function result(event:ResultEvent, token:Object = null):void 
					{
						for (var i:uint = 0; i < event.result.length; i++)
							_pagedList.setItemAt(event.result[i], token + i);
					}, 
					function fault(event:FaultEvent, token:Object = null):void 
					{
						trace(event.fault.faultString);
						_fetchedPages[page] = undefined;
						_numFetchedPages--;
					}, 
					startIndex));
				_fetchedPages[page] = true;
				_numFetchedPages++;
			}
			return pendingObject;
		}            
		
		public function reset():void{
			_fetchedPages = new Dictionary();
			_numFetchedPages = 0;
			_pagedList = new PagedList(_numDBItems);
			_asyncListView.list = _pagedList;
		}
		
		public function init():void
		{
			var asyncToken:AsyncToken = _countFunction();
			asyncToken.addResponder(new AsyncResponder(
				function result(event:ResultEvent, token:Object = null):void 
				{
					_numDBItems = event.result as Number;
					reset();
				}, 
				function fault(event:FaultEvent, token:Object = null):void 
				{
					trace(event.fault.faultString);
				}, null
			));
		}
		
		public function get countFunction():Function
		{
			return _countFunction;
		}
		
		public function set countFunction(value:Function): void
		{
			_countFunction = value;
		}
		
		public function get fetchFunction():Function
		{
			return _fetchFunction;
		}
		
		public function set fetchFunction(value:Function): void
		{
			_fetchFunction = value;
		}
		
		public function get asyncListView(): AsyncListView
		{
			return _asyncListView;
		}
		
		public function set pendingObject(value:Object): void
		{
			_pendingObject = value;
		}
		
		public function get pendingObject(): Object
		{
			return _pendingObject;
		}
	}
}