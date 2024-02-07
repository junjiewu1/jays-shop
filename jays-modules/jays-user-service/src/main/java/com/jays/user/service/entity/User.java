package com.jays.user.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author junjie
 * @since 2024-02-06
 */
@Getter
@Setter
@Accessors(chain = true)
@Schema(name = "User", description = "")

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String pwd;

    private String headImg;

    private String slogan;

    @Schema(description = "0表示女，1表示男")
    private Byte sex;

    private Integer points;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String mail;

    private String secret;
}
