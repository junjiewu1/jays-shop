package com.jays.admin.common.system.controller;

import com.jays.common.core.annotation.OperationLog;
import com.jays.common.core.web.ApiResult;
import com.jays.common.core.web.BaseController;
import com.jays.common.core.web.PageResult;
import com.jays.admin.common.system.entity.LoginRecord;
import com.jays.admin.common.system.param.LoginRecordParam;
import com.jays.admin.common.system.service.LoginRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 登录日志控制器
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:31
 */
@Tag(name = "登录日志")
@RestController
@RequestMapping("/system/login-record")
public class LoginRecordController extends BaseController {
    @Resource
    private LoginRecordService loginRecordService;

    @PreAuthorize("hasAuthority('sys:login-record:list')")
    @OperationLog
    @Operation(summary ="分页查询登录日志")
    @GetMapping("/page")
    public ApiResult<PageResult<LoginRecord>> page(LoginRecordParam param) {
        return success(loginRecordService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('sys:login-record:list')")
    @OperationLog
    @Operation(summary ="查询全部登录日志")
    @GetMapping()
    public ApiResult<List<LoginRecord>> list(LoginRecordParam param) {
        return success(loginRecordService.listRel(param));
    }

    @PreAuthorize("hasAuthority('sys:login-record:list')")
    @OperationLog
    @Operation(summary ="根据id查询登录日志")
    @GetMapping("/{id}")
    public ApiResult<LoginRecord> get(@PathVariable("id") Integer id) {
        return success(loginRecordService.getByIdRel(id));
    }

}
