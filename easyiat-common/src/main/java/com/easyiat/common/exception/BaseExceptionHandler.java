package com.easyiat.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * @description:
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/

@Slf4j
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public JSONResult bizExceptionHandler(HttpServletRequest req, BaseException e) {
        log.error("发生业务异常: ", e);
        return JSONResult.build(e.getCode(), e.getMessage());
    }

    /**
     * 处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常，详情继续往下看代码
     *
     * @param e 参数绑定异常
     * @return  DataResult
     */
    @ExceptionHandler({BindException.class})
    @ResponseBody
    public JSONResult bindExceptionHandler(HttpServletRequest req, BindException e) {
        log.error("参数绑定异常: ", e);
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return JSONResult.build(BaseMessageEnum.PARAM_VALID_ERROR.code(),message);
    }

    /**
     * 空指针异常
     *
     * @param e 空指针异常
     * @return  DataResult
     */
    @ExceptionHandler({NullPointerException.class})
    @ResponseBody
    public JSONResult nullPointExceptionHandler(HttpServletRequest req, NullPointerException e) {
        log.error("空指针异常: ", e);
        return JSONResult.build(BaseMessageEnum.INTERNAL_SERVER_ERROR);
    }

    /**
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
     *
     * @param e 入参校验未通过
     * @return  DataResult
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public JSONResult constraintViolationExceptionHandler(HttpServletRequest req, ConstraintViolationException e) {
        log.error("入参校验未通过: ", e);
        return JSONResult.build(BaseMessageEnum.PARAM_VALID_ERROR.code(),e.getMessage());
    }

    /**
     * 参数校验失败
     * @param req   HttpServletRequest
     * @param e MethodArgumentNotValidException
     * @return  MsgCodeResult
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public JSONResult methodArgumentNotValidExceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e){
        log.error("参数校验失败");
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return JSONResult.build(BaseMessageEnum.PARAM_VALID_ERROR.code(),message);
    }


    /**
     *
     * @param req   JSON解析异常
     * @param e JsonMappingException
     * @return  MsgCodeResult
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public JSONResult jsonMappingExceptionHandler(HttpServletRequest req, HttpMessageNotReadableException e){
        log.error("参数反序列化失败");
        return JSONResult.build(BaseMessageEnum.JSON_PARSE_ERROR);
    }


    /**
     * 请求方式错误
     * @param req   Http请求
     * @param e 异常
     * @return  DataResult
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public JSONResult httpRequestMethodNotSupportedExceptionHandler(HttpServletRequest req, Exception e){
        log.error("错误的请方式");
        return JSONResult.build(BaseMessageEnum.INTERNAL_SERVER_ERROR.code(),e.getMessage());
    }



    /**
     * 处理其他异常
     *
     * @param req   Http请求
     * @param e 异常
     * @return  DataResult
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JSONResult unknownExceptionHandler(HttpServletRequest req, Exception e) {
        log.error("未知异常: ", e);
        return JSONResult.build(BaseMessageEnum.INTERNAL_SERVER_ERROR);
    }
}
