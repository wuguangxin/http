package com.wuguangxin.http;

import android.text.TextUtils;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * 封装请求参数的HashMap便捷类，需要Google的gson.jar支持
 * @author wuguangxin
 * @date: 2014-10-13 下午4:15:38
 */
public class Params extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public Params() {
    }

    public String optString(String key){
        return optString(key, null);
    }

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

    public int optInt(String key){
        return optInt(key, 0);
    }

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
	public Params put(String key, Object value){
		if(value == null){
			value = "";
		}
		super.put(key, value);
		return this;
	}

	/**
	  * 获取URL参数串（默认保留最后的&）
	  * @return
	  */
	public String getUrlParams(){
		return getUrlParams(false);
	}
	
	/**
	 * 获取URL参数串（去除最后的&）
	 * @param removeLastStr 是否删除最后一个“&”符号
	 * @return
	 */
	public String getUrlParams(boolean removeLastStr){
		if(isEmpty()){
			return null;
		}
		StringBuilder paramsBuilder = new StringBuilder();
		Iterator<String> paramsIterator = keySet().iterator();
		String key = null;
		while (paramsIterator.hasNext()) {
			key = paramsIterator.next();
			if(!TextUtils.isEmpty(key)){
				Object value = get(key);
				if (value != null && !TextUtils.isEmpty(value.toString())) {
					paramsBuilder.append(key).append("=").append(value).append("&");
				}
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
	 * @return
	 */
	public JSONObject toJSONObject(){
		return new JSONObject(this);
	}
}
