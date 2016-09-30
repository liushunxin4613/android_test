package base.activity;

import com.leo.test1.R;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import util.data.ViewConfigUtil.ActionBarCenterConfig;
import android.widget.AbsListView.LayoutParams;

public class BaseActionBarCenterActivity extends BaseImmersionActivity {

	@Override
	public void initView() {
		super.initView();
		initActionBar();
	}

	private TextView title;

	public boolean initActionBar(){
		ActionBar actionBar = getActionBar();
		if (actionBar == null) {
			return false;
		}
		actionBarCenter(actionBar);
		getActionBar().setDisplayShowHomeEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case android.R.id.home:
			onBackPressed();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@SuppressLint({ "ResourceAsColor", "ClickableViewAccessibility" })
	public void actionBarViewCenter(ActionBar actionBar){
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setCustomView(ActionBarCenterConfig.LAYOUT_ID);
		title = (TextView) actionBar.getCustomView().findViewById(ActionBarCenterConfig.TITLE_ID);
		title.setText(getTitle().toString());
		TextPaint paint = title.getPaint(); //设置加粗
		paint.setFakeBoldText(true);
		LinearLayout ly = (LinearLayout) actionBar.getCustomView().findViewById(ActionBarCenterConfig.IMGBT_ID);
		ly.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		ly.setOnTouchListener(new OnTouchListener(){    
			@Override   
			public boolean onTouch(View v, MotionEvent event) {    
				if(event.getAction() == MotionEvent.ACTION_DOWN){    
					//更改为按下时的背景图片
					v.setBackgroundColor(R.color.immersionColor);
				}else if(event.getAction() == MotionEvent.ACTION_UP){    
					//改为抬起时的图片
					v.getBackground().setAlpha(0);
				}    
				return false;    
			}
		});
	}

	protected void setTitle(String title){
		this.title.setText(title);
	}

	public void actionBarCenter(ActionBar actionBar){
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayOptions(
				ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_TITLE,
				ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_TITLE);  

		title = new TextView(this);
		title.setTextColor(getResources().getColor(R.color.white));
		title.setTextSize(20);
		title.setText(getTitle().toString());
		TextPaint paint = title.getPaint(); //设置加粗
		paint.setFakeBoldText(true);
		title.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		title.setGravity(Gravity.CENTER);

		actionBar.setCustomView(title, new ActionBar.LayoutParams(
				ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER));

		actionBar.setDisplayShowTitleEnabled(false);
	}

}
