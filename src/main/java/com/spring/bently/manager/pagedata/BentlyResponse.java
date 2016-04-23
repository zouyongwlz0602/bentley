/*
 * Create Author  : yong.zou
 * Create Date    : 2015-07-16
 * Project        : sales-portal-server
 * File Name      : SalesPortalResponse.java
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package com.spring.bently.manager.pagedata;

import java.io.Serializable;

/**
 * 功能描述:  <p>
 *
 * @author : yong.zou <p>
 * @version 1.0 2015-07-16
 * @since sales-portal-server 1.0
 */
public class BentlyResponse<T> implements Serializable{

    public static final int SUCCESS = 200;
    public static final int ILLEGAL_PARAM = 400;
    public static final int SERVER_ERROR = 500;

    private static final long serialVersionUID = 1L;
    private int code;
    private boolean success;
    private String msg;
    private T data;

    public BentlyResponse() {
    }

    public BentlyResponse(int code, String msg, T data) {
        this.code = code;
        this.success = code == SUCCESS;
        this.msg = msg;
        this.data = data;
    }

    public static <T> BentlyResponse<T> success(T data) {
        BentlyResponse<T> response = new BentlyResponse<T>(SUCCESS, "", data);
        response.setSuccess(true);
        return response;
    }

    @SuppressWarnings("unchecked")
    public static BentlyResponse fail(String msg) {
        BentlyResponse response = new BentlyResponse(SERVER_ERROR, msg, null);
        response.setSuccess(false);
        return response;
    }

    @Override
    public String toString()
    {
        return "JsonpData{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", code=" + code +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {

        if (this == o)
        {
            return true;
        }
        if (!(o instanceof BentlyResponse))
        {
            return false;
        }
        BentlyResponse jsonpData = (BentlyResponse) o;
        if (success != jsonpData.success)
        {
            return false;
        }
        if (msg != null ? !msg.equals(jsonpData.msg) : jsonpData.msg != null)
        {
            return false;
        }
        if (data != null ? !data.equals(jsonpData.data) : jsonpData.data != null)
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int result1 = (success ? 1 : 0);
        result1 = 31 * result1 + (msg != null ? msg.hashCode() : 0);
        result1 = 31 * result1 + (data != null ? data.hashCode() : 0);
        return result1;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
