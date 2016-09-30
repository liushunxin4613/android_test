package adapter;

import java.util.List;
import java.util.Map;

import com.leo.test1.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import base.adapter.BaseRootAdapter;
import android.widget.AbsListView.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewAdapter extends BaseRootAdapter<Map<String, Integer>> {

	private int fromArrId[];
	private String toArr[];
	
	private int num = 1;

	public GridViewAdapter(Context context, List<Map<String, Integer>> data, int resource,int fromArrId[],String toArr[]) {
		super(context, data, resource);
		this.fromArrId = fromArrId;
		this.toArr = toArr;
	}
	
	public void setNumColumns(int num){
		this.num = num;
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
			holder.img = (ImageView) view.findViewById(fromArrId[0]);
			holder.tv = (TextView) view.findViewById(fromArrId[1]);

			view.setTag(holder);
		}else {
			holder = (ViewHolder) view.getTag();
		}

		holder.img.setBackgroundResource(data.get(position).get(toArr[0]));
		holder.tv.setText(data.get(position).get(toArr[1]));
		
		int width = parent.getWidth()/num;
		view.setLayoutParams(new LayoutParams(width, LayoutParams.WRAP_CONTENT));

		View leftView = view.findViewById(R.id.item_view_left);
		View bottomView = view.findViewById(R.id.item_view_bottom);

		//判定leftView和bottomView的显示和隐藏
		int index = position + 1;
		if (index % num == 1) {
			leftView.setVisibility(View.GONE);
		}else {
			leftView.setVisibility(View.VISIBLE);
		}
		int a = data.size() % num;
		int b = data.size() / num;
		if (a == 0) b--;
		if (index > b * num) {
			bottomView.setVisibility(View.GONE);
		}else {
			bottomView.setVisibility(View.VISIBLE);
		}

		return view;
	}

	class ViewHolder{
		ImageView img;
		TextView tv;
	}


}
