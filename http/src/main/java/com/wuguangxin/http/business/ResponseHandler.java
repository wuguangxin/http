package com.wuguangxin.http.business;

import android.os.Message;

import com.wuguangxin.http.HttpError;
import com.wuguangxin.http.HttpState;

import java.io.File;

/**
 * 请求服务器时回调类
 *
 * <p>Created by wuguangxin on 14/12/29 </p>
 */
public class ResponseHandler extends BaseHandler{
	private boolean cancelled;
	
	@Override
	final public void handleMessage(Message msg){
		switch (msg.what) {
			case HttpState.START:
				onStart(md5((String)msg.obj));
				break;
			case HttpState.COUNT:
				try {
					onCountFileSize((Integer) msg.obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case HttpState.PROGRESS:
				try {
					long[] data = (long[]) msg.obj;
					if(data != null && data.length == 2){
						long size = data[0];
						long cur = data[1];
						if(cur < 1){
							cur = 1;
						}
						onProgress(size, size / cur * 1000l);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case HttpState.SUCCESS:
				if (msg.obj != null) {
					if (msg.obj instanceof File) {
						onSuccess((File) msg.obj);
					} else {
						String str = (String) msg.obj;
						onSuccess(str); // 可根据实际情况做处理
						onCountByteLength(str != null ? str.getBytes().length : 0);
					}
				} else {
					onFailure(HttpError.DATA_PARSER_ERROR);
					onCountByteLength(0);
				}
				break;
			case HttpState.CANCEL:
				onCancel();
				break;
			case HttpState.FAILURE:
				handlerFailure(msg);
				break;
			case HttpState.FINISH:
				onFinish();
				break;
		}
	}

	/**
	 * 处理失败状态
	 * @param msg 失败消息
	 */
	private void handlerFailure(Message msg){
		String errMsg = HttpError.UNKNOWN_HOST_EXCEPTION;
		Object obj = msg.obj;
		if (obj instanceof String) {
			errMsg = (String) obj;
		} else if (obj instanceof Exception) {
			errMsg = HttpError.DEFAULT_EXCEPTION;
		}
		onFailure(errMsg);
	}
	
	/**
	 * 是否取消网络请求
	 * @return 是否取消
	 */
	public boolean isCancelled(){
		return cancelled;
	}

	/**
	 * 设置取消网络请求
	 * @param cancelled 是否取消
	 */
	public void setCancelled(boolean cancelled){
		this.cancelled = cancelled;
	}

	/**
	 * 加密数据算法
	 * @param text 明文
	 * @return 密文
	 */
	public String encode(String text){
		return text;
	}

	/**
	 * 实现解密数据算法
	 * @param text 密文
	 * @return 明文
	 */
	public String decode(String text){
		return text;
	}

	/**
	 * MD5加密
	 * @param text 文本
	 * @return MD5密文
	 */
	public String md5(String text){
		return text;
	}
}
