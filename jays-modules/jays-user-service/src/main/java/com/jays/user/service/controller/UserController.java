package com.jays.user.service.controller;

import com.gulimall.common.core.web.*;
import com.jays.user.service.entity.vo.UserParam;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import java.util.List;

import com.jays.user.service.service.UserService;
import com.jays.user.service.entity.User;

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
@RequestMapping("/user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    //新增或者更新
    @PostMapping
    public ApiResult<?> save(@RequestBody User user) {
        return success(userService.saveOrUpdate(user));
    }

    //删除
    @DeleteMapping("/{id}")
    public ApiResult<?> delete(@PathVariable Integer id) {
        return success(userService.removeById(id));
    }

    @PostMapping("/del/batch")
    public ApiResult<?> deleteBatch(@RequestBody List<Integer> ids) {//批量删除
        return success(userService.removeByIds(ids));
    }

    @GetMapping("/{id}")
    public ApiResult<User> findOne(@PathVariable Integer id) {
        return success(userService.getById(id));
    }

    @GetMapping("/page")
    public ApiResult<PageResult<User>> findPage(UserParam param) {
        PageParam<User, UserParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number");
        return success(userService.page(page, page.getWrapper()));
    }

    //查询所有数据
    @GetMapping
    public ApiResult<List<User>> findAll(UserParam param) {
        PageParam<User, UserParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number");
        return success(userService.list(page.getWrapper()));
    }
}

