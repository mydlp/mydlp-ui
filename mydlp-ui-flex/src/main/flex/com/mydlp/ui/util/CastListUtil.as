package com.mydlp.ui.util
{
	import com.mydlp.ui.domain.BundledKeywordGroup;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ListCollectionView;
	import mx.controls.List;
	
	public class CastListUtil
	{
		public static function castMembersOfList(members:ListCollectionView, clazz:Class):ListCollectionView
		{
			var castedList:ListCollectionView = new ArrayCollection;
			for each(var i:* in members)
				castedList.addItem(i as clazz);
			return castedList;
		}
	}
}