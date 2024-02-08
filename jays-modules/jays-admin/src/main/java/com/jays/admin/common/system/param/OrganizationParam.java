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
 * 组织机构查询参数
 *
 * @author EleAdmin
 * @since 2021-08-29 20:35:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "组织机构查询参数")
public class OrganizationParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @Schema(description = "机构id")
    @QueryField(type = QueryType.EQ)
    private Integer organizationId;

    @Schema(description = "上级id, 0是顶级")
    @QueryField(type = QueryType.EQ)
    private Integer parentId;

    @Schema(description = "机构名称")
    private String organizationName;

    @Schema(description = "机构全称")
    private String organizationFullName;

    @Schema(description = "机构代码")
    private String organizationCode;

    @Schema(description = "机构类型(字典代码)")
    private String organizationType;

    @Schema(description = "负责人id")
    @QueryField(type = QueryType.EQ)
    private Integer leaderId;

    @Schema(description = "备注")
    private String comments;

    @Schema(description = "机构类型名称")
    @TableField(exist = false)
    private String organizationTypeName;

    @Schema(description = "负责人姓名")
    @TableField(exist = false)
    private String leaderNickname;

    @Schema(description = "负责人账号")
    @TableField(exist = false)
    private String leaderUsername;

}
