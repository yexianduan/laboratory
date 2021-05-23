/**
 * @file: ResponseResult.java
 * @author: yolanda
 * @date: 2021/3/17 12:57
 */

package com.yexianduan.laboratory.common.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yolanda
 * @version 1.0
 * @className ResponseResult
 * @date 2021/3/17  12:57
 * @see
 * @since
 */
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = -1802122468331526708L;
    private int status = -1;
    private String message = "";
    private T t;

    @Override
    public String toString() {
        return "ResponseResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", t=" + t +
                '}';
    }

    public ResponseResult() {
    }

    public ResponseResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return t;
    }

    public void setData(T t){
        this.t=t;
    }
}
