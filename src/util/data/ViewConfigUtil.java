package util.data;

import com.leo.test1.R;

public final class ViewConfigUtil {

	/**
	 * actionBar center参数
	 * @author macos
	 *
	 */
	public static final class ActionBarCenterConfig{
		public static final int LAYOUT_ID = R.layout.actionbar_center;
		public static final int IMGBT_ID = R.id.ab_center_back;
		public static final int TITLE_ID = R.id.ab_center_title;
	}

	/**
	 * actionBar three参数
	 * @author macos
	 *
	 */
	public static final class ActionBarThreeConfig{
		public static final int LAYOUT_ID = R.layout.actionbar_three;
		public static final int IMGBT_FIND_ID = R.id.ab_three_find;
		public static final int IMGBT_LIST_ID = R.id.ab_three_list;
		public static final int TITLE_ID = R.id.ab_three_title;
	}

	/**
	 * actionBar title center参数
	 * @author macos
	 *
	 */
	public static class ActionBarTitleCenterConfig{
		public static final int LAYOUT_ID = R.layout.actionbar_title_center;
		public static final int TITLE_ID = R.id.ab_title_center_tv;
	}

	/**
	 * volley 
	 */
	public static final class VolleyConfig{
		/**
		 * 网络超时时间
		 */
		public static final int OUT_TIME = 5 * 1000;
		/**
		 * 重新请求次数
		 */
		public static final int RETREIS_NUM = 2;
	}

	/**
	 * 键值对
	 * @author macos
	 *
	 */
	public static final class KeyConfig{
		public static final String USERNAME = "username";
		public static final String PWD = "password";
		public static final String LOGIN_ARR[] = {
				"username"
				,"token"
		};
		public static final String ADDRESS_STRING = "address";
		public static final String TYPE_STRING = "type";
		public static final String NAME = "name";
		public static final String ID = "id";
		public static final String LIST = "list";
	}


	/**
	 *自定义spinnerlistview布局
	 */
	public static final class SpinnerListViewConfig{

		public static final int LAYOUT_ID = R.layout.activity_fupin_data;

		public static final int SPINNNER_ARR[] = {
				R.id.splv_sp0
				,R.id.splv_sp1
				,R.id.splv_sp2
		};

		public static final int LISTVIEW_ID = R.id.splv_dylistview;

	}

	/**
	 * item spinner的配置参数
	 * @author macos
	 *
	 */
	public static final class ItemSpinnerConfig{
		public static final int LAYOUT_ID = R.layout.item_spinner;
		public static final int TEXTVIEW_ID = R.id.item_sp_tv;
	}

	/**
	 * item Dynamiclistview的配置参数
	 * @author macos
	 *
	 */
	public static final class ItemDynamiclistviewConfig{
		public static final int LAYOUT_ID = R.layout.item_dynamiclistview;
		public static final int LISTVIEW_ID = R.id.item_dylistview;
	}

	/**
	 * json解析参数
	 * @author macos
	 *
	 */
	public static final class JsonDataConfig{
		public static final int LIST_NUM = 20;
		public static final int RETCODE_SUCCESS = 200;
		public static final String INDEX_RETCODE = "retcode";
		public static final String ROOT_JSON_KEY_ARR[] = {
				"retcode"
				,"list"
				,"msg"
				,"info"
				,"family_type"
		};
	}

}
