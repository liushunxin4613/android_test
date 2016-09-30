package util.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import entity.User;

/**
 * 数据处理Util
 * 
 * @author liushunxin
 *
 */
public class DataUtil {

	public static final String TAG = "DataUtil";

	public static final String ROOT_SHAREPREFERENCE_USER_INFO = "user_info";
	public static final String ROOT_ADDRESS = "address";

	public static final String EDITOR_USER_USERNAME = "userUsername";
	public static final String EDITOR_USER_PWD = "userPwd";
	public static final String EDITOR_USER_IS_PWD = "userSavePwd";
	public static String APPLICATION_ISSUE_DATA = "issueData";
	public static String EDITOR_USER_ID = "userId";
	public static String EDITOR_USER_NAME = "userName";
	public static String EDITOR_USER_EMAIL = "userEmail";

	/**
	 * 保存登录信息到本地
	 * 
	 * @param context
	 * @param user
	 * @param isLogin
	 */
	public static void saveUserLoginInfo(Context context,User user,boolean isLogin){
		if(user == null) return;
		SharedPreferences sharedPre = context.getSharedPreferences(
				ROOT_SHAREPREFERENCE_USER_INFO, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPre.edit();
		editor.putString(EDITOR_USER_USERNAME, user.getUsername());
		if (isLogin) {
			editor.putString(EDITOR_USER_PWD,user.getPwd());
		}else {
			editor.putString(EDITOR_USER_PWD,null);
		}
		editor.commit();

	}

	/**
	 * 存入单体信息
	 */
	public static void saveInfo(Context context,String root,String key,String value){
		SharedPreferences sharedPre = context.getSharedPreferences(
				root, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPre.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * 存入map信息
	 */
	@SuppressWarnings("unchecked")
	public static void saveMapInfo(Context context,String root,Map<String, Object> map){
		SharedPreferences sharedPre = context.getSharedPreferences(
				root, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPre.edit();

		Iterator<Entry<String,Object>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String,Object> entry = (Entry<String, Object>) iter.next();
			if (entry.getValue() instanceof String) {
				editor.putString(entry.getKey(), (String) entry.getValue());
			}else if (entry.getValue() instanceof Set) {
				editor.putStringSet(entry.getKey(), (Set<String>) entry.getValue());
			}else if (entry.getValue() instanceof Boolean) {
				editor.putBoolean(entry.getKey(), (Boolean) entry.getValue());
			}else if (entry.getValue() instanceof Float) {
				editor.putFloat(entry.getKey(), (Float) entry.getValue());
			}else if (entry.getValue() instanceof Integer) {
				editor.putInt(entry.getKey(), (Integer) entry.getValue());
			}else if (entry.getValue() instanceof Long) {
				editor.putLong(entry.getKey(), (Long) entry.getValue());
			}
		}

		editor.commit();
	}

	//	public static void saveMapInfo(Context context,String root,Map<String, String> map){
	//		SharedPreferences sharedPre = context.getSharedPreferences(
	//				root, Activity.MODE_PRIVATE);
	//		SharedPreferences.Editor editor = sharedPre.edit();
	//		
	//		Iterator<Entry<String,String>> iter = map.entrySet().iterator();
	//		while (iter.hasNext()) {
	//			Entry<String,String> entry = (Entry<String, String>) iter.next();
	//			editor.putString(entry.getKey(), entry.getValue());
	//		}
	//		
	//		editor.commit();
	//	}

	/**
	 * 获取信息
	 */
	public static String getInfo(Context context,String root,String key){
		SharedPreferences sharedPre = context.getSharedPreferences(
				root, Activity.MODE_PRIVATE);
		return sharedPre.getString(key,null);
	}
	
	/**
	 * 获取set信息
	 */
	public static Set<String> getSetInfo(Context context,String root,String key){
		SharedPreferences sharedPre = context.getSharedPreferences(
				root, Activity.MODE_PRIVATE);
		return sharedPre.getStringSet(key,null);
	}

	/**
	 * 获取Map信息
	 */
	public static Map<String, String> getMapInfo(Context context,String root,List<String> keyList){
		SharedPreferences sharedPre = context.getSharedPreferences(
				root, Activity.MODE_PRIVATE);
		Map<String, String> map = new HashMap<String, String>();
		for (String key : keyList) {
			map.put(key, sharedPre.getString(key, null));
		}
		return map;
	}

	public static void cleanInfo(Context context){
		SharedPreferences sharedPre = context.getSharedPreferences(
				ROOT_SHAREPREFERENCE_USER_INFO, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPre.edit();
		editor.clear();
		editor.commit();
	}

	/**
	 * 从本地获取登录信息
	 * 
	 * @param context
	 * @return
	 */
	public static User getUserLoginInfo(Context context){
		SharedPreferences sharedPre = context.getSharedPreferences(
				ROOT_SHAREPREFERENCE_USER_INFO, Activity.MODE_PRIVATE);
		String username = sharedPre.getString(EDITOR_USER_USERNAME,null);
		String pwd = sharedPre.getString(EDITOR_USER_PWD,null);

		if (!isIsPwd(context)) {
			pwd = null;
		}

		User user = new User(username, pwd);
		return user;
	}

	/**
	 * 是否保存密码到本地
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isIsPwd(Context context){
		SharedPreferences sharedPre = context.getSharedPreferences(
				ROOT_SHAREPREFERENCE_USER_INFO, Activity.MODE_PRIVATE);
		boolean isSavePed = sharedPre.getBoolean(EDITOR_USER_IS_PWD,false);
		return isSavePed;
	}

	/**
	 * 获取是否从本地保存密码的信息
	 * 
	 * @param context
	 * @param isPwd
	 */
	public static void saveIsPwd(Context context,boolean isPwd){
		SharedPreferences sharedPre = context.getSharedPreferences(
				ROOT_SHAREPREFERENCE_USER_INFO, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPre.edit();
		editor.putBoolean(EDITOR_USER_IS_PWD,isPwd);
		editor.commit();
	}

}
