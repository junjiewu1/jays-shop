package com.jays.admin.common.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:01
 */
@Data
@Schema(description = "角色")
@TableName("sys_role")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "角色id")
    @TableId(type = IdType.AUTO)
    private Integer roleId;

    @Schema(description = "角色标识")
    private String roleCode;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "备注")
    private String comments;

    @Schema(description = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @Schema(description = "租户id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

    @Schema(hidden = true)
    @TableField(exist = false)
    private Integer userId;

}
