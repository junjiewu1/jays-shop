package com.jays.common.core.web;

import com.baomidou.mybatisplus.annotation.TableField;
import com.jays.common.core.annotation.QueryField;
import com.jays.common.core.annotation.QueryType;
import com.jays.common.core.utils.CommonUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 查询参数基本字段
 *
 * @author EleAdmin
 * @since 2021-08-26 22:14:43
 */
public class BaseParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    //@Operation(summary="分页查询页码")
    private Long page;

    @TableField(exist = false)
    //@Operation(summary="分页查询每页数量")
    private Long limit;

    @TableField(exist = false)
    //@ApiModelProperty(value = "排序字段", notes = "排序字段或sql, 如果是sql则order字段无用, 如: `id asc, name desc`")
    private String sort;

    @TableField(exist = false)
    //@ApiModelProperty(value = "排序方式", notes = "sort是字段名称时对应的排序方式, asc升序, desc降序")
    private String order;

    @QueryField(value = "create_time", type = QueryType.GE)
    @TableField(exist = false)
    //@Operation(summary="创建时间起始值")
    private String createTimeStart;

    @QueryField(value = "create_time", type = QueryType.LE)
    @TableField(exist = false)
    //@Operation(summary="创建时间结束值")
    private String createTimeEnd;

    /**
     * 获取集合中的第一条数据
     *
     * @param records 集合
     * @return 第一条数据
     */
    public <T> T getOne(List<T> records) {
        return CommonUtil.listGetOne(records);
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(String createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }


    @Override
    public String toString() {
        return "BaseParam{" +
                "page=" + page +
                ", limit=" + limit +
                ", sort='" + sort + '\'' +
                ", order='" + order + '\'' +
                ", createTimeStart='" + createTimeStart + '\'' +
                ", createTimeEnd='" + createTimeEnd + '\'' +
                '}';
    }
}
