package com.jays.admin.common.system.param;

import com.jays.common.core.annotation.QueryField;
import com.jays.common.core.annotation.QueryType;
import com.jays.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典查询参数
 *
 * @author EleAdmin
 * @since 2021-08-28 22:12:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "字典查询参数")
public class DictionaryParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @QueryField(type = QueryType.EQ)
    @Schema(description = "字典id")
    private Integer dictId;

    @Schema(description = "字典标识")
    private String dictCode;

    @Schema(description = "字典名称")
    private String dictName;

    @Schema(description = "备注")
    private String comments;

}
