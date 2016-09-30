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
	 * ��������β�����Ԫ��
	 * 
	 * @param items
	 *            Ԫ�ؼ���
	 * @return �ų��ظ���Ԫ�أ�������ӳɹ��ĸ���
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
	 * ��������ͷ�����Ԫ��
	 * 
	 * @param items
	 *            Ԫ�ؼ���
	 * @return �ų��ظ���Ԫ�أ�������ӳɹ��ĸ���
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
	 * ����Ԫ�أ���comparator��Ϊ������������
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
	 * ��comparator��Ϊ������������
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
	 * ������ݼ��е���������
	 */
	public void clear(){
		data.clear();
		notifyDataSetChanged();
	}

	/**
	 * ɾ��ָ��λ�õ�Ԫ��
	 * 
	 * @param location
	 *            Ҫɾ����Ԫ�ص�λ��
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
	 * �ж��������������Ƿ��Ѿ������ö���
	 * 
	 * @param obj
	 * @return
	 */
	public abstract boolean isContains(T obj);
	
}
