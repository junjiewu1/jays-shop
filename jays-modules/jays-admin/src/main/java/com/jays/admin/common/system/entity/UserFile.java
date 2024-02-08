package com.jays.admin.common.system.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户文件
 *
 * @author EleAdmin
 * @since 2022-07-21 14:34:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema( description = "用户文件")
@TableName("sys_user_file")
public class UserFile implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "文件名称")
    private String name;

    @Schema(description = "是否是文件夹, 0否, 1是")
    private Integer isDirectory;

    @Schema(description = "上级id")
    private Integer parentId;

    @Schema(description = "文件路径")
    private String path;

    @Schema(description = "文件大小")
    private Integer length;

    @Schema(description = "文件类型")
    private String contentType;

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

}
