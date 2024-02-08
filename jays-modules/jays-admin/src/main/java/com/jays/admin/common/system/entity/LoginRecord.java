package com.jays.admin.common.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录日志
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:41
 */
@Data
@Schema(description = "登录日志")
@TableName("sys_login_record")
public class LoginRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int TYPE_LOGIN = 0;  // 登录成功
    public static final int TYPE_ERROR = 1;  // 登录失败
    public static final int TYPE_LOGOUT = 2;  // 退出登录
    public static final int TYPE_REFRESH = 3;  // 续签token

    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @Schema(description = "用户账号")
    private String username;

    @Schema(description = "操作系统")
    private String os;

    @Schema(description = "设备名称")
    private String device;

    @Schema(description = "浏览器类型")
    private String browser;

    @Schema(description = "ip地址")
    private String ip;

    @Schema(description = "操作类型, 0登录成功, 1登录失败, 2退出登录, 3续签token")
    private Integer loginType;

    @Schema(description = "备注")
    private String comments;

    @Schema(description = "租户id")
    private Integer tenantId;

    @Schema(description = "操作时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

    @Schema(description = "用户id")
    @TableField(exist = false)
    private Integer userId;

    @Schema(description = "用户昵称")
    @TableField(exist = false)
    private String nickname;

}
