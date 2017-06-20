package com.wuguangxin.http;

/**
 * Http异常类。
 *
 * Created by wuguangxin on 14/10/13
 */
public interface HttpError {
	/**
	 * 默认异常
	 */
	String DEFAULT_EXCEPTION 			= "网络异常";

	/**
	 * 未知主机异常
	 */
	String UNKNOWN_HOST_EXCEPTION 		= "网络异常[001]";

	/**
	 * 无妨访问HOST
	 */
	String HTTPHOST_CONNECT_EXCEPTION 	= "网络异常[002]";

	/**
	 * URL编码异常
	 */
	String URL_ENCODING_EXCEPTION 		= "网络异常[003]";

	/**
	 * 服务器请求超时
	 */
	String CONNECT_TIMEOUT_EXCEPTION 	= "网络异常[004]";

	/**
	 * 服务器响应超时
	 */
	String SOCKET_TIMEOUT_EXCEPTION 	= "网络异常[005]";


	// ================================================================
	/**
	 * 数据解析异常
	 */
	String DATA_PARSER_ERROR 			= "网络异常[M01]";

	/**
	 * 参数丢失异常
	 */
	String PARAMS_LOSE_ERROR 			= "网络异常[M02]";

	/**
	 * 发生地址重定向
	 */
	String ADDRESS_REDIRECTS_EXCEPTION 	= "网络异常[M03]";

	/**
	 * 服务器处理异常
	 */
	String SERVER_HANDLE_EXCEPTION 		= "网络异常[M04]";
}
