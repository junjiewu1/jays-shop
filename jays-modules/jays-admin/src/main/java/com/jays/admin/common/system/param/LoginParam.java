package com.jays.admin.common.system.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 登录参数
 *
 * @author EleAdmin
 * @since 2021-08-30 17:35:16
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "登录参数")
public class LoginParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "账号")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "租户id")
    private Integer tenantId;

}
