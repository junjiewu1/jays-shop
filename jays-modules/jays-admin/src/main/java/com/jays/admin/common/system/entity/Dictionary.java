package com.jays.admin.common.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.util.Date;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 字典
 *
 * @author EleAdmin
 * @since 2020-03-14 11:29:03
 */
@Data
@Schema(description = "字典")
@TableName("sys_dictionary")
public class Dictionary implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "字典id")
    @TableId(type = IdType.AUTO)
    private Integer dictId;

    @Schema(description = "字典标识")
    private String dictCode;

    @Schema(description = "字典名称")
    private String dictName;

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

}
