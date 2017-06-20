package com.wuguangxin.http;

/**
 * Http请求类型为GET
 * Created by wuguangxin on 17/6/20.
 */
public enum  HttpMethod {
    /**
     * 通过请求URI得到资源
     */
    GET,

    /**
     * 用于添加新的内容
     */
    POST,

    /**
     * 用于修改某个内容
     */
    PUT,

    /**
     * 删除某个内容
     */
    DELETE,

    /**
     * 用于代理进行传输，如使用SSL
     */
    CONNECT,

    /**
     * 询问可以执行哪些方法
     */
    OPTIONS,

    /**
     * 部分文档更改
     */
    PATCH
}
