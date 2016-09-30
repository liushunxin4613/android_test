package util.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import util.data.ViewConfigUtil.JsonDataConfig;

public class JsonUtil {

	public static final String TAG = "NoticeJsonUtil";
	
	public static JSONArray getResultJSONArray(JSONObject response){
		try {
			int retcode = response.getInt(JsonDataConfig.ROOT_JSON_KEY_ARR[0]);
			JSONArray result = response.getJSONArray(JsonDataConfig.ROOT_JSON_KEY_ARR[1]);
			if (retcode == JsonDataConfig.RETCODE_SUCCESS) {
				return result;
			}
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
		}
		return null;
	}
	
	public static Object getResult(String json){
		try {
			JSONObject response = new JSONObject(json);
			int retcode = response.getInt(JsonDataConfig.ROOT_JSON_KEY_ARR[0]);
			try {
				String msg = response.getString(JsonDataConfig.ROOT_JSON_KEY_ARR[2]);
				return msg;
			} catch (JSONException e) {
				JSONObject result = response.getJSONObject(JsonDataConfig.ROOT_JSON_KEY_ARR[3]);
				if (retcode == JsonDataConfig.RETCODE_SUCCESS) {
					return result;
				}
			}
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
		}
		return null;
	}
	
}
