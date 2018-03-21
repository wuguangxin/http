package com.wuguangxin.http;

import org.json.JSONObject;

/**
 * Params的接口
 * Created by wuguangxin on 14/10/13
 */
public interface IParams {

    /**
     * 添加数据
     * @param key
     * @param value
     */
    Params put(String key, Object value);

    /**
     * 获取value
     * @param key key
     * @return value
     */
    String optString(String key);

    /**
     * 获取 String value
     * @param key key
     * @param defValue 默认 String value
     * @return String value
     */
    String optString(String key, String defValue);

    /**
     * 获取 int value
     * @param key key
     * @return int value
     */
    int optInt(String key);

    /**
     * 获取int value
     * @param key key
     * @param defValue 默认 int value
     * @return int value
     */
    int optInt(String key, int defValue);

    /**
     * 获取URL参数串（默认保留最后的&）
     * @return URL参数串
     */
    String getUrlParams();

    /**
     * 获取URL参数串（去除最后的&）
     * @param removeLastStr 是否删除最后一个“&”符号
     * @return URL参数串
     */
    String getUrlParams(boolean removeLastStr);

    /**
     * 返回Json格式的字符串，并且按A~Z进行排序；key和value必须是String类型
     * @return Json字符串
     */
    String toJSONString();

    /**
     * 返回Json对象JSONObject，并且按A~Z进行排序；key和value必须是String类型
     * @return JSONObject
     */
    JSONObject toJSONObject();
}