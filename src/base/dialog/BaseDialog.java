package base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BaseDialog extends Dialog implements OnClickListener,DialogInterface{
	
	protected Context context;
	protected TextView titleView;
	protected Button positiveBtn;
	protected Button nevigativeBtn;

	protected int theme = 0;
	protected CharSequence title;
	protected CharSequence positiveText;
	protected CharSequence nevigativeText;
	protected DialogInterface.OnClickListener onclickListener;

	protected static final String KEY_TITLE = "title";
	protected static final String KEY_POSITIVE_BUTTON = "positiveText";
	protected static final String KEY_NEVIGATIVE_BUTTON = "nevigativeText";
	
	public static String TAG;

	public BaseDialog(Context context) {
		super(context);
		this.context = context;
	}
	
	public BaseDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
		this.theme = theme;
	}
	
	protected BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		this.context = context;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TAG = getClass().getName();
		if (savedInstanceState != null) {
			title = savedInstanceState.getCharSequence(KEY_TITLE);
			positiveText = savedInstanceState.getCharSequence(KEY_POSITIVE_BUTTON);
			nevigativeText = savedInstanceState.getCharSequence(KEY_NEVIGATIVE_BUTTON);
		}
	}

	public DialogInterface.OnClickListener getOnclickListener() {
		return onclickListener;
	}

	public void setOnclickListener(
			DialogInterface.OnClickListener onclickListener) {
		this.onclickListener = onclickListener;
	}

	public CharSequence getTitle() {
		return title;
	}

	public void setTitle(CharSequence title) {
		this.title = title;
		if (titleView != null) {
			this.titleView.setVisibility(View.VISIBLE);
			this.titleView.setText(title);
		}
	}

	public void setPositiveButtonText(CharSequence text) {
		positiveText = text;
		if (positiveBtn != null) {
			positiveBtn.setText(positiveText);
		}
	}

	public void setNevigativeButtonText(CharSequence text) {
		nevigativeText = text;
		if (nevigativeBtn != null)
			nevigativeBtn.setText(nevigativeText);
	}

	@Override
	public void onClick(View v) {
		if (v.equals(positiveBtn)) {
			if (onclickListener != null)
				onclickListener.onClick(this, DialogInterface.BUTTON_POSITIVE);
			this.dismiss();
		} else if (v.equals(nevigativeBtn)) {
			if (onclickListener != null)
				onclickListener.onClick(this, DialogInterface.BUTTON_NEGATIVE);
			this.dismiss();
		}
	}
	
}
