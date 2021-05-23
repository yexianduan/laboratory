/**
 * @file: ResponsePageResult.java
 * @author: yolanda
 * @date: 2021/4/6 17:04
 */

package com.yexianduan.laboratory.common.util;

import java.io.Serializable;

/**
 * @author yolanda
 * @version 1.0
 * @className ResponsePageResult
 * @date 2021/4/6  17:04
 * @see
 * @since
 */
public class ResponsePageResult<T> implements Serializable {
    private static final long serialVersionUID = -1802122468331526708L;
    private int status = -1;
    private String message = "";
    private Long pageSize;
    private Long pageCount;
    private Long total;

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    private Long current;






    private T t;

    @Override
    public String toString() {
        return "ResponseResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", t=" + t +
                '}';
    }

    public ResponsePageResult() {
    }

    public ResponsePageResult(int status, String message) {
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
