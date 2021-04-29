package com.easyiat.common.exception;


/**
 * @description: 全局异常处理类
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected Integer code;
    /**
     * 错误信息
     */
    protected String message;

    public BaseException() {
        super();
    }

    public BaseException(BaseResult baseError) {
        super(String.valueOf(baseError.code()));
        this.code = baseError.code();
        this.message = baseError.message();
    }

    public BaseException(BaseResult baseError, Throwable cause) {
        super(String.valueOf(baseError.code()), cause);
        this.code = baseError.code();
        this.message = baseError.message();
    }

    public BaseException(String errorMsg) {
        super(String.valueOf(errorMsg));
        this.message = errorMsg;
    }



    public BaseException(Integer errorCode, String errorMsg) {
        super(String.valueOf(errorCode));
        this.code = errorCode;
        this.message = errorMsg;
    }
    public BaseException(BaseMessageEnum baseCodeMessage) {
        super(String.valueOf(baseCodeMessage.code()));
        this.code = baseCodeMessage.code();
        this.message = baseCodeMessage.message();
    }

    public BaseException(BaseMessageEnum baseCodeMessage, Throwable cause) {
        super(String.valueOf(baseCodeMessage.code()), cause);
        this.code = baseCodeMessage.code();
        this.message = baseCodeMessage.message();
    }

    public BaseException(Integer errorCode, String errorMsg, Throwable cause) {
        super(String.valueOf(errorCode), cause);
        this.code = errorCode;
        this.message = errorMsg;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
