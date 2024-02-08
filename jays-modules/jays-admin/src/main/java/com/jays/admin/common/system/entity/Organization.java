package com.jays.admin.common.system.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 组织机构
 *
 * @author EleAdmin
 * @since 2020-03-14 11:29:04
 */
@Data
@Schema(description = "组织机构")
@TableName("sys_organization")
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "机构id")
    @TableId(type = IdType.AUTO)
    private Integer organizationId;

    @Schema(description = "上级id, 0是顶级")
    private Integer parentId;

    @Schema(description = "机构名称")
    private String organizationName;

    @Schema(description = "机构全称")
    private String organizationFullName;

    @Schema(description = "机构代码")
    private String organizationCode;

    @Schema(description = "机构类型, 字典标识")
    private String organizationType;

    @Schema(description = "负责人id")
    private Integer leaderId;

    @Schema(description = "排序号")
    private Integer sortNumber;

    @Schema(description = "备注")
    private String comments;

    @Schema(description = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @Schema(description = "租户id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

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
