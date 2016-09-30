package inter;

public interface AcFmInterface {

	/**
	 * 初始化数据在设置母视图之前
	 */
	void initRootView();
	
	/**
	 * 返回母视图id
	 * @return
	 */
	int getRootViewId();
	
	/**
	 * 获取母视图后初始化监听
	 */
	void initView();
	
	/**
	 * 获取监听后初始化数据及UI
	 */
	void initData();
	
}
