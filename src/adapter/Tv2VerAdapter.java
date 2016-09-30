package adapter;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import base.adapter.BaseOrderlyAdapter;

public class Tv2VerAdapter extends BaseOrderlyAdapter<JSONObject> {

	private int arrId[];
	private String arr[];
	private String KEY;
	
	public Tv2VerAdapter(Context context, List<JSONObject> data, int resource,int arrId[],String arr[],String KEY) {
		super(context, data, resource);
		this.arrId = arrId;
		this.arr = arr;
		this.KEY = KEY;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		ViewHolder holder = null;
		View view = convertView;
		if(view == null){
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(resource, parent, false);
			
			holder = new ViewHolder();
			holder.tvArr = new TextView[arrId.length];
			for (int i = 0; i < holder.tvArr.length; i++) {
				holder.tvArr[i] = (TextView) view.findViewById(arrId[i]);
			}
			
			view.setTag(holder);
		}else {
			holder = (ViewHolder) view.getTag();
		}
		
		for (int i = 0; i < holder.tvArr.length; i++) {
			holder.tvArr[i].setText(data.get(position).optString(arr[i]));
		}
		
		return view;
	}

	class ViewHolder{
		TextView tvArr[];
	}

	@Override
	public boolean isContains(JSONObject obj) {
		for (JSONObject map:data) {
			try {
				if (map.getString(KEY).equals(obj.getString(KEY))) {
					return true;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
