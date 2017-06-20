package com.wuguangxin.http;

import android.text.TextUtils;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * 封装请求参数的HashMap便捷类，需要Google的gson.jar支持。
 *
 * <p>Created by wuguangxin on 14/10/13 </p>
 */
public class Params extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public Params() {
    }

	/**
	 * 获取value
	 * @param key key
	 * @return value
	 */
	public String optString(String key){
        return optString(key, null);
    }

	/**
	 * 获取 String value
	 * @param key key
	 * @param defValue 默认 String value
	 * @return String value
	 */
    public String optString(String key, String defValue){
        if(TextUtils.isEmpty(key)){
            return defValue;
        }
        Object obj = get(key);
        if(obj != null) {
            return (String) obj;
        }
        return defValue;
    }

	/**
	 * 获取 int value
	 * @param key key
	 * @return int value
	 */
	public int optInt(String key){
        return optInt(key, 0);
    }

	/**
	 * 获取int value
	 * @param key key
	 * @param defValue 默认 int value
	 * @return int value
	 */
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

	/**
	 * 重写put方法，吧value=null的转换为""
	 * @param key key
	 * @param value value
	 * @return Params
	 */
    @Override
	public Params put(String key, Object value){
		if(value == null){
			value = "";
		}
		super.put(key, value);
		return this;
	}

	/**
	  * 获取URL参数串（默认保留最后的&）
	  * @return URL参数串
	  */
	public String getUrlParams(){
		return getUrlParams(false);
	}
	
	/**
	 * 获取URL参数串（去除最后的&）
	 * @param removeLastStr 是否删除最后一个“&”符号
	 * @return URL参数串
	 */
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
	
	/**
	 * 返回Json格式的字符串，并且按A~Z进行排序；key和value必须是String类型
	 * @return Json字符串
	 */
	public String toJSONString(){
		return toJSONObject().toString();
	}
	
	/**
	 * 返回Json对象JSONObject，并且按A~Z进行排序；key和value必须是String类型
	 * @return JSONObject
	 */
	public JSONObject toJSONObject(){
		return new JSONObject(this);
	}
}
