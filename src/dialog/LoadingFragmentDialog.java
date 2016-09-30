package dialog;

import base.dialog.BaseFragmentDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LoadingFragmentDialog extends BaseFragmentDialog {

	private CharSequence content;

	private TextView contentTV;

	private OnBackPressedLisener onBackPressedListener;

	public interface OnBackPressedLisener {
		
		public void onBackPressed();
	}

	public LoadingFragmentDialog() {
	}

	protected int resource;
	protected int itemId;
	
	public LoadingFragmentDialog(CharSequence content,int theme,int resource,int itemId) {
		this.content = content;
		this.theme = theme;
		this.resource = resource;
		this.itemId = itemId;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(resource, container,
				false);
		contentTV = (TextView) rootView.findViewById(itemId);

		if (content != null) {
			contentTV.setText(content);
			contentTV.setVisibility(View.VISIBLE);
		}
		
		if (getDialog() != null) {
			getDialog().setOnKeyListener(new OnKeyListener() {
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
		
		return rootView;
	}
	
	public OnBackPressedLisener getOnBackPressedListener() {
		return onBackPressedListener;
	}

	public void setOnBackPressedListener(
			OnBackPressedLisener onBackPressedListener) {
		this.onBackPressedListener = onBackPressedListener;
	}

}
