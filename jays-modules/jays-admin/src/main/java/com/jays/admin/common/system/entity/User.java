package com.jays.admin.common.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

/**
 * 用户
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:13
 */
@Data
@Schema(description = "用户")
@TableName("sys_user")
public class User implements UserDetails {
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id")
    @TableId(type = IdType.AUTO)
    private Integer userId;

    @Schema(description = "账号")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "性别, 字典标识")
    private String sex;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "邮箱是否验证, 0否, 1是")
    private Integer emailVerified;

    @Schema(description = "真实姓名")
    private String realName;

    @Schema(description = "身份证号")
    private String idCard;

    @Schema(description = "出生日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @Schema(description = "个人简介")
    private String introduction;

    @Schema(description = "机构id")
    private Integer organizationId;

    @Schema(description = "状态, 0正常, 1冻结")
    private Integer status;

    @Schema(description = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @Schema(description = "租户id")
    private Integer tenantId;

    @Schema(description = "注册时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

    @Schema(description = "机构名称")
    @TableField(exist = false)
    private String organizationName;

    @Schema(description = "性别名称")
    @TableField(exist = false)
    private String sexName;

    @Schema(description = "角色列表")
    @TableField(exist = false)
    private List<Role> roles;

    @Schema(description = "权限列表")
    @TableField(exist = false)
    private List<Menu> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status != null && this.status == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
