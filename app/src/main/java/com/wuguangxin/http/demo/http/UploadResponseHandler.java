package com.wuguangxin.http.demo.http;

import android.text.TextUtils;

import com.wuguangxin.http.HttpError;
import com.wuguangxin.http.business.ResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 上传文件回调
 * 
 * @author wuguangxin
 * @date: 2015-6-4 下午6:03:14
 */
public class UploadResponseHandler extends ResponseHandler {

	@Override
	final public void onSuccess(String strResponse){
		super.onSuccess(strResponse);
		if (TextUtils.isEmpty(strResponse)) {
			onFailure(HttpError.DATA_PARSER_ERROR);
		} else {
			JSONObject rootResponseJson = null;
			try {
				rootResponseJson = new JSONObject(strResponse);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			if (rootResponseJson != null) {
				onSuccess(rootResponseJson);
			} else {
				onFailure(HttpError.DATA_PARSER_ERROR);
			}
		}
	}

	/**
	 * 上传成功后
	 * @param response
	 */
	public void onSuccess(JSONObject response){}
}
