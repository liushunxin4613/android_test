package base.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import util.AppUtil;

@SuppressLint("SetJavaScriptEnabled")
public class BaseWebView extends WebView {

	protected Context context;
	
	public BaseWebView(Context context) {
		super(context);
		this.context = context;
	}

	public void initWebView(){
		setWebViewSetting();
		setWebViewClient(new BaseWebViewClient(context));
	}
	
	public void setWebViewSetting(){
		WebSettings settings = getSettings();

		settings.setJavaScriptEnabled(true);// 支持JavaScript

		settings.setUseWideViewPort(true);//将图片调整到适合webview的大小

		settings.setLoadsImagesAutomatically(true);//支持自动加载图片

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {//解决硬件加速问题
			setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		}

		settings.setCacheMode(WebSettings.LOAD_DEFAULT);

		settings.setDomStorageEnabled(true);
		
		String ua = getSettings().getUserAgentString();
		getSettings().setUserAgentString(ua+"; FishOS /"+ AppUtil.getVersionName(context));
	}
	
	
}
