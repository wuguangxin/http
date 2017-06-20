package com.wuguangxin.http;

/**
 * Http异步处理状态。
 *
 * Created by wuguangxin on 14/10/13
 */
public interface HttpState{
	/**
	 * 请求开始
	 */
	int START = 100;
	
	/**
	 * 文件大小
	 */
	int COUNT = 110;
	
	/**
	 * 下载进度
	 */
	int PROGRESS = 111;
	
	/**
	 * 请求成功
	 */
	int SUCCESS = 200;
	
	/**
	 * 请求取消
	 */
	int CANCEL = 300;
	
	/**
	 * 请求失败
	 */
	int FAILURE = 400;
	
	/**
	 * 请求完成
	 */
	int FINISH = 900;
}
