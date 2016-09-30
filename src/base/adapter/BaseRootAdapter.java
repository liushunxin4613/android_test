package base.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class BaseRootAdapter<T> extends BaseAdapter {

	protected List<T> data;
	protected Context context;
	protected int resource;

	public BaseRootAdapter(Context context,List<T> data) {
		this.data = data;
		this.context = context;
	}

	public BaseRootAdapter(Context context,List<T> data,int resource) {
		this.data = data;
		this.context = context;
		this.resource = resource;
	}

	public void setData(List<T> data){
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public T getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public abstract View getView(int position, View convertView, ViewGroup parent);

}
