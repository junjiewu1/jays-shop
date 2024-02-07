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
@Schema(name = "Address", description = "")

public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Integer defaultStatus;

    private String receiveName;

    private String phone;

    @Schema(description = "省/直辖市")
    private String province;

    @Schema(description = "市")
    private String city;

    @Schema(description = "区")
    private String region;

    @Schema(description = "详细地址")
    private String detailAddress;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
