package com.jays.admin.common.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 租户
 *
 * @author EleAdmin
 * @since 2021-08-28 11:31:06
 */
@Data
@Schema(description = "租户")
@TableName("sys_tenant")
public class Tenant implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "租户id")
    @TableId(type = IdType.AUTO)
    private Integer tenantId;

    @Schema(description = "租户名称")
    private String tenantName;

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
