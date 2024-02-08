package com.jays.admin.common.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件上传记录
 *
 * @author EleAdmin
 * @since 2021-08-29 12:36:32
 */
@Data
@Schema(description = "文件上传记录")
@TableName("sys_file_record")
public class FileRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @Schema(description = "文件名称")
    private String name;

    @Schema(description = "文件存储路径")
    private String path;

    @Schema(description = "文件大小")
    private Long length;

    @Schema(description = "文件类型")
    private String contentType;

    @Schema(description = "备注")
    private String comments;

    @Schema(description = "创建人")
    private Integer createUserId;

    @Schema(description = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @Schema(description = "租户id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

    @Schema(description = "文件访问地址")
    @TableField(exist = false)
    private String url;

    @Schema(description = "文件缩略图访问地址")
    @TableField(exist = false)
    private String thumbnail;

    @Schema(description = "文件下载地址")
    @TableField(exist = false)
    private String downloadUrl;

    @Schema(description = "创建人账号")
    @TableField(exist = false)
    private String createUsername;

    @Schema(description = "创建人名称")
    @TableField(exist = false)
    private String createNickname;

}
