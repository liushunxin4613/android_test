package base.fragment;

import inter.AcFmInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseFragment extends Fragment implements AcFmInterface{

	protected View rootView;
	
	public static String TAG;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		TAG = getClass().getName();
		initRootView();
		rootView = inflater.inflate(getRootViewId(), null);
		initView();
		initData();
		return rootView;
	}

	@Override
	public void initRootView() {
	}

	@Override
	public int getRootViewId(){
		return 0;
	};

	@Override
	public void initView() {
	}

	@Override
	public void initData() {
	}
	
	public void sayTag(int key,Object obj){
		String str = obj + "";
		switch (key) {
		case 0:
			Log.v(TAG, str);
			break;
		case 1:
			Log.d(TAG, str);
			break;
		case 2:
			Log.i(TAG, str);
			break;
		case 3:
			Log.w(TAG, str);
			break;
		case 4:
			Log.e(TAG, str);
			break;
		case 5:
			Log.wtf(TAG, str);
			break;
		}
	}

}
