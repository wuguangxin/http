package com.wuguangxin.http.demo.http;

import android.text.TextUtils;
import android.util.Log;

import com.wuguangxin.http.HttpError;
import com.wuguangxin.http.business.ResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 请求服务器时传递该对象，并在该对象的相关方法中做业务处理
 *
 * @author wuguangxin
 */
public class JsonResponseHandler extends ResponseHandler {
    private static final String TAG = "JsonResponseHandler";
    private boolean isDecode = false;
    private String key;

    @Override
    public void onStart(String key) {
        super.onStart(key);
        this.key = key;
    }

    @Override
    final public void onSuccess(String strResponse) {
        Log.e(TAG, "strResponse: " + strResponse);
        strResponse = decode(strResponse);
        if (TextUtils.isEmpty(strResponse)) {
            onFailure(HttpError.DATA_PARSER_ERROR);
        } else {
            JSONObject responseObj = null;
            try {
                responseObj = new JSONObject(strResponse);
            } catch (JSONException e) {
                e.printStackTrace();
                responseObj = null;
            }
            if (responseObj == null) {
                onFailure(HttpError.DATA_PARSER_ERROR);
            } else {
                Log.e(TAG, "responseObj = " + responseObj.toString());
                onSuccess(responseObj);
                onSuccess(responseObj, key);
                int code = responseObj.optInt("resultCode");
                String msg = responseObj.optString("resultMsg");
                JSONObject data = responseObj.optJSONObject("resultDate");
                String dataString = responseObj.optString("resultDate");
                JSONArray dataJsonArray = responseObj.optJSONArray("resultDate");
                if (code == 0 && TextUtils.isEmpty(msg)) {
                    msg = HttpError.SERVER_HANDLE_EXCEPTION;
                }
                onSuccess(data, code, msg);
                onSuccess(data, code, msg, key);
                onSuccess(dataString, code, msg);
                onSuccess(dataString, code, msg, key);
                onSuccess(dataJsonArray, code, msg, key);
            }
        }
    }

    /**
     * 当成请求服务器成功时，返回一个JSON对象,该JSON为服务器返回的解密后数据
     *
     * @param response JSONObject类型
     */
    public void onSuccess(JSONObject response) {
    }

    /**
     * 当成请求服务器成功时，返回一个JSON对象,该JSON为服务器返回的解密后数据
     *
     * @param response JSONObject类型
     * @param key 请求地址的MD5值，可作为缓存数据的为一似值
     */
    public void onSuccess(JSONObject response, String key) {
    }

    /**
     * 当成请求服务器成功时，返回returnData、returnCode、returnMsg
     *
     * @param data JSONObject类型
     * @param code 返回码
     * @param msg 返回信息
     */
    public void onSuccess(JSONObject data, int code, String msg) {
    }

    /**
     * 当成请求服务器成功时，返回returnData、returnCode、returnMsg, [key]
     *
     * @param data 为resultDate数据, JSONObject类型
     * @param code 返回码
     * @param msg 返回信息
     * @param key 请求地址的MD5值，可作为缓存数据的为一似值
     */
    public void onSuccess(JSONObject data, int code, String msg, String key) {
    }

    /**
     * 当成请求服务器成功时，返回returnData、returnCode、returnMsg, [key]
     *
     * @param data 为resultDate数据,String类型
     * @param code 返回码
     * @param msg 返回信息
     */
    public void onSuccess(String data, int code, String msg) {
    }

    /**
     * 当成请求服务器成功时，返回returnData、returnCode、returnMsg, [key]
     *
     * @param data String类型
     * @param code 返回码
     * @param msg 返回信息
     * @param key 请求地址的MD5值，可作为缓存数据的为一似值
     */
    public void onSuccess(String data, int code, String msg, String key) {
    }

    /**
     * 当成请求服务器成功时，返回returnData、returnCode、returnMsg, [key]
     *
     * @param data JSONArray类型
     * @param code 返回码
     * @param msg 返回信息
     * @param key 请求地址的MD5值，可作为缓存数据的为一似值
     */
    public void onSuccess(JSONArray data, int code, String msg, String key) {
    }

    /**
     * 加密参数
     */
    @Override
    public String encode(String text) {
        return text;
    }

    /**
     * 解密参数
     */
    @Override
    public String decode(String text) {
        return text;
    }
}

