package customLib;

import com.leo.test1.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import util.UIUtil;

/**
 * ��̬ˢ�ºͼ�������ListView
 * 
 */
public class DynamicListView extends ListView implements OnScrollListener {

	/**
	 * ������ �����ؼ���ˢ�»��߼��ظ����¼� ���е���Ŀ�¼�������һ��ƫ������Ҳ����positionӦ�ü�1�������������е���Ŀ
	 * 
	 */
	public interface RefreshListener {
		/**
		 * ˢ������
		 * 
		 * @param dynamicListView
		 * @return true:ˢ�¶�����ɣ�ˢ�µĶ����Զ���ʧ
		 *         false:ˢ��δ��ɣ���Ҫ�����ݼ������֮��ȥ���ÿؼ���doneRefresh ()����
		 */
		public boolean onRefresh(DynamicListView dynamicListView);

		/**
		 * ȡ��ˢ������
		 * 
		 * @param dynamicListView
		 */
		public void onCancelRefresh(DynamicListView dynamicListView);

	}

	public interface LoadMoreListener {
		/**
		 * ��������
		 * 
		 * @param dynamicListView
		 * @return true:���ظ��ද����ɣ����ظ���Ķ����Զ���ʧ
		 *         false:���ظ���δ��ɣ���Ҫ�����ݼ������֮��ȥ���ÿؼ���doneMore()����
		 */
		public boolean onLoadMore(DynamicListView dynamicListView);

		/**
		 * ȡ����������
		 * 
		 * @param dynamicListView
		 */
		public void onCancelLoadMore(DynamicListView dynamicListView);
	}

	/**
	 * ״̬�ؼ���StatusView���б�ͷ�Ϻ͵׶˵ģ���״̬ö��
	 * 
	 * @author Bao
	 * 
	 */
	enum ViewStatus {
		none, normal, will, ing, stop
	}

	/**
	 * ״̬�ؼ�
	 * 
	 */
	class StatusView extends LinearLayout {
		public int height;
		public int width;

		private ProgressBar progressBar = null;
		private TextView textView = null;

		private ViewStatus viewStatus = ViewStatus.none;

		private int normalrefreshString = R.string.refresh_normal;
		private int willrefreshString = R.string.refresh_will;
		private int refreshingString = R.string.refreshing;
		private int stopfrefreshString = R.string.refresh_stop;
		private int normalloadmoreString = R.string.loadmore_normal;
		private int willloadmoreString = R.string.loadmore_will;
		private int loadmoreingString = R.string.loadmoreing;
		private int stopfloadmoreString = R.string.loadmore_stop;

		public StatusView(Context context, AttributeSet attrs) {
			super(context, attrs);
			initThis(context);
		}

		public StatusView(Context context) {
			super(context);
			initThis(context);
		}

		@SuppressWarnings("deprecation")
		private void initThis(Context context) {
			this.setOrientation(LinearLayout.HORIZONTAL);
			this.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

			progressBar = new ProgressBar(context);
			progressBar.setLayoutParams(new LinearLayout.LayoutParams(60, 60));
			progressBar.setIndeterminate(false);
			progressBar.setIndeterminateDrawable(getResources().getDrawable(R.drawable.refreshing));

			textView = new TextView(context);
			textView.setPadding(5, 20, 0, 20);

			this.addView(progressBar);
			this.addView(textView);

			int w = View.MeasureSpec.makeMeasureSpec(0,
					View.MeasureSpec.UNSPECIFIED);
			int h = View.MeasureSpec.makeMeasureSpec(0,
					View.MeasureSpec.UNSPECIFIED);
			this.measure(w, h);

			height = this.getMeasuredHeight();
			width = this.getMeasuredWidth();

			this.setStatus(true,ViewStatus.normal);
		}

		public ViewStatus getStatus() {
			return viewStatus;
		}

		public void setStatus(boolean isRefresh,ViewStatus status) {
			if (this.viewStatus != status) {
				this.viewStatus = status;
				if (status == ViewStatus.ing) {
					this.progressBar.setVisibility(View.VISIBLE);
				} else {
					this.progressBar.setVisibility(View.GONE);
				}
				statusString(isRefresh);
				this.invalidate();
			}
		}

		private void statusString(boolean isRefresh) {
			int strId = 0;
			switch (viewStatus) {
			case normal:
				if (isRefresh) {
					strId = normalrefreshString;
				}else {
					strId = normalloadmoreString;
				}
				progressBar.setProgress(0);
				break;
			case will:
				if (isRefresh) {
					strId = willrefreshString;
				} else {
					strId = willloadmoreString;
				}
				break;
			case ing:
				if (isRefresh) {
					strId = refreshingString;
				} else {
					strId = loadmoreingString;
				}
				break;
			case stop:
				if (isRefresh) {
					strId = stopfrefreshString;
				} else {
					strId = stopfloadmoreString;
				}
				break;
			default:
				break;
			}
			textView.setText(strId);
		}

	}

	private StatusView refreshView;
	private StatusView moreView;
	/**
	 * 
	 */
	private boolean isRecord = false;
	/**
	 * �Ƿ�ֹͣˢ��
	 */
	private boolean isStopRefresh = false;
	/**
	 * �Ƿ�ֹͣ����
	 */
	private boolean isStopLoad = false;
	private int downY = -1;
	private final float minTimesToRefresh = 2.5f;
	private final static int ITEM_FLAG_FIRST = 0x0001;
	private final static int ITEM_FLAG_NONE = 0;
	private final static int ITEM_FLAG_LAST = 0x0010;

	/**
	 * ListView����λ��־λ
	 */
	private int itemFlag = ITEM_FLAG_FIRST;

	// ����������
	private RefreshListener onRefreshListener;
	private LoadMoreListener onMoreListener;
	// �������Ͷ˵�ʱ���Ƿ��Զ����ظ���
	private boolean doMoreWhenBottom = false;

	public DynamicListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initThis(context);
	}

	public DynamicListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initThis(context);
	}

	public DynamicListView(Context context) {
		super(context);
		initThis(context);
	}

	private void initThis(Context context) {
		refreshView = new StatusView(context);
		moreView = new StatusView(context);
		this.addHeaderView(refreshView, null, false);
		this.addFooterView(moreView, null, false);
		this.setOnScrollListener(this);
		doneRefresh();
		doneMore();
	}

	public View getViewByPosition(int pos) {
		final int firstListItemPosition = getFirstVisiblePosition();
		final int lastListItemPosition = firstListItemPosition + getChildCount() - 1;

		if (pos < firstListItemPosition || pos > lastListItemPosition ) {
			return getAdapter().getView(pos, null, this);
		} else {
			final int childIndex = pos - firstListItemPosition;
			return getChildAt(childIndex);
		}
	}

	// ����������
	public void setOnRefreshListener(RefreshListener onRefreshListener) {
		this.onRefreshListener = onRefreshListener;
	}

	public LoadMoreListener getOnMoreListener() {
		return onMoreListener;
	}

	public void setOnMoreListener(LoadMoreListener onMoreListener) {
		this.onMoreListener = onMoreListener;
	}

	public void setDoMoreWhenBottom(boolean doMoreWhenBottom) {
		this.doMoreWhenBottom = doMoreWhenBottom;
	}

	// ����
	public boolean isDoMoreWhenBottom() {
		return doMoreWhenBottom;
	}

	public RefreshListener getOnRefreshListener() {
		return onRefreshListener;
	}

	@Override
	public void onScroll(AbsListView l, int t, int oldl, int count) {
		if (t == 0)
			itemFlag = itemFlag | ITEM_FLAG_FIRST;
		else if ((t + oldl) == count) {
			itemFlag = itemFlag | ITEM_FLAG_LAST;
			if (doMoreWhenBottom && onMoreListener != null
					&& moreView.getStatus() != ViewStatus.ing) {
				doMore();
			}
		} else {
			itemFlag = itemFlag & ITEM_FLAG_NONE;
			// isRecorded = false;
		}

	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// �ڶ���
			if (!isRecord && (itemFlag & ITEM_FLAG_FIRST) == ITEM_FLAG_FIRST
			&& onRefreshListener != null) {
				if (refreshView.getStatus() == ViewStatus.normal) {
					downY = (int) ev.getY(0);
					isRecord = true;
				} else if (refreshView.getStatus() == ViewStatus.ing) {
					downY = (int) ev.getY(0);
					isRecord = true;
					isStopRefresh = true;
				}
			}
			// �ڵײ�
			if (!isRecord && (itemFlag & ITEM_FLAG_LAST) == ITEM_FLAG_LAST
					&& onMoreListener != null) {
				if (moreView.getStatus() == ViewStatus.normal) {
					downY = (int) ev.getY(0);
					isRecord = true;
				} else if (moreView.getStatus() == ViewStatus.ing) {
					downY = (int) ev.getY(0);
					isRecord = true;
					isStopLoad = true;
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			isRecord = false;
			if (onRefreshListener != null
					&& refreshView.getStatus() == ViewStatus.will) {
				doRefresh();
			} else if (refreshView.getStatus() == ViewStatus.normal) {
				refreshView.setPadding(0, -1 * refreshView.height, 0, 0);
			} else if (refreshView.getStatus() == ViewStatus.stop) {
				isStopRefresh = false;
				doneRefresh();
				refreshView.setPadding(0, -1 * refreshView.height, 0, 0);
			} else if (refreshView.getStatus() == ViewStatus.ing
					&& isStopRefresh) {
				refreshView.setPadding(0, 10, 0, 10);
			}

			if (onMoreListener != null
					&& moreView.getStatus() == ViewStatus.will) {
				doMore();
			} else if (moreView.getStatus() == ViewStatus.normal) {
				moreView.setPadding(0, 0, 0, -1 * moreView.height);
			} else if (moreView.getStatus() == ViewStatus.stop) {
				isStopLoad = false;
				doneMore();
				moreView.setPadding(0, 0, 0, -1 * moreView.height);
			} else if (moreView.getStatus() == ViewStatus.ing
					&& isStopLoad) {
				moreView.setPadding(0, 10, 0, 10);
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (!isRecord && (itemFlag & ITEM_FLAG_FIRST) == ITEM_FLAG_FIRST
			&& onRefreshListener != null) {
				if (refreshView.getStatus() == ViewStatus.normal) {
					downY = (int) ev.getY(0);
					isRecord = true;
				} else if (refreshView.getStatus() == ViewStatus.ing) {
					downY = (int) ev.getY(0);
					isRecord = true;
					isStopRefresh = true;
				}
			} else if (!isRecord
					&& (itemFlag & ITEM_FLAG_LAST) == ITEM_FLAG_LAST
					&& onMoreListener != null) {
				if (moreView.getStatus() == ViewStatus.normal) {
					downY = (int) ev.getY(0);
					isRecord = true;
				} else if (moreView.getStatus() == ViewStatus.ing) {
					isRecord = true;
					isStopLoad = true;
				}
			} else if (isRecord) {
				int nowY = (int) ev.getY(0);
				int offset = nowY - downY;
				// �������ӵĳ���
				double increase = arctan(Math.abs(offset));
				if (isStopRefresh && offset > 0
						&& (itemFlag & ITEM_FLAG_FIRST) == ITEM_FLAG_FIRST
						&& onRefreshListener != null) {// ֹͣˢ��
					// ����
					setSelection(0);
					if (offset >= (minTimesToRefresh * refreshView.height)) {
						refreshView.setStatus(true,ViewStatus.stop);
					} else {
						refreshView.setStatus(true,ViewStatus.ing);
					}
					refreshView.setPadding(0, (int) increase, 0, 0);
				} else if (isStopLoad && offset < 0
						&& (itemFlag & ITEM_FLAG_LAST) == ITEM_FLAG_LAST
						&& onMoreListener != null) {
					// ����
					setSelection(this.getCount());
					if (offset <= -1 * (minTimesToRefresh * moreView.height)) {
						moreView.setStatus(false,ViewStatus.stop);
					} else {
						moreView.setStatus(false,ViewStatus.ing);
					}
					moreView.setPadding(0, 0, 0, (int) increase);
				} else { // ˢ��
					if (offset > 0
							&& (itemFlag & ITEM_FLAG_FIRST) == ITEM_FLAG_FIRST
							&& onRefreshListener != null) {
						// ����
						setSelection(0);
						if (offset >= (minTimesToRefresh * refreshView.height)) {
							refreshView.setStatus(true,ViewStatus.will);
						} else {
							refreshView.setStatus(true,ViewStatus.normal);
						}

						refreshView.setPadding(0, (int) increase
								- refreshView.height, 0, 0);
					} else if (offset < 0
							&& (itemFlag & ITEM_FLAG_LAST) == ITEM_FLAG_LAST
							&& onMoreListener != null) {
						// ����
						setSelection(this.getCount());
						if (offset <= -1
								* (minTimesToRefresh * moreView.height)) {
							moreView.setStatus(false,ViewStatus.will);
						} else {
							moreView.setStatus(false,ViewStatus.normal);
						}
						moreView.setPadding(0, 0, 0, (int) increase
								- refreshView.height);
					}
				}

			}
			break;
		default:
			break;
		}

		return super.onTouchEvent(ev);
	}

	/**
	 * �����Һ���������ƫ���� ���100dp
	 * 
	 * @param x
	 * @return
	 */
	public double arctan(int x) {
		int len = UIUtil.dip2px(getContext(), 500);
		double a = 2 * len / Math.PI;
		double y = a * Math.atan(x / (len * 1.5));
		return y;
	}

	/**
	 * ��ʼˢ��
	 */
	public void doRefresh() {
		refreshView.setStatus(true,ViewStatus.ing);
		refreshView.setPadding(0, 10, 0, 10);
		if (onRefreshListener.onRefresh(this))
			doneRefresh();
	}

	/**
	 * ��ʼ���ظ���
	 */
	public void doMore() {
		moreView.setStatus(false,ViewStatus.ing);
		moreView.setPadding(0, 10, 0, 10);
		if (onMoreListener.onLoadMore(this))
			doneMore();
	}

	/**
	 * ˢ�����֮����ã�����ȡ��ˢ�µĶ���
	 */
	public void doneRefresh() {
		refreshView.setStatus(true,ViewStatus.normal);
		refreshView.setPadding(0, -1 * refreshView.height, 0, 0);
		if (onRefreshListener != null)
			onRefreshListener.onCancelRefresh(this);
	}

	/**
	 * ���ظ������֮����ã�����ȡ�����ظ���Ķ���
	 */
	public void doneMore() {
		moreView.setStatus(false,ViewStatus.normal);
		moreView.setPadding(0, 0, 0, -1 * moreView.height);
		if (onMoreListener != null)
			onMoreListener.onCancelLoadMore(this);
	}

	/**
	 * ��ȡˢ�µ�״̬
	 * 
	 * @return һ�� ��Ҫˢ�� ˢ�����
	 */
	public ViewStatus getRefreshStatus() {
		return refreshView.getStatus();
	}

	/**
	 * ��ȡ���ظ����״̬
	 * 
	 * @return һ�� ��Ҫ���� �������
	 */
	public ViewStatus getMoreStatus() {
		return moreView.getStatus();
	}

}
