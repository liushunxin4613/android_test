package util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

public class AppUtil {

	public static final String TAG = "AppUtil";
	
	/**
	 * ��ȡ��ǰAPP�汾��
	 */
	public static String getVersionName(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			return info.versionName;
		} catch (Exception e) {
			Log.i(TAG, e.getMessage());
		}
		return null;
	}
	/**
	 * ��ȡ��ǰAPP�汾��
	 */
	public static int getVersionCode(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			return info.versionCode;
		} catch (Exception e) {
			Log.i(TAG, e.getMessage());
		}
		return 0;
	}

}
