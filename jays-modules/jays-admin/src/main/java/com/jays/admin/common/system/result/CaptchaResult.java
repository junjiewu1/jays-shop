package com.jays.admin.common.system.result;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 验证码返回结果
 *
 * @author EleAdmin
 * @since 2021-08-30 17:35:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "验证码返回结果")
public class CaptchaResult implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "图形验证码base64数据")
    private String base64;

    @Schema(description = "验证码文本")
    private String text;

}
