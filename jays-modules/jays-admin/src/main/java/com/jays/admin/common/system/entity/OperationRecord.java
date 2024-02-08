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
 * 操作日志
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:33
 */
@Data
@Schema(description = "操作日志")
@TableName("sys_operation_record")
public class OperationRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "操作模块")
    private String module;

    @Schema(description = "操作功能")
    private String description;

    @Schema(description = "请求地址")
    private String url;

    @Schema(description = "请求方式")
    private String requestMethod;

    @Schema(description = "调用方法")
    private String method;

    @Schema(description = "请求参数")
    private String params;

    @Schema(description = "返回结果")
    private String result;

    @Schema(description = "异常信息")
    private String error;

    @Schema(description = "备注")
    private String comments;

    @Schema(description = "消耗时间, 单位毫秒")
    private Long spendTime;

    @Schema(description = "操作系统")
    private String os;

    @Schema(description = "设备名称")
    private String device;

    @Schema(description = "浏览器类型")
    private String browser;

    @Schema(description = "ip地址")
    private String ip;

    @Schema(description = "状态, 0成功, 1异常")
    private Integer status;

    @Schema(description = "租户id")
    private Integer tenantId;

    @Schema(description = "操作时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

    @Schema(description = "用户昵称")
    @TableField(exist = false)
    private String nickname;

    @Schema(description = "用户账号")
    @TableField(exist = false)
    private String username;

}
