package com.gulimall.common.core.web;

import com.gulimall.common.core.constant.Constants;
import com.baomidou.mybatisplus.core.metadata.IPage;


import java.util.List;

/**
 * Controller基类
 *
 * @author EleAdmin
 * @since 2017-06-10 10:10:19
 */
public class BaseController {
    /**
     * 返回成功
     *
     * @return ApiResult
     */
    public ApiResult<?> success() {
        return new ApiResult<>(Constants.RESULT_OK_CODE, Constants.RESULT_OK_MSG);
    }

    /**
     * 返回成功
     *
     * @param message 状态信息
     * @return ApiResult
     */
    public ApiResult<?> success(String message) {
        return success().setMessage(message);
    }

    /**
     * 返回成功
     *
     * @param data 返回数据
     * @return ApiResult
     */
    public <T> ApiResult<T> success(T data) {
        return new ApiResult<>(Constants.RESULT_OK_CODE, Constants.RESULT_OK_MSG, data);
    }

    /**
     * 返回成功
     *
     * @param message 状态信息
     * @return ApiResult
     */
    public <T> ApiResult<T> success(String message, T data) {
        return success(data).setMessage(message);
    }

    /**
     * 返回分页查询数据
     *
     * @param list  当前页数据
     * @param count 总数量
     * @return ApiResult
     */
    public <T> ApiResult<PageResult<T>> success(List<T> list, Long count) {
        return success(new PageResult<>(list, count));
    }

    /**
     * 返回分页查询数据
     *
     * @param iPage IPage
     * @return ApiResult
     */
    public <T> ApiResult<PageResult<T>> success(IPage<T> iPage) {
        return success(iPage.getRecords(), iPage.getTotal());
    }

    /**
     * 返回失败
     *
     * @return ApiResult
     */
    public ApiResult<?> fail() {
        return new ApiResult<>(Constants.RESULT_ERROR_CODE, Constants.RESULT_ERROR_MSG);
    }

    /**
     * 返回失败
     *
     * @param message 状态信息
     * @return ApiResult
     */
    public ApiResult<?> fail(String message) {
        return fail().setMessage(message);
    }

    /**
     * 返回失败
     *
     * @param data 返回数据
     * @return ApiResult
     */
    public <T> ApiResult<T> fail(T data) {
        return fail(Constants.RESULT_ERROR_MSG, data);
    }

    /**
     * 返回失败
     *
     * @param message 状态信息
     * @param data    返回数据
     * @return ApiResult
     */
    public <T> ApiResult<T> fail(String message, T data) {
        return new ApiResult<>(Constants.RESULT_ERROR_CODE, message, data);
    }

}
