package base.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BaseService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	/**
	 * 启动Service的功能请求类型的Key
	 */
	public static final String KEY_START_SERVICE_FOR = "StartServiceFor";
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (intent == null) {
			stopSelf();
			return super.onStartCommand(intent, flags, startId);
		}
		int requestCode = intent.getIntExtra(KEY_START_SERVICE_FOR, 0);
		if (requestCode == 0) {
			stopSelf();
			return super.onStartCommand(intent, flags, startId);
		}
		toStartCommand(requestCode);
		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * 对service做出响应
	 * @param requestCode
	 */
	protected void toStartCommand(int requestCode) {
		
	}
	
}
