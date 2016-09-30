package base.activity;

import com.leo.test1.R;

import android.app.ActionBar;
import android.text.TextPaint;
import android.view.Gravity;
import android.widget.AbsListView.LayoutParams;
import android.widget.TextView;
import util.data.ViewConfigUtil.ActionBarTitleCenterConfig;

public class BaseActionBarTitleCenterActivity extends BaseImmersionActivity {

	@Override
	public void initView() {
		super.initView();
		initActionBar();
	}

	public boolean initActionBar(){
		ActionBar actionBar = getActionBar();
		if (actionBar == null) {
			return false;
		}
		actionBarCenter(actionBar);
		return true;
	}

	public void actionBarCenter(ActionBar actionBar){
		actionBar.setDisplayOptions(
				ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_TITLE,
				ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_TITLE);  

		TextView tv = new TextView(this);
		tv.setTextColor(getResources().getColor(R.color.white));
		tv.setTextSize(20);
		tv.setText(getTitle().toString());
		TextPaint paint = tv.getPaint(); //设置加粗
		paint.setFakeBoldText(true);
		tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		tv.setGravity(Gravity.CENTER);

		actionBar.setCustomView(tv, new ActionBar.LayoutParams(
				ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER));

		actionBar.setDisplayShowTitleEnabled(false);
	}
	
	public void actionBarViewCenter(ActionBar actionBar){
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setCustomView(ActionBarTitleCenterConfig.LAYOUT_ID);
		TextView title = (TextView) actionBar.getCustomView().findViewById(ActionBarTitleCenterConfig.TITLE_ID);
		title.setText(getTitle().toString());
		TextPaint paint = title.getPaint(); //设置加粗
		paint.setFakeBoldText(true);
	}
}
