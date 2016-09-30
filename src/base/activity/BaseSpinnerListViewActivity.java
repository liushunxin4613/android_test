package base.activity;

import adapter.Tv2VerAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import customLib.DynamicListView;
import customLib.DynamicListView.LoadMoreListener;
import customLib.DynamicListView.RefreshListener;

public class BaseSpinnerListViewActivity extends BaseActionBarCenterActivity implements LoadMoreListener,RefreshListener,OnItemClickListener,OnItemSelectedListener{

	protected Spinner mSpinnerArr[];
	protected DynamicListView mListView;
	protected Tv2VerAdapter mAdapter;

	private int spinnerArr[];
	private int listviewId;
	
	public static final int SP_ZHEN_ID = 1001;
	public static final int SP_CUN_ID = 1002;
	public static final int SP_TYPE_ID = 1003;

	/**
	 * �˷�����Ҫ��super.initView()֮ǰ���
	 * @param spinnerArr
	 * @param listviewId
	 */
	public void setSpinnerConfig(int spinnerArr[],int listviewId){
		this.spinnerArr = spinnerArr;
		this.listviewId = listviewId;
	}

	@Override
	public void initView() {
		super.initView();

		mSpinnerArr = new Spinner[spinnerArr.length];
		for (int i = 0; i < mSpinnerArr.length; i++) {
			mSpinnerArr[i] = (Spinner) findViewById(spinnerArr[i]);
			mSpinnerArr[i].setId(SP_ZHEN_ID + i);
			mSpinnerArr[i].setOnItemSelectedListener(this);
		}

		mListView = (DynamicListView) findViewById(listviewId);

		mListView.setOnMoreListener(this);
		mListView.setOnRefreshListener(this);
		mListView.setOnItemClickListener(this);

	}

	/**
	 * listview����¼�
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id){};

	/**
	 * ˢ������
	 */
	@Override
	public boolean onRefresh(DynamicListView dynamicListView) {
		return false;
	}

	/**
	 * ȡ��ˢ��,doneRefresh֮��Ĳ���,��ȡ��ˢ��֮��
	 */
	@Override
	public void onCancelRefresh(DynamicListView dynamicListView) {}

	/**
	 * ��������
	 */
	@Override
	public boolean onLoadMore(DynamicListView dynamicListView) {
		return false;
	}

	/**
	 * ȡ������,doneMore֮��Ĳ���,��ȡ������֮��
	 */
	@Override
	public void onCancelLoadMore(DynamicListView dynamicListView) {}

	/**
	 * spinnerѡ���¼�
	 */
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {}

	/**
	 * spinnerѡ���¼�
	 */
	@Override
	public void onNothingSelected(AdapterView<?> parent) {}

}
