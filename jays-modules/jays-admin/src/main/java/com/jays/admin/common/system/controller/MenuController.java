package com.jays.admin.common.system.controller;

import com.jays.common.core.annotation.OperationLog;
import com.jays.admin.common.system.entity.Menu;
import com.jays.admin.common.system.param.MenuParam;
import com.jays.admin.common.system.service.MenuService;
import com.jays.common.core.web.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 菜单控制器
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:23
 */
@Tag(name = "菜单管理")
@RestController
@RequestMapping("/system/menu")
public class MenuController extends BaseController {
    @Resource
    private MenuService menuService;

    @PreAuthorize("hasAuthority('sys:menu:list')")
    @OperationLog
    @Operation(summary ="分页查询菜单")
    @GetMapping("/page")
    public ApiResult<PageResult<Menu>> page(MenuParam param) {
        PageParam<Menu, MenuParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number");
        return success(menuService.page(page, page.getWrapper()));
    }

    @PreAuthorize("hasAuthority('sys:menu:list')")
    @OperationLog
    @Operation(summary ="查询全部菜单")
    @GetMapping()
    public ApiResult<List<Menu>> list(MenuParam param) {
        PageParam<Menu, MenuParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number");
        return success(menuService.list(page.getOrderWrapper()));
    }

    @PreAuthorize("hasAuthority('sys:menu:list')")
    @OperationLog
    @Operation(summary ="根据id查询菜单")
    @GetMapping("/{id}")
    public ApiResult<Menu> get(@PathVariable("id") Integer id) {
        return success(menuService.getById(id));
    }

    @PreAuthorize("hasAuthority('sys:menu:save')")
    @OperationLog
    @Operation(summary ="添加菜单")
    @PostMapping()
    public ApiResult<?> add(@RequestBody Menu menu) {
        if (menu.getParentId() == null) {
            menu.setParentId(0);
        }
        if (menuService.save(menu)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:menu:update')")
    @OperationLog
    @Operation(summary ="修改菜单")
    @PutMapping()
    public ApiResult<?> update(@RequestBody Menu menu) {
        if (menuService.updateById(menu)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:menu:remove')")
    @OperationLog
    @Operation(summary ="删除菜单")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (menuService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:menu:save')")
    @OperationLog
    @Operation(summary ="批量添加菜单")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<Menu> menus) {
        if (menuService.saveBatch(menus)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:menu:update')")
    @OperationLog
    @Operation(summary ="批量修改菜单")
    @PutMapping("/batch")
    public ApiResult<?> updateBatch(@RequestBody BatchParam<Menu> batchParam) {
        if (batchParam.update(menuService, "menu_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:menu:remove')")
    @OperationLog
    @Operation(summary ="批量删除菜单")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (menuService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
