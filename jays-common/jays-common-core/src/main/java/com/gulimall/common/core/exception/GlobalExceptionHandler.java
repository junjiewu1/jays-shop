package com.gulimall.common.core.exception;

import com.gulimall.common.core.constant.Constants;
import com.gulimall.common.core.utils.CommonUtil;
import com.gulimall.common.core.web.ApiResult;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局异常处理器
 *
 * @author EleAdmin
 * @since 2018-02-22 11:29:30
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResult<?> methodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e,
                                                           HttpServletResponse response) {
//        CommonUtil.addCrossHeaders(response);
        return new ApiResult<>(Constants.RESULT_ERROR_CODE, "请求方式不正确").setError(e.toString());
    }

//    @ResponseBody
//    @ExceptionHandler(AccessDeniedException.class)
//    public ApiResult<?> accessDeniedExceptionHandler(AccessDeniedException e, HttpServletResponse response) {
//        CommonUtil.addCrossHeaders(response);
//        return new ApiResult<>(Constants.UNAUTHORIZED_CODE, Constants.UNAUTHORIZED_MSG).setError(e.toString());
//    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ApiResult<?> businessExceptionHandler(BusinessException e, HttpServletResponse response) {
//        CommonUtil.addCrossHeaders(response);
        return new ApiResult<>(e.getCode(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public ApiResult<?> exceptionHandler(Throwable e, HttpServletResponse response) {
        logger.error(e.getMessage(), e);
//        CommonUtil.addCrossHeaders(response);
        return new ApiResult<>(Constants.RESULT_ERROR_CODE, Constants.RESULT_ERROR_MSG).setError(e.toString());
    }

}
