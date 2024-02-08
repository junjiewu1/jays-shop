package com.jays.user.service.controller;
import com.jays.common.core.annotation.OperationLog;
import com.jays.common.core.web.ApiResult;
import com.jays.common.core.web.BaseController;
import com.jays.user.service.entity.EmailRecord;
import com.jays.user.service.service.EmailRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

;

/**
 * 邮件功能控制器
 *
 * @author EleAdmin
 * @since 2020-03-21 00:37:11
 */
@Tag(name = "邮件功能")
@RestController
@RequestMapping("/api/email")
public class EmailController extends BaseController {
    @Resource
    private EmailRecordService emailRecordService;

    @OperationLog
    @Operation(summary ="发送邮件")
    @PostMapping()
    public ApiResult<?> send(@RequestBody EmailRecord emailRecord) {
        try {
            emailRecordService.sendFullTextEmail(emailRecord.getTitle(), emailRecord.getContent(),
                    emailRecord.getReceiver().split(","));
            return success("发送成功");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return fail("发送失败");
    }

}
