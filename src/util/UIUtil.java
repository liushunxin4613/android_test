package util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class UIUtil {
	/**
	 * ��ʾ������ʾ
	 * 
	 * @param context
	 * @param resId
	 */
	public static void showToast(Context context, int resId) {
		if (context == null) return;
		Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
	}

	/**
	 * ��ʾ������ʾ
	 * 
	 * @param context
	 * @param msg
	 */
	public static void showToast(Context context, String msg) {
		if (context == null) return;
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
	
	public static void showLogI(String TAG,String key,Object obj){
		Log.i(TAG, key + " : " + obj);
	}
	
	/**
	 * �жϵ��λ���Ƿ�������ڲ���Χ��
	 * 
	 * @param view
	 * @param x
	 *            ����x����
	 * @param y
	 *            ����y����
	 * @return
	 */
	public static boolean isTouchInView(View view, int x, int y) {
		if (view == null) {
			return false;
		}
		int[] location = new int[2];
		view.getLocationInWindow(location);
		int leftOffset = location[0];
		int topOffset = location[1];
		if (x < leftOffset || x > leftOffset + view.getWidth()) {
			return false;
		}

		if (y < topOffset || y > topOffset + view.getHeight()) {
			return false;
		}

		return true;
	}

	/**
	 * ���������źͼ���ͷ��ͼƬ��������ڸߣ����Ը�Ϊ��׼���ţ����п���֮���Կ�Ϊ��׼���ţ����и�
	 *
	 * @param bitmap
	 *            Ҫ�����ͼƬ
	 * @param imageView
	 *            ���ͼƬ������
	 * @return
	 */
	public static Bitmap scaleImage(Bitmap bitmap, View imageView) {
		int height = bitmap.getHeight();
		int width = bitmap.getWidth();
		if (height > width) {
			Matrix matrix = new Matrix();
			float scale = imageView.getWidth() * 1.0f / bitmap.getWidth();
			matrix.postScale(scale, scale);
			int y = (height - width) / 2;
			return Bitmap.createBitmap(bitmap, 0, y, width, width, matrix,
					false);
		} else {
			Matrix matrix = new Matrix();
			float scale = imageView.getHeight() * 1.0f / bitmap.getHeight();
			matrix.postScale(scale, scale);
			int x = (width - height) / 2;
			return Bitmap.createBitmap(bitmap, x, 0, height, height, matrix,
					false);
		}
	}

	/**
	 * ���������źͼ���ͷ��ͼƬ��������ڸߣ����Ը�Ϊ��׼���ţ����п���֮���Կ�Ϊ��׼���ţ����и�
	 *
	 * @param bitmap
	 *            Ҫ�����ͼƬ
	 * @param w
	 * @param h
	 * @return
	 */
	public static Bitmap scaleImage(Bitmap bitmap, int w, int h) {
		int height = bitmap.getHeight();
		int width = bitmap.getWidth();
		float bitRatio = width * 1.0f / height;
		float viewRatio = w * 1.0f / h;
		if (bitRatio > viewRatio) {
			Matrix matrix = new Matrix();
			float scale = w * 1.0f / bitmap.getWidth();
			matrix.postScale(scale, scale);
			return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,
					true);
		} else {
			Matrix matrix = new Matrix();
			float scale = h * 1.0f / bitmap.getHeight();
			matrix.postScale(scale, scale);
			return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,
					true);
		}
	}

	/**
	 * ���źͼ���ͷ��ͼƬ��������ڸߣ����Ը�Ϊ��׼���ţ����п���֮���Կ�Ϊ��׼���ţ����и�
	 *
	 * @param bitmap
	 *            Ҫ�����ͼƬ
	 * @param len
	 *            �����������߳�
	 * @return ���ط������������bitmap
	 */
	public static Bitmap scaleImage(Bitmap bitmap, int len) {
		int height = bitmap.getHeight();
		int width = bitmap.getWidth();
		if (height > width) {
			Matrix matrix = new Matrix();
			float scale = len * 1.0f / bitmap.getWidth();
			matrix.postScale(scale, scale);
			int y = (height - width) / 2;
			return Bitmap.createBitmap(bitmap, 0, y, width, width, matrix,
					false);
		} else {
			Matrix matrix = new Matrix();
			float scale = len * 1.0f / bitmap.getHeight();
			matrix.postScale(scale, scale);
			int x = (width - height) / 2;
			return Bitmap.createBitmap(bitmap, x, 0, height, height, matrix,
					false);
		}
	}

	/**
	 * ���ݸ����Ĵ�С������Options��inSampleSize
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and
			// keeps both height and width larger than the requested height and
			// width.
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}

	/**
	 * ������м���ͼƬ�����ظ��ݿ�߲����ü���Bitmap����
	 * 
	 * @param context
	 * @param uri
	 *            ͼƬuri
	 * @param width
	 *            Ŀ����
	 * @param height
	 *            Ŀ��߶�
	 * @return
	 */
	public static Bitmap loadBitmap(Context context, Uri uri, int width,
			int height) {
		Bitmap bitmap = null;
		try {
			// ����ֻ�����������õ�options����
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(context.getContentResolver()
					.openInputStream(uri), null, options);
			// ��������inSampleSize
			options.inSampleSize = calculateInSampleSize(options, width, height);
			options.inJustDecodeBounds = false;
			// decodeʵ�������ͼƬ
			bitmap = BitmapFactory.decodeStream(context.getContentResolver()
					.openInputStream(uri), null, options);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	public static byte[] convertBitmapToBytes(Bitmap bitmap) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			ByteBuffer buffer = ByteBuffer.allocate(bitmap.getByteCount());
			bitmap.copyPixelsToBuffer(buffer);
			return buffer.array();
		} else {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			byte[] data = baos.toByteArray();
			return data;
		}
	}

	/**
	 * �����ֻ��ķֱ��ʴ� dp �ĵ�λ ת��Ϊ px(����)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * �����ֻ��ķֱ��ʴ� px(����) �ĵ�λ ת��Ϊ dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * ��ȡ��Ļ���
	 * 
	 * @param context
	 * @return
	 */
	public static int getDisplayWidth(Context context) {
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * ��ȡ��Ļ�߶�
	 * 
	 * @param context
	 * @return
	 */
	public static int getDisplayHeigth(Context context) {
		return context.getResources().getDisplayMetrics().heightPixels;
	}
}
