package com.jays.admin.common.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 邮件发送记录
 *
 * @author EleAdmin
 * @since 2021-08-29 12:36:35
 */
@Data
@Schema(description = "邮件发送记录")
@TableName("sys_email_record")
public class EmailRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @Schema(description = "邮件标题")
    private String title;

    @Schema(description = "邮件内容")
    private String content;

    @Schema(description = "收件邮箱")
    private String receiver;

    @Schema(description = "发件邮箱")
    private String sender;

    @Schema(description = "创建人")
    private Integer createUserId;

    @Schema(description = "备注")
    private String comments;

    @Schema(description = "租户id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

}
