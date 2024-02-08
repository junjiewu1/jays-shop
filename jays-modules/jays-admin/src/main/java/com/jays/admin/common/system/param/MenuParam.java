package com.jays.admin.common.system.param;

import com.jays.common.core.annotation.QueryField;
import com.jays.common.core.annotation.QueryType;
import com.jays.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单查询参数
 *
 * @author EleAdmin
 * @since 2021-08-29 19:36:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "菜单查询参数")
public class MenuParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @Schema(description = "菜单id")
    @QueryField(type = QueryType.EQ)
    private Integer menuId;

    @Schema(description = "上级id, 0是顶级")
    @QueryField(type = QueryType.EQ)
    private Integer parentId;

    @Schema(description = "菜单名称")
    private String title;

    @Schema(description = "菜单路由关键字")
    private String path;

    @Schema(description = "菜单组件地址")
    private String component;

    @Schema(description = "菜单类型, 0菜单, 1按钮")
    @QueryField(type = QueryType.EQ)
    private Integer menuType;

    @Schema(description = "权限标识")
    private String authority;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "是否隐藏, 0否, 1是(仅注册路由不显示左侧菜单)")
    @QueryField(type = QueryType.EQ)
    private Integer hide;

}
