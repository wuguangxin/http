package com.wuguangxin.http.okhttp;

import com.wuguangxin.http.Params;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * OkHttp工具类。(目前暂时使用zhy封装的OkHttpUtils)。
 *
 * Created by wuguangxin on 17/6/20.
 */
public final class OkHttpUtils {
	private static final String TAG = "OkHttpUtils";

	private static final int DEF_CONN_TIMEOUT = 30000;
	private static final int DEF_SO_TIMEOUT = 30000;

	private static int CONN_TIMEOUT = DEF_CONN_TIMEOUT;
	private static int SO_TIMEOUT = DEF_SO_TIMEOUT;

	public static void setConnTimeout(int connTimeout) {
		OkHttpUtils.CONN_TIMEOUT = connTimeout;
	}

	public static void setSoTimeout(int soTimeout) {
		OkHttpUtils.SO_TIMEOUT = soTimeout;
	}

	private static Map<String, String> headers;

	/**
	 * 设置请求头信息
	 * @param headers
	 */
	public static void setHeader(Map<String, String> headers) {
		OkHttpUtils.headers = headers;
	}

	/**
	 * 发送GET请求
	 * @param params 请求的参数集合
	 * @param url 地址
	 * @param callback 回调
	 */
	public static final void get(Params params, String url, Callback callback){
		com.zhy.http.okhttp.OkHttpUtils
				.get()
				.url(url)
				.headers(headers)
				.params(getMaps(params))
				.build()
				.connTimeOut(CONN_TIMEOUT)
				.readTimeOut(SO_TIMEOUT)
				.execute(callback);
	}

	/**
	 * 发送POST请求
	 * @param params 请求的参数集合
	 * @param url 地址
	 * @param callback 回调
	 */
	public static final void post(Params params, String url, Callback callback){
		com.zhy.http.okhttp.OkHttpUtils
				.post()
				.url(url)
				.headers(headers)
				.params(getMaps(params))
				.build()
				.connTimeOut(CONN_TIMEOUT)
				.readTimeOut(SO_TIMEOUT)
				.execute(callback);
	}

	/**
	 * 发送POST上传文件请求
	 * @param params
	 * @param url
	 * @param name 文件对应的key
	 * @param fileName 文件名
	 * @param file 文件
	 * @param callback 回调
	 */
	public static final void postFile(Params params, String url, String name, String fileName, File file, Callback callback){
		com.zhy.http.okhttp.OkHttpUtils
				.post()
				.url(url)
				.headers(headers)
				.params(getMaps(params))
				.addFile(name, fileName, file)
				.build()
				.connTimeOut(CONN_TIMEOUT)
				.readTimeOut(SO_TIMEOUT)
				.execute(callback);
	}

	/**
	 * 上传文件（POST方式）
	 * @param url 接口地址
	 * @param name
	 * @param fileName
	 * @param file
	 * @param callBack
	 */
	public static final void upload(String url, String name, String fileName, File file, FileCallBack callBack){
		com.zhy.http.okhttp.OkHttpUtils//
				.post()
				.url(url)
				.addFile(name, fileName, file)
				.headers(headers)
				.build()
				.execute(callBack);
	}

	/**
	 * 下载文件（GET方式）
	 * @param url 地址
	 * @param callBack 回调
	 */
	public static final void download(String url, final FileCallBack callBack){
		com.zhy.http.okhttp.OkHttpUtils//
				.get()
				.url(url)
				.headers(headers)
				.build()
				.execute(callBack);
	}

	/**
	 * POST方式下载文件
	 * @param url 地址
	 * @param callBack 回调
	 */
	public static final void downloadByPost(String url, final FileCallBack callBack){
		com.zhy.http.okhttp.OkHttpUtils//
				.post()
				.url(url)
				.headers(headers)
				.build()
				.execute(callBack);
	}

	/**
	 * 为了方便，客户端使用OBJECT作为Params的Value的泛型，再给第三方传时，再转换成 Map<String, String>
	 * @param params
	 * @return
	 */
	private static HashMap<String, String> getMaps(Params params){
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
