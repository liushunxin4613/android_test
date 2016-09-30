package base.activity;

import com.leo.test1.R;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import util.data.ViewConfigUtil.ActionBarThreeConfig;

@SuppressLint({ "ResourceAsColor", "ClickableViewAccessibility" })
public class BaseActionBarThreeActivity extends BaseImmersionActivity implements OnClickListener{
	
	protected LinearLayout lyList,lyFind;
	protected final int LYLIST_ID = 0;
	protected final int LYFIND_ID = 1;
	
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
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setCustomView(ActionBarThreeConfig.LAYOUT_ID);
		TextView title = (TextView) actionBar.getCustomView().findViewById(ActionBarThreeConfig.TITLE_ID);
		title.setText(getTitle().toString());
		TextPaint paint = title.getPaint(); //设置加粗
		paint.setFakeBoldText(true);
		
		lyList = (LinearLayout) actionBar.getCustomView().findViewById(ActionBarThreeConfig.IMGBT_LIST_ID);
		lyList.setId(LYLIST_ID);
		lyList.setOnClickListener(this);
		lyList.setOnTouchListener(new OnTouchListener(){    
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
		lyFind = (LinearLayout) actionBar.getCustomView().findViewById(ActionBarThreeConfig.IMGBT_FIND_ID);
		lyList.setId(LYFIND_ID);
		lyFind.setOnClickListener(this);
		lyFind.setOnTouchListener(new OnTouchListener(){    
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
		return true;
	}

	@Override
	public void onClick(View v) {
	}
}
