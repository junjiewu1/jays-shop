package com.jays.admin.common.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色菜单
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:54
 */
@Data
@Schema(description = "角色权限")
@TableName("sys_role_menu")
public class RoleMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @Schema(description = "角色id")
    private Integer roleId;

    @Schema(description = "菜单id")
    private Integer menuId;

    @Schema(description = "租户id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

}
