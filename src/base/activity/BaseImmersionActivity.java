package base.activity;

import util.ui.KitKatUtils;
import util.ui.SystemBarTintManager;

import com.leo.test1.R;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

/**
 * ����ʽ֪ͨ��Ч����Activity
 * 
 */
public class BaseImmersionActivity extends BaseActivity {

	/**
	 * ��������������ɫ���Ƿ�Ϊ��ɫ��Ĭ��Ϊ��ɫ
	 */
	private boolean isLight = true;
	/**
	 * ״̬������ɫ
	 */
	private int statusColor;
	
	private SystemBarTintManager systemBarManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		KitKatUtils.setStatusBar(this, isLight);
	}

	/**
	 * @return ����֪ͨ������ɫ���Ƿ�Ϊ��ɫ
	 */
	public boolean isLight() {
		return isLight;
	}

	/**
	 * @param isLight
	 *            ����֪ͨ����������ɫ�Ƿ�Ϊ��ɫ������miui��Ч��,true ��ɫ; false ��ɫ
	 */
	public void setLight(boolean isLight) {
		this.isLight = isLight;
		KitKatUtils.setStatusBar(this, isLight);
	}

	/**
	 * ����֪ͨ������ɫ
	 * 
	 * @param resid
	 */
	public void setStatusColor(int color) {
		this.statusColor = color;
		systemBarManager = new SystemBarTintManager(this);
		systemBarManager.setStatusBarTintEnabled(true);
		systemBarManager.setStatusBarTintColor(statusColor);
	}

	/**
	 * ����֪ͨ������ɫ
	 * 
	 * @param drawable
	 */
	public void setBackgroundDrawable(Drawable drawable) {
		this.getWindow().setBackgroundDrawable(drawable);
	}
	
	@Override
	public void initView() {
		KitKatUtils.setStatusBar(this, isLight);
		setStatusColor(getResources().getColor(R.color.immersionColor));
	}
}