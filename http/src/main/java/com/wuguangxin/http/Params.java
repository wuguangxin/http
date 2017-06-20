package com.wuguangxin.http;

import android.text.TextUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 封装请求参数的HashMap便捷类，需要Google的gson.jar支持。
 *
 * Created by wuguangxin on 14/10/13
 */
public class Params extends HashMap<String, String> implements IParams {
    private static final long serialVersionUID = 1L;

    public Params() {
    }

	/**
	 * 重写put方法，吧value=null的转换为""
	 * @param key key
	 * @param value value
	 * @return Params
	 */
	@Override
	public String put(String key, String value){
		if(value == null)
			value = "";
		return super.put(key, value);
	}

    @Override
	public String optString(String key){
        return optString(key, null);
    }

	@Override
    public String optString(String key, String defValue){
        if(TextUtils.isEmpty(key)){
            return defValue;
        }
        String obj = get(key);
        if(obj != null) {
            return obj;
        }
        return defValue;
    }

	@Override
	public int optInt(String key){
        return optInt(key, 0);
    }

	@Override
    public int optInt(String key, int defValue){
        if(TextUtils.isEmpty(key)){
            return defValue;
        }
        Object obj = get(key);
        if(obj != null) {
            return Integer.valueOf(obj.toString());
        }
        return defValue;
    }

	@Override
	public String getUrlParams(){
		return getUrlParams(false);
	}

	@Override
	public String getUrlParams(boolean removeLastStr){
		if(isEmpty()){
			return null;
		}
		StringBuilder paramsBuilder = new StringBuilder();
		Iterator<String> paramsIterator = keySet().iterator();
		String key;
		while (paramsIterator.hasNext()) {
			key = paramsIterator.next();
			if(!TextUtils.isEmpty(key)){
				paramsBuilder.append(key).append("=").append(get(key)).append("&");
			}
		}
		if(removeLastStr){
			paramsBuilder.delete(paramsBuilder.length()-1, paramsBuilder.length()); 
		}
		return paramsBuilder.toString();
	}

	@Override
	public String toJSONString(){
		return toJSONObject().toString();
	}

	@Override
	public JSONObject toJSONObject(){
		return new JSONObject(this);
	}
}
