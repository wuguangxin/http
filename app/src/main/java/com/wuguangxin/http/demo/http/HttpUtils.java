package com.wuguangxin.http.demo.http;

import android.util.Log;

import com.wuguangxin.http.HttpClient;
import com.wuguangxin.http.Params;
import com.wuguangxin.http.business.ResponseHandler;
import com.wuguangxin.http.demo.URL;
import com.wuguangxin.http.demo.utils.MD5;

import java.io.File;

import static android.os.Build.VERSION_CODES.BASE;

/**
 * 封装与网络交互的请求
 *
 * @author wuguangxin
 * @date: 2014-8-17 下午2:22:41
 */
public class HttpUtils{
	private static final String TAG = "HttpUtils";
	private static final int CONN_TIMEOUT = 60000;
	private static final int SO_TIMEOUT = 10000;
	private static HttpClient httpClient = HttpClient.getInstance();
	private static final ResponseHandler DEF_HANDLER = new ResponseHandler();

	static {
		httpClient.setConnTimeout(CONN_TIMEOUT);
		httpClient.setSoTimeout(SO_TIMEOUT);
		httpClient.setUrlEncoding("UTF-8");
	}
	
	/**
	 * 发送GET请求 
	 * @param params 请求的参数集合
	 * @param path 接口名
	 */
	public static final void get(Params params, String path){
		httpClient.get(getUrl(params, path), DEF_HANDLER);
	}
	
	/**
	 * 发送GET请求
	 * @param params 请求的参数集合
	 * @param path 接口名
	 * @param responseHandler 回调函数
	 */
	public static final void get(Params params, String path, ResponseHandler responseHandler){
		httpClient.get(getUrl(params, path), responseHandler);
	}

	/**
	 * 发送GET请求
	 * @param url 地址
	 * @param responseHandler 回调函数
	 */
	public static final void get(String url, ResponseHandler responseHandler){
		httpClient.get(url, responseHandler);
	}

	/**
	 * 发送POST请求
	 * @param params 请求的参数集合
	 * @param path 接口名
	 */
	public static final void post(Params params, String path){
		String url = URL.Base + path;
		Log.i(TAG, "POST " + url);
		httpClient.post(url, params, DEF_HANDLER);
	}

	/**
	 * 发送POST请求
	 * @param params 请求的参数集合
	 * @param path 接口名
	 * @param responseHandler 回调函数
	 */
	public static final void post(Params params, String path, ResponseHandler responseHandler){
		String url = BASE + path;
		Log.i(TAG, "POST " + url);
		httpClient.post(url, params, responseHandler);
	}

	/**
	 * 根据参数获取URL
	 * @param params 请求的参数集合
	 * @param path 接口名
	 */
	public static final String getUrl(Params params, String path){
		StringBuilder urlBuilder = new StringBuilder(URL.Base).append(path);
		if(params != null && !params.isEmpty()){
			urlBuilder.append("?").append(params.getUrlParams()).append("sign=").append(getSign(params));
		}
		Log.i(TAG, urlBuilder.toString());
		return urlBuilder.toString();
	}

	private static String getSign(Params params) {
		String sign = null;
		if(params != null){
			sign = MD5.encode(params.getUrlParams(true));
		}
		return sign;
	}

	/**
	 * 上传文件
	 * @param url 服务器地址
	 * @param file 文件
	 * @param responseHandler 回调
	 */
	public static final void upload(String url, File file, ResponseHandler responseHandler){
		upload(url, file, null, responseHandler);
	}
	
	/**
	 * 上传文件 
	 * @param url 服务器地址
	 * @param file 文件
	 * @param params 参数
	 * @param responseHandler 回调
	 */
	public static final void upload(String url, File file, Params params, ResponseHandler responseHandler){
		httpClient.upload(url, file, params, responseHandler);
	}
	
	/**
	 * 下载文件，使用URLConnection (实用于本服务器下载)
	 * @param url 服务器地址
	 * @param savePath 文件
	 * @param responseHandler 下载回调
	 * @return
	 */
	public static void download(String url, File savePath, ResponseHandler responseHandler){
		httpClient.download(url, savePath, responseHandler);
	}
}
