package com.cy.store.util;

import java.io.Serializable;

/**
 * 响应结果类
 *
 * @param <E>
 * @author xiaoke
 */
public class JsonResult<E> implements Serializable {
    /**
     * 状态码
     */
    private Integer state;

    /**
     * 状态描述信息
     */
    private String message;

    /**
     * 数据
     */
    private E data;

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Throwable e) {
        // 获取异常对象中的异常信息
        this.message = e.getMessage();
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
