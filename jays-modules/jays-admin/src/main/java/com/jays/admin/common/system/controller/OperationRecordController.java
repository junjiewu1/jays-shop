package com.jays.admin.common.system.controller;

import com.jays.common.core.annotation.OperationLog;
import com.jays.common.core.web.ApiResult;
import com.jays.common.core.web.BaseController;
import com.jays.common.core.web.PageResult;
import com.jays.admin.common.system.entity.OperationRecord;
import com.jays.admin.common.system.param.OperationRecordParam;
import com.jays.admin.common.system.service.OperationRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 操作日志控制器
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:12
 */
@Tag(name = "操作日志")
@RestController
@RequestMapping("/system/operation-record")
public class OperationRecordController extends BaseController {
    @Resource
    private OperationRecordService operationRecordService;

    /**
     * 分页查询操作日志
     */
    @PreAuthorize("hasAuthority('sys:operation-record:list')")
    @Operation(summary ="分页查询操作日志")
    @GetMapping("/page")
    public ApiResult<PageResult<OperationRecord>> page(OperationRecordParam param) {
        return success(operationRecordService.pageRel(param));
    }

    /**
     * 查询全部操作日志
     */
    @PreAuthorize("hasAuthority('sys:operation-record:list')")
    @OperationLog
    @Operation(summary ="查询全部操作日志")
    @GetMapping()
    public ApiResult<List<OperationRecord>> list(OperationRecordParam param) {
        return success(operationRecordService.listRel(param));
    }

    /**
     * 根据id查询操作日志
     */
    @PreAuthorize("hasAuthority('sys:operation-record:list')")
    @Operation(summary ="根据id查询操作日志")
    @GetMapping("/{id}")
    public ApiResult<OperationRecord> get(@PathVariable("id") Integer id) {
        return success(operationRecordService.getByIdRel(id));
    }

}
