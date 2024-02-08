package com.jays.admin.common.system.controller;

import com.jays.admin.common.core.utils.SecurityUtils;
import com.jays.common.core.annotation.OperationLog;
import com.jays.common.core.web.ApiResult;
import com.jays.common.core.web.BaseController;
import com.jays.admin.common.system.entity.EmailRecord;
import com.jays.admin.common.system.service.EmailRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;

/**
 * 邮件功能控制器
 *
 * @author EleAdmin
 * @since 2020-03-21 00:37:11
 */
@Tag(name = "邮件功能")
@RestController
@RequestMapping("/system/email")
public class EmailController extends BaseController {
    @Resource
    private EmailRecordService emailRecordService;

    @PreAuthorize("hasAuthority('sys:email:send')")
    @OperationLog
    @Operation(summary ="发送邮件")
    @PostMapping()
    public ApiResult<?> send(@RequestBody EmailRecord emailRecord) {
        try {
            emailRecordService.sendFullTextEmail(emailRecord.getTitle(), emailRecord.getContent(),
                    emailRecord.getReceiver().split(","));
            emailRecord.setCreateUserId(SecurityUtils.getLoginUserId());
            emailRecordService.save(emailRecord);
            return success("发送成功");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return fail("发送失败");
    }

}
