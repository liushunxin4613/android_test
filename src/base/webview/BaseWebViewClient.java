package base.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import dialog.DialogFactory;
import dialog.LoadingDialog;
import dialog.LoadingDialog.OnBackPressedLisener;

public class BaseWebViewClient extends WebViewClient {

	private DialogFactory factory;
	
	public BaseWebViewClient(Context context) {
		factory = new DialogFactory();
		LoadingDialog dialog = (LoadingDialog) factory.newLoadingDialog(context, null);
		dialog.setOnBackPressedListener(new OnBackPressedLisener() {
			@Override
			public void onBackPressed() {
				if(factory != null)
					factory.dismissDialogDefault();
			}
		});
	}

	@Override// 是否采用第三方浏览器
	public boolean shouldOverrideUrlLoading(WebView view, String url) {// true为使用webview,false表示第三方
		if (url.startsWith("http:") || url.startsWith("https:")) {
			view.loadUrl(url);
		}
		return true;
	}

	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {//加载前调用
		factory.showDialog();
		view.setAlpha(0);
		view.getSettings().setBlockNetworkImage(true); //图片下载阻塞
		super.onPageStarted(view, url, favicon);
	}

	@Override
	public void onPageFinished(WebView view, String url) {//加载后调用
		factory.dismissDialog();
		view.setAlpha(1);
		view.getSettings().setBlockNetworkImage(false); 
		super.onPageFinished(view, url);
	}

}
