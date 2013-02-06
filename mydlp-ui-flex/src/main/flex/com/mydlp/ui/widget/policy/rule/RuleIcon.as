package com.mydlp.ui.widget.policy.rule
{
	import com.mydlp.ui.domain.ApiRule;
	import com.mydlp.ui.domain.DiscoveryRule;
	import com.mydlp.ui.domain.MailRule;
	import com.mydlp.ui.domain.PrinterRule;
	import com.mydlp.ui.domain.RemoteStorageRule;
	import com.mydlp.ui.domain.RemovableStorageInboundRule;
	import com.mydlp.ui.domain.RemovableStorageRule;
	import com.mydlp.ui.domain.Rule;
	import com.mydlp.ui.domain.ScreenshotRule;
	import com.mydlp.ui.domain.WebRule;

	public class RuleIcon
	{
		public function RuleIcon()
		{
		}
		
		[Embed('/assets/icons/32x32/www.png')]
		public static const webRule32Icon:Class;
		
		[Embed('/assets/icons/32x32/mail.png')]
		public static const mailRule32Icon:Class;
		
		[Embed('/assets/icons/32x32/usbicon.png')]
		public static const removableStorageRule32Icon:Class;
		
		[Embed('/assets/icons/32x32/print.png')]
		public static const printerRule32Icon:Class;
		
		[Embed('/assets/icons/32x32/search.png')]
		public static const discoveryRule32Icon:Class;
		
		[Embed('/assets/icons/32x32/remote_storage.png')]
		public static const remoteStorageRule32Icon:Class;
		
		[Embed('/assets/icons/32x32/puzzle.png')]
		public static const apiRule32Icon:Class;
		
		[Embed('/assets/icons/32x32/usbiconinbound.png')]
		public static const removableStorageInboundRule32Icon:Class;
		
		[Embed('/assets/icons/32x32/photo_camera.png')]
		public static const screenshotRule32Icon:Class;
		
		[Embed('/assets/icons/24x24/www.png')]
		public static const webRule24Icon:Class;
		
		[Embed('/assets/icons/24x24/mail.png')]
		public static const mailRule24Icon:Class;
		
		[Embed('/assets/icons/24x24/usbicon.png')]
		public static const removableStorageRule24Icon:Class;
		
		[Embed('/assets/icons/24x24/print.png')]
		public static const printerRule24Icon:Class;
		
		[Embed('/assets/icons/24x24/search.png')]
		public static const discoveryRule24Icon:Class;
		
		[Embed('/assets/icons/24x24/remote_storage.png')]
		public static const remoteStorageRule24Icon:Class;
		
		[Embed('/assets/icons/24x24/puzzle.png')]
		public static const apiRule24Icon:Class;
		
		[Embed('/assets/icons/24x24/usbiconinbound.png')]
		public static const removableStorageInboundRule24Icon:Class;
		
		[Embed('/assets/icons/24x24/photo_camera.png')]
		public static const screenshotRule24Icon:Class;
		
		[Embed('/assets/icons/16x16/www.png')]
		public static const webRule16Icon:Class;
		
		[Embed('/assets/icons/16x16/mail.png')]
		public static const mailRule16Icon:Class;
		
		[Embed('/assets/icons/16x16/usbicon.png')]
		public static const removableStorageRule16Icon:Class;
		
		[Embed('/assets/icons/16x16/print.png')]
		public static const printerRule16Icon:Class;
		
		[Embed('/assets/icons/16x16/search.png')]
		public static const discoveryRule16Icon:Class;
		
		[Embed('/assets/icons/16x16/remote_storage.png')]
		public static const remoteStorageRule16Icon:Class;
		
		[Embed('/assets/icons/16x16/puzzle.png')]
		public static const apiRule16Icon:Class;
		
		[Embed('/assets/icons/16x16/usbiconinbound.png')]
		public static const removableStorageInboundRule16Icon:Class;
		
		[Embed('/assets/icons/16x16/photo_camera.png')]
		public static const screenshotRule16Icon:Class;

		public static function getIcon32(rule:Rule): Class
		{
			if (rule is WebRule)
			{
				return webRule32Icon;
			}
			else if (rule is MailRule)
			{
				return mailRule32Icon;
			}
			else if (rule is RemovableStorageRule)
			{
				return removableStorageRule32Icon;
			}
			else if (rule is PrinterRule)
			{
				return printerRule32Icon;
			}
			else if (rule is DiscoveryRule)
			{
				return discoveryRule32Icon;
			}
			else if (rule is RemoteStorageRule)
			{
				return remoteStorageRule32Icon;
			}
			else if (rule is ApiRule)
			{
				return apiRule32Icon;
			}
			else if (rule is RemovableStorageInboundRule)
			{
				return removableStorageInboundRule32Icon;
			}
			else if (rule is ScreenshotRule)
			{
				return screenshotRule32Icon;
			}
			else
			{
				throw new Error("Unknown rule type;");
			}
			return null;
		}
		
		public static function getIcon16(rule:Rule): Class
		{
			if (rule is WebRule)
			{
				return webRule16Icon;
			}
			else if (rule is MailRule)
			{
				return mailRule16Icon;
			}
			else if (rule is RemovableStorageRule)
			{
				return removableStorageRule16Icon;
			}
			else if (rule is PrinterRule)
			{
				return printerRule16Icon;
			}
			else if (rule is DiscoveryRule)
			{
				return discoveryRule16Icon;
			}
			else if (rule is RemoteStorageRule)
			{
				return remoteStorageRule16Icon;
			}
			else if (rule is ApiRule)
			{
				return apiRule16Icon;
			}
			else if (rule is RemovableStorageInboundRule)
			{
				return removableStorageInboundRule16Icon;
			}
			else if (rule is ScreenshotRule)
			{
				return screenshotRule16Icon;
			}
			else
			{
				throw new Error("Unknown rule type;");
			}
			return null;
		}
		
		public static function getIcon24(rule:Rule): Class
		{
			if (rule is WebRule)
			{
				return webRule24Icon;
			}
			else if (rule is MailRule)
			{
				return mailRule24Icon;
			}
			else if (rule is RemovableStorageRule)
			{
				return removableStorageRule24Icon;
			}
			else if (rule is PrinterRule)
			{
				return printerRule24Icon;
			}
			else if (rule is DiscoveryRule)
			{
				return discoveryRule24Icon;
			}
			else if (rule is RemoteStorageRule)
			{
				return remoteStorageRule24Icon
			}
			else if (rule is ApiRule)
			{
				return apiRule24Icon;
			}
			else if (rule is RemovableStorageInboundRule)
			{
				return removableStorageInboundRule24Icon;
			}
			else if (rule is ScreenshotRule)
			{
				return screenshotRule24Icon;
			}
			else
			{
				throw new Error("Unknown rule type;");
			}
			return null;
		}
		
	}
}