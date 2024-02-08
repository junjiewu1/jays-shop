package com.jays.admin.common.system.param;

import com.jays.common.core.annotation.QueryField;
import com.jays.common.core.annotation.QueryType;
import com.jays.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色查询参数
 *
 * @author EleAdmin
 * @since 2021-08-29 20:35:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "角色查询参数")
public class RoleParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @Schema(description = "角色id")
    @QueryField(type = QueryType.EQ)
    private Integer roleId;

    @Schema(description = "角色标识")
    private String roleCode;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "备注")
    private String comments;

}
