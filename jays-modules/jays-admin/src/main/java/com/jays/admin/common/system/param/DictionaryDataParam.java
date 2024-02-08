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
 * 字典数据查询参数
 *
 * @author EleAdmin
 * @since 2021-08-28 22:12:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "字典数据查询参数")
public class DictionaryDataParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @Schema(description = "字典数据id")
    @QueryField(type = QueryType.EQ)
    private Integer dictDataId;

    @Schema(description = "字典id")
    @QueryField(type = QueryType.EQ)
    private Integer dictId;

    @Schema(description = "字典数据标识")
    private String dictDataCode;

    @Schema(description = "字典数据名称")
    private String dictDataName;

    @Schema(description = "备注")
    private String comments;

    @Schema(description = "字典代码")
    @TableField(exist = false)
    private String dictCode;

    @Schema(description = "字典名称")
    @TableField(exist = false)
    private String dictName;

    @Schema(description = "字典数据代码或字典数据名称")
    @TableField(exist = false)
    private String keywords;

}
