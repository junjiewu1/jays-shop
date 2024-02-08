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
 * 操作日志参数
 *
 * @author EleAdmin
 * @since 2021-08-29 20:35:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "操作日志参数")
public class OperationRecordParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @Schema(description = "用户id")
    @QueryField(type = QueryType.EQ)
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

    @Schema(description = "ip地址")
    private String ip;

    @Schema(description = "备注")
    private String comments;

    @Schema(description = "状态, 0成功, 1异常")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @Schema(description = "用户账号")
    @TableField(exist = false)
    private String username;

    @Schema(description = "用户昵称")
    @TableField(exist = false)
    private String nickname;

}
