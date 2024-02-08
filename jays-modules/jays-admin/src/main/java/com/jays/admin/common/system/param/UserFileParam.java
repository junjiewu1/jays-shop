package com.jays.admin.common.system.param;

import com.jays.common.core.annotation.QueryField;
import com.jays.common.core.annotation.QueryType;
import com.jays.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户文件查询参数
 *
 * @author EleAdmin
 * @since 2022-07-21 14:34:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema( description = "用户文件查询参数")
public class UserFileParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @Schema(description = "用户id")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @Schema(description = "文件名称")
    private String name;

    @Schema(description = "上级id")
    @QueryField(type = QueryType.EQ)
    private Integer parentId;

}
