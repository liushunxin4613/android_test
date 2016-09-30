package base.fragment;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

public class BaseMainFragment extends BaseFragment {

	@Override
	public void setMenuVisibility(boolean menuVisible) {
		super.setMenuVisibility(menuVisible);
		
		if (this.getView() != null) {
			this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
		}
		
	}

	public int getWindowsWidth(){
		WindowManager wm = getActivity().getWindowManager();
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);
		int width = metrics.widthPixels;
		return width;
	}
	
}
