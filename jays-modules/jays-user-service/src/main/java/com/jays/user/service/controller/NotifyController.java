package com.jays.user.service.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.crypto.digest.DigestUtil;
import com.jays.common.core.annotation.OperationLog;
import com.jays.common.core.web.ApiResult;
import com.jays.common.redis.service.RedisService;
import com.jays.common.core.utils.CommonUtil;
import com.jays.common.core.web.BaseController;
import com.jays.user.service.entity.EmailRecord;
import com.jays.user.service.service.EmailRecordService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author junjie
 * @since 2024-02-06
 */
@Controller
@RequestMapping("/api/notify")
public class NotifyController extends BaseController {

    @Autowired
    RedisService redisService;
    @Resource
    private EmailRecordService emailRecordService;
    //图形验证码有效期十分钟
    private static final long CAPTCHA_CODE_EXPIRED = 60 * 1000 * 10;
    @GetMapping("/captcha")
    @Operation(summary ="图形验证码")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        redisService.setCacheObject(getCaptchaKey(request),lineCaptcha.getCode(), CAPTCHA_CODE_EXPIRED, TimeUnit.MILLISECONDS);
        lineCaptcha.write(response.getOutputStream());

    }

    @OperationLog
    @Operation(summary ="发送邮件")
    @PostMapping("/sendCode")
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

    /**
     * 获取验证码缓存key
     * @param request
     * @return
     */
    private String getCaptchaKey(HttpServletRequest request){
        String clientIP = CommonUtil.getClientIP(request);
        String key = "user-service:captcha:"+ DigestUtil.md5Hex(clientIP+request.getHeader("User-Agent"));
        System.out.println(key);
        return key;
    }
}

