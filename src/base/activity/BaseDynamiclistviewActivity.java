package base.activity;

import java.util.List;

import org.json.JSONObject;

import adapter.Tv2VerAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import customLib.DynamicListView;
import customLib.DynamicListView.LoadMoreListener;
import customLib.DynamicListView.RefreshListener;
import util.VolleyUtil.OnVolleyResponseListener;
import util.data.ViewConfigUtil.ItemDynamiclistviewConfig;

public class BaseDynamiclistviewActivity extends BaseActionBarCenterActivity implements OnItemClickListener,RefreshListener,LoadMoreListener,OnVolleyResponseListener{

	@Override
	public int getRootViewId() {
		return ItemDynamiclistviewConfig.LAYOUT_ID;
	}

	protected Tv2VerAdapter adapter;
	protected DynamicListView listview;

	protected final int REFRESH_WHAT = 0;
	protected final int LOADMORE_WHAT = 1;
	protected final int INIT_WHAT = 2;

	@Override
	public void initView() {
		super.initView();

		listview = (DynamicListView) findViewById(ItemDynamiclistviewConfig.LISTVIEW_ID);

		listview.setOnItemClickListener(this);
		listview.setOnRefreshListener(this);
		listview.setOnMoreListener(this);
	}

	@Override
	public void onListResponse(int what,List<JSONObject> data) {
		switch (what) {
		case REFRESH_WHAT://刷新数据
			adapter.addItemsToHead(data);
			listview.doneRefresh();
			listview.setOnMoreListener(this);
			break;
		case LOADMORE_WHAT://加载数据
			adapter.addItems(data);
			listview.doneMore();
			break;
		}
	}

	@Override
	public boolean onLoadMore(DynamicListView dynamicListView) {
		return false;
	}

	@Override
	public void onCancelLoadMore(DynamicListView dynamicListView) {
	}

	@Override
	public boolean onRefresh(DynamicListView dynamicListView) {
		return false;
	}

	@Override
	public void onCancelRefresh(DynamicListView dynamicListView) {
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	}

}
