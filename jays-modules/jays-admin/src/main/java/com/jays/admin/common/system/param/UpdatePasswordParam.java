package com.jays.admin.common.system.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 修改密码参数
 *
 * @author EleAdmin
 * @since 2021-08-30 17:35:16
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "修改密码参数")
public class UpdatePasswordParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "原始密码")
    private String oldPassword;

    @Schema(description = "新密码")
    private String password;

}
