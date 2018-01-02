package com.wuguangxin.http.okhttp;

import com.wuguangxin.http.Params;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.FileCallBack;

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
				.params(params)
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
				.params(params)
				.build()
				.connTimeOut(CONN_TIMEOUT)
				.readTimeOut(SO_TIMEOUT)
				.execute(callback);
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
}
