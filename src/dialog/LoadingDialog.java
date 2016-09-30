package dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import base.dialog.BaseDialog;

public class LoadingDialog extends BaseDialog {

	public LoadingDialog(Context context, int theme) {
		super(context, theme);
	}

	public LoadingDialog(Context context, int theme, int resource, int itemId) {
		super(context, theme);
		this.resource = resource;
		this.itemId = itemId;
	}

//	private CharSequence content;

	private TextView contentTV;

	private OnBackPressedLisener onBackPressedListener;

	public interface OnBackPressedLisener {
		public void onBackPressed();
	}

	protected int resource;
	protected int itemId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (resource != 0) {
			setContentView(resource);

			contentTV = (TextView) findViewById(itemId);

			if (title != null) {
				contentTV.setText(title);
				contentTV.setVisibility(View.VISIBLE);
			}

			setOnKeyListener(new OnKeyListener() {
				@Override
				public boolean onKey(DialogInterface dialog, int keyCode,
						KeyEvent event) {
					if (KeyEvent.KEYCODE_BACK == keyCode) {
						switch (event.getAction()) {
						case KeyEvent.ACTION_DOWN:
							break;
						case KeyEvent.ACTION_UP:
							if (onBackPressedListener != null)
								onBackPressedListener.onBackPressed();
							break;
						}
					}
					return false;
				}
			});
			setCancelable(false);
		}
	}

	public OnBackPressedLisener getOnBackPressedListener() {
		return onBackPressedListener;
	}

	public void setOnBackPressedListener(
			OnBackPressedLisener onBackPressedListener) {
		this.onBackPressedListener = onBackPressedListener;
	}

}
