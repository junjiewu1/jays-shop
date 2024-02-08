package com.jays.admin.common.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.List;

/**
 * 菜单
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:17
 */
@Data
@Schema(description = "菜单")
@TableName("sys_menu")
public class Menu implements GrantedAuthority {
    private static final long serialVersionUID = 1L;
    public static final int TYPE_MENU = 0;  // 菜单类型
    public static final int TYPE_BTN = 1;  // 按钮类型

    @Schema(description = "菜单id")
    @TableId(type = IdType.AUTO)
    private Integer menuId;

    @Schema(description = "上级id, 0是顶级")
    private Integer parentId;

    @Schema(description = "菜单名称")
    private String title;

    @Schema(description = "菜单路由地址")
    private String path;

    @Schema(description = "菜单组件地址")
    private String component;

    @Schema(description = "菜单类型, 0菜单, 1按钮")
    private Integer menuType;

    @Schema(description = "排序号")
    private Integer sortNumber;

    @Schema(description = "权限标识")
    private String authority;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "是否隐藏, 0否, 1是(仅注册路由不显示左侧菜单)")
    private Integer hide;

    @Schema(description = "路由元信息")
    private String meta;

    @Schema(description = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @Schema(description = "租户id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

    @Schema(description = "子菜单")
    @TableField(exist = false)
    private List<Menu> children;

    @Schema(description = "角色权限树选中状态")
    @TableField(exist = false)
    private Boolean checked;

}
