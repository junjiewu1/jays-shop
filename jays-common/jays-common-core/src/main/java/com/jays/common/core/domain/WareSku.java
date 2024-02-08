package com.jays.common.core.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 商品库存
 * </p>
 *
 * @author junjie
 * @since 2023-11-01
 */
@Data
public class WareSku implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    private Long skuId;

    private Long wareId;

    private Integer stock;

    private String skuName;

    private Integer stockLocked;
}
