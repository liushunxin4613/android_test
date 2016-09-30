package base.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import base.webview.BaseWebView;

/**
 * webview 父类
 */
public abstract class BaseWebViewActivity extends BaseActionBarCenterActivity {

	protected BaseWebView webView;
	private String uriStr;


	public abstract String getUriStr();
	
	@Override
	public void initRootView() {
		webView = new BaseWebView(this);
		setContentView(webView);
	}
	

	@Override
	public void initView() {
		super.initView();
		uriStr = getUriStr();
		webView.initWebView();
		webView.loadUrl(uriStr);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (webView != null) {
			webView.saveState(outState);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (webView != null) {
			webView.onResume();
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (webView != null) {
			webView.stopLoading();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (webView != null) {
			webView.onPause();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (webView != null) {
			webView = null;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {//设置返回键是webview返回
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (webView != null) {
				if (webView.canGoBack()) {
					webView.goBack();
					return true;
				} 
			}
		}
		if (!goBackMain()) {
			finish();
			return super.onKeyDown(keyCode, event);
		}
		return false;
	}

	@Override  
	public void onBackPressed() {
		if(webView != null) {  
			if(webView.canGoBack()){  
				webView.goBack();
				return;
			}
		}
		if (!goBackMain()) {
			super.onBackPressed();
			finish();
		}
	} 

	public boolean goBackMain(){
		return false;
	}
}
