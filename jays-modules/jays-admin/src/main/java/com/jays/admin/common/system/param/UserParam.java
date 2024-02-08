package com.jays.admin.common.system.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.jays.common.core.annotation.QueryField;
import com.jays.common.core.annotation.QueryType;
import com.jays.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户查询参数
 *
 * @author EleAdmin
 * @since 2021-08-29 20:35:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "用户查询参数")
public class UserParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @Schema(description = "账号")
    private String username;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "性别(字典)")
    @QueryField(type = QueryType.EQ)
    private String sex;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "邮箱是否验证, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer emailVerified;

    @Schema(description = "真实姓名")
    private String realName;

    @Schema(description = "身份证号")
    private String idCard;

    @Schema(description = "出生日期")
    private String birthday;

    @Schema(description = "机构id")
    @QueryField(type = QueryType.EQ)
    private Integer organizationId;

    @Schema(description = "状态, 0正常, 1冻结")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @Schema(description = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @Schema(description = "角色id")
    @TableField(exist = false)
    private Integer roleId;

    @Schema(description = "机构名称")
    @TableField(exist = false)
    private String organizationName;

    @Schema(description = "性别名称")
    @TableField(exist = false)
    private String sexName;

    @Schema(description = "搜索关键字")
    @TableField(exist = false)
    private String keywords;

}
