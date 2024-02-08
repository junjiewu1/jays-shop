package com.jays.admin.common.system.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 字典数据
 *
 * @author EleAdmin
 * @since 2020-03-14 11:29:04
 */
@Data
@Schema(description = "字典数据")
@TableName("sys_dictionary_data")
public class DictionaryData implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "字典数据id")
    @TableId(type = IdType.AUTO)
    private Integer dictDataId;

    @Schema(description = "字典id")
    private Integer dictId;

    @Schema(description = "字典数据标识")
    private String dictDataCode;

    @Schema(description = "字典数据名称")
    private String dictDataName;

    @Schema(description = "排序号")
    private Integer sortNumber;

    @Schema(description = "备注")
    private String comments;

    @Schema(description = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

    @Schema(description = "字典代码")
    @TableField(exist = false)
    private String dictCode;

    @Schema(description = "字典名称")
    @TableField(exist = false)
    private String dictName;

}
