package 
{

	import flash.display.Bitmap;
	import flash.display.BitmapData;
	import flash.display.MovieClip;
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.events.ProgressEvent;
	import flash.text.TextField;
	import flash.text.TextFormat;
	
	import mx.core.mx_internal;
	import mx.preloaders.SparkDownloadProgressBar;
	
	use namespace mx_internal;
	
	public class MainPreloader extends SparkDownloadProgressBar {
		
		[Embed(source="/assets/images/logo_icon.png")]
		public static const LOGO:Class;
		
		private var preloaderLogo : Bitmap;
		private var loadingText : TextField;
		private var loadingProgress : TextField;
		
		private var _initProgressCount : uint = 0;
		
		private var textFormat : TextFormat = new TextFormat("Verdana", 16, 0x666666, true);
		
		public function MainPreloader() {
			super();
			textFormat.align = "center";
		}
		
		
		override public function set preloader(value : Sprite) : void {
			super.preloader = value;
			
			if (!preloaderLogo) {
				preloaderLogo = new LOGO();
				
				var startX : Number = Math.round((stageWidth - preloaderLogo.width) / 2);
				var startY : Number = Math.round((stageHeight - preloaderLogo.height)/2) - 50;
				if (startY < 10) startX = 10;
				if (startY < 20) startY = 20;
				
				preloaderLogo.x = startX;
				preloaderLogo.y = startY;
				
				loadingText = new TextField();
				loadingProgress = new TextField();
				
				loadingText.width = stageWidth;//to allow center align
				loadingProgress.width = stageWidth;                
				
				
				loadingText.text = "Loading...";
				loadingText.y = preloaderLogo.y + preloaderLogo.height + 20;
				
				
				loadingProgress.text = "0%";
				loadingProgress.y = loadingText.y + loadingText.textHeight + 10;
				
				addChild(preloaderLogo);
				addChild(loadingText);
				addChild(loadingProgress);
				
				loadingText.setTextFormat(textFormat);
				loadingProgress.setTextFormat(textFormat);
			}
		}
		
		
		override protected function progressHandler(event : ProgressEvent) : void {
			super.progressHandler(event);
			if (loadingProgress) {
				loadingProgress.text = Math.floor(event.bytesLoaded / event.bytesTotal * 100) + "%";
				loadingProgress.setTextFormat(textFormat);
			}
			
		}
		
		override protected function completeHandler(event : Event) : void {
			loadingText.text = "Ready!";
			loadingText.setTextFormat(textFormat);
		}        
		
		
		override protected function initProgressHandler(event : Event) : void {
			super.initProgressHandler(event);
			//similar to super
			_initProgressCount++;
			if (loadingProgress) {
				loadingProgress.text = "100% / " + Math.floor(_initProgressCount / initProgressTotal * 100) + "%";
				loadingProgress.setTextFormat(textFormat);
			}
		}
	}
}