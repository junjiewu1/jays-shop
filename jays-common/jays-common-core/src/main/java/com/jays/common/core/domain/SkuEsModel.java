package com.jays.common.core.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SkuEsModel { //common中
    private Long skuId;
    private Long spuId;
    private String skuTitle;
    private BigDecimal skuPrice;
    private String skuImg;
    private Long saleCount;
    private boolean hasStock;
    private Long hotScore;
    private Long brandId;
    private Long catalogId;
    private String brandName;
    private String brandImg;
    private String catalogName;
    private List<SkuEsAttr> attrs;
}

