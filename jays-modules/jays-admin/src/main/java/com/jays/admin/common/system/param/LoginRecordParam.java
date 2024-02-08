package com.jays.admin.common.system.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.jays.common.core.annotation.QueryField;
import com.jays.common.core.annotation.QueryType;
import com.jays.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 登录日志查询参数
 *
 * @author EleAdmin
 * @since 2021-08-29 19:09:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "登录日志查询参数")
public class LoginRecordParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @QueryField(type = QueryType.EQ)
    @Schema(description = "主键id")
    private Integer id;

    @Schema(description = "用户账号")
    private String username;

    @Schema(description = "操作系统")
    private String os;

    @Schema(description = "设备名")
    private String device;

    @Schema(description = "浏览器类型")
    private String browser;

    @Schema(description = "ip地址")
    private String ip;

    @QueryField(type = QueryType.EQ)
    @Schema(description = "操作类型, 0登录成功, 1登录失败, 2退出登录, 3续签token")
    private Integer loginType;

    @Schema(description = "备注")
    private String comments;

    @Schema(description = "用户id")
    @TableField(exist = false)
    private Integer userId;

    @Schema(description = "用户昵称")
    @TableField(exist = false)
    private String nickname;

}
