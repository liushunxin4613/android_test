package util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class ReceiveUtil {
	
	private final BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			toReceive.toReceive(intent.getAction());
		}
	};;
	
	private ToReceive toReceive;
	private Context context;
	
	public ReceiveUtil(Context context,ToReceive toReceive) {
		this.context = context;
		this.toReceive = toReceive;
		context.registerReceiver(receiver, toReceive.getIntentFilter());
	}
	
	public void unregisterReceiver(){
		context.unregisterReceiver(receiver);
	}
	
	public interface ToReceive{
		IntentFilter getIntentFilter();
		void toReceive(String action);
	}
	
}
