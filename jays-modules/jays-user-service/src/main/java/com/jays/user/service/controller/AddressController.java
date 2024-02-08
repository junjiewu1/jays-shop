package com.jays.user.service.controller;

import com.jays.common.core.web.ApiResult;
import com.jays.common.core.web.BaseController;
import com.jays.common.core.web.PageParam;
import com.jays.common.core.web.PageResult;
import com.jays.user.service.entity.vo.AddressParam;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import java.util.List;

import com.jays.user.service.service.AddressService;
import com.jays.user.service.entity.Address;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author junjie
 * @since 2024-02-06
 */
@RestController
@RequestMapping("/api/address")
public class AddressController extends BaseController {
    @Resource
    private AddressService addressService;

    //新增或者更新
    @PostMapping
    public ApiResult<?> save(@RequestBody Address address) {
        return success(addressService.saveOrUpdate(address));
    }

    //删除
    @DeleteMapping("/{id}")
    public ApiResult<?> delete(@PathVariable Integer id) {
        return success(addressService.removeById(id));
    }

    @PostMapping("/del/batch")
    public ApiResult<?> deleteBatch(@RequestBody List<Integer> ids) {//批量删除
        return success(addressService.removeByIds(ids));
    }

    @GetMapping("/{id}")
    public ApiResult<Address> findOne(@PathVariable Integer id) {
        return success(addressService.getById(id));
    }

    @GetMapping("/page")
    public ApiResult<PageResult<Address>> findPage(AddressParam param) {
        PageParam<Address, AddressParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number");
        return success(addressService.page(page, page.getWrapper()));
    }

    //查询所有数据
    @GetMapping
    public ApiResult<List<Address>> findAll(AddressParam param) {
        PageParam<Address, AddressParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number");
        return success(addressService.list(page.getWrapper()));
    }
}

