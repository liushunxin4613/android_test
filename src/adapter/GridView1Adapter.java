package adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import base.adapter.BaseRootAdapter;
import entity.GridViewInfo;

public class GridView1Adapter extends BaseRootAdapter<GridViewInfo> {

	private int fromArrId[];
	
	private int pad = 10;
	
	private int minImgWidth = 96;
	
	public GridView1Adapter(Context context, List<GridViewInfo> data, int resource,int fromArrId[]) {
		super(context, data, resource);
		this.fromArrId = fromArrId;
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
			holder.ly = (LinearLayout) view.findViewById(fromArrId[2]);
			
			holder.lyy = (LinearLayout) view.findViewById(fromArrId[3]);
			
			view.setTag(holder);
		}else {
			holder = (ViewHolder) view.getTag();
		}

		holder.img.setBackgroundResource(data.get(position).getImg());
		holder.tv.setText(data.get(position).getText());
		holder.ly.setBackgroundResource(data.get(position).getColor());
		
		holder.lyy.setLayoutParams(new LayoutParams(minImgWidth, minImgWidth));
		
		view.setPadding(pad, pad, pad, pad);
		
		return view;
	}

	class ViewHolder{
		ImageView img;
		TextView tv;
		LinearLayout ly;
		LinearLayout lyy;
	}
	
}