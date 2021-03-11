package com.ylkget.common.exception;

/**
 * <p>
 * 自定义异常
 * </p>
 *
 * @author joe 2021/3/11 10:19
 */
public class FastException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public FastException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public FastException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public FastException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public FastException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
