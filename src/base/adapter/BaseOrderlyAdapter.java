package base.adapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseOrderlyAdapter<T> extends BaseRootAdapter<T> {

	public BaseOrderlyAdapter(Context context, List<T> data, int resource) {
		super(context, data, resource);
	}

	@Override
	public abstract View getView(int position, View convertView, ViewGroup parent);

	/**
	 * 向适配器尾部添加元素
	 * 
	 * @param items
	 *            元素集合
	 * @return 排除重复的元素，返回添加成功的个数
	 */
	public int addItems(List<T> items) {
		int addCount = 0;
		for (T t : items) {
			if (!isContains(t)) {
				data.add(t);
				addCount++;
			}
		}
		if (addCount > 0)
			notifyDataSetChanged();
		return addCount;
	}

	/**
	 * 向适配器头部添加元素
	 * 
	 * @param items
	 *            元素集合
	 * @return 排除重复的元素，返回添加成功的个数
	 */
	public int addItemsToHead(List<T> items) {
		int addCount = 0;
		for (T t : items) {
			if (!isContains(t)) {
				data.add(addCount, t);
				addCount++;
			}
		}
		if (addCount > 0)
			notifyDataSetChanged();
		return addCount;
	}

	protected Comparator<T> comparator;
	
	/**
	 * 插入元素，若comparator不为空则重新排序
	 * 
	 * @param obj
	 */
	public void insertItem(T obj) {
		data.add(obj);
		if (comparator != null)
			Collections.sort(data, comparator);
		notifyDataSetChanged();
	}

	/**
	 * 若comparator不为空则重新排序
	 */
	public void reSortData() {
		if (comparator != null)
			Collections.sort(data, comparator);
		notifyDataSetChanged();
	}
	
	public void orderData(){
		Collections.reverse(data);
		notifyDataSetChanged();
	}
	
	/**
	 * 清除数据集中的所有数据
	 */
	public void clear(){
		data.clear();
		notifyDataSetChanged();
	}

	/**
	 * 删除指定位置的元素
	 * 
	 * @param location
	 *            要删除的元素的位置
	 */
	public void reomveItem(int location) {
		data.remove(location);
		notifyDataSetChanged();
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	/**
	 * 判断适配器集合中是否已经包含该对象
	 * 
	 * @param obj
	 * @return
	 */
	public abstract boolean isContains(T obj);
	
}
