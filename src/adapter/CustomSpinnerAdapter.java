package adapter;

import java.util.ArrayList;
import java.util.List;

import com.leo.test1.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import base.adapter.BaseRootAdapter;
import entity.Find;

public class CustomSpinnerAdapter extends BaseRootAdapter<Find> implements SpinnerAdapter{
	
	private List<DataSetObserver> observerList = new ArrayList<DataSetObserver>();

	private int textviewId;
	
	public CustomSpinnerAdapter(Context context, List<Find> data, int resource,int textviewId) {
		super(context, data, resource);
		this.textviewId = textviewId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		View view = convertView;
		if(view == null){
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(resource, parent, false);
			holder = new ViewHolder();
			holder.textView = (TextView) view.findViewById(textviewId);
			view.setTag(holder);
		}else {
			holder = (ViewHolder) view.getTag();		
		}
		holder.textView.setText(data.get(position).getName());
		return view;
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {//弹出窗的视图
		TextView textView = new TextView(context);
		textView.setText(data.get(position).getName());
		textView.setGravity(Gravity.CENTER);
		textView.setTextSize(16);
		textView.setTextColor(R.color.textview);
		textView.setPadding(10, 20, 10, 20);
		return textView;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		if (observer == null)
			throw new NullPointerException("observer不可为空");
		if (!observerList.contains(observer))
			observerList.add(observer);
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		if (observer == null)
			throw new NullPointerException("observer不可为空");
		if (!observerList.contains(observer))
			throw new Resources.NotFoundException("observer未注册");
		observerList.remove(observer);
	}
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Find getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}
	/**
	 * 不关注此功能
	 * @param position
	 * @return
	 */
	@Override
	public int getItemViewType(int position) {
		return 0;
	}

	/**
	 * 不关注此功能
	 * @return
	 */
	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	private class ViewHolder {
		TextView textView;
	}
}
