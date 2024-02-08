package com.jays.admin.common.system.result;

import com.jays.admin.common.system.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 登录返回结果
 *
 * @author EleAdmin
 * @since 2021-08-30 17:35:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "登录返回结果")
public class LoginResult implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "access_token")
    private String access_token;

    @Schema(description = "用户信息")
    private User user;

}
