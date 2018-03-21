package com.wuguangxin.http;

import android.text.TextUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 封装请求参数的HashMap便捷类，需要Google的gson.jar支持。
 * Created by wuguangxin on 14/10/13
 */
public class Params extends HashMap<String, Object> implements IParams {
    private static final long serialVersionUID = 1L;

    public Params() {
    }

    public Params(Params params) {
        if (params != null) {
            for (String key : params.keySet()) {
                put(key, params.get(key));
            }
        }
    }

    public Params(Map<String, String> map) {
        if (map != null) {
            for (String key : map.keySet()) {
                put(key, map.get(key));
            }
        }
    }

    /**
     * 插入key-value键值对，如果key或者value为null或""，则不插入，如果Value不是String类型时，转换为String
     *
     * @param key key
     * @param value Object
     * @return Params
     */
    @Override
    public Params put(String key, Object value) {
        if (!TextUtils.isEmpty(key) && value != null && value.toString().length() > 0) {
            super.put(key, String.valueOf(value));
        }
        return this;
    }

    @Override
    public String optString(String key) {
        return optString(key, null);
    }

    @Override
    public String optString(String key, String defValue) {
        if (TextUtils.isEmpty(key)) {
            return defValue;
        }
        Object obj = get(key);
        if (obj != null) {
            return (String) obj;
        }
        return defValue;
    }

    @Override
    public int optInt(String key) {
        return optInt(key, 0);
    }

    @Override
    public int optInt(String key, int defValue) {
        if (TextUtils.isEmpty(key)) {
            return defValue;
        }
        Object obj = get(key);
        if (obj != null) {
            return Integer.valueOf(obj.toString());
        }
        return defValue;
    }

    @Override
    public String getUrlParams() {
        return getUrlParams(false);
    }

    @Override
    public String getUrlParams(boolean removeLastStr) {
        if (isEmpty()) {
            return null;
        }
        StringBuilder paramsBuilder = new StringBuilder();
        Iterator<String> paramsIterator = keySet().iterator();
        String key;
        while (paramsIterator.hasNext()) {
            key = paramsIterator.next();
            if (!TextUtils.isEmpty(key)) {
                paramsBuilder.append(key).append("=").append(get(key)).append("&");
            }
        }
        if (removeLastStr) {
            paramsBuilder.delete(paramsBuilder.length() - 1, paramsBuilder.length());
        }
        return paramsBuilder.toString();
    }

    @Override
    public String toJSONString() {
        return toJSONObject().toString();
    }

    @Override
    public JSONObject toJSONObject() {
        return new JSONObject(this);
    }

    /**
     * 为了方便，客户端使用OBJECT作为Params的Value的泛型，再给第三方传时，再转换成 Map<String, String>
     * @param params
     * @return
     */
    public static HashMap<String, String> toHashMap(Params params){
        HashMap<String, String> maps = new HashMap<>();
        if (params != null) {
            for (String key : params.keySet()) {
                Object value = params.get(key);
                maps.put(key, String.valueOf(value));
            }
        }
        return maps;
    }
}
