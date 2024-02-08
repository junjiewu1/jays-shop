package com.jays.admin.common.system.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jays.common.core.annotation.OperationLog;
import com.jays.common.core.utils.CommonUtil;
import com.jays.admin.common.system.entity.DictionaryData;
import com.jays.admin.common.system.entity.Organization;
import com.jays.admin.common.system.entity.Role;
import com.jays.admin.common.system.entity.User;
import com.jays.admin.common.system.param.UserImportParam;
import com.jays.admin.common.system.param.UserParam;
import com.jays.admin.common.system.service.DictionaryDataService;
import com.jays.admin.common.system.service.OrganizationService;
import com.jays.admin.common.system.service.RoleService;
import com.jays.admin.common.system.service.UserService;
import com.jays.common.core.web.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户控制器
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:41
 */
@Tag(name = "用户管理")
@RestController
@RequestMapping("/system/user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private OrganizationService organizationService;
    @Resource
    private DictionaryDataService dictionaryDataService;

    @PreAuthorize("hasAuthority('sys:user:list')")
    @OperationLog
    @Operation(summary ="分页查询用户")
    @GetMapping("/page")
    public ApiResult<PageResult<User>> page(UserParam param) {
        return success(userService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('sys:user:list')")
    @OperationLog
    @Operation(summary ="查询全部用户")
    @GetMapping()
    public ApiResult<List<User>> list(UserParam param) {
        return success(userService.listRel(param));
    }

    @PreAuthorize("hasAuthority('sys:user:list')")
    @OperationLog
    @Operation(summary ="根据id查询用户")
    @GetMapping("/{id}")
    public ApiResult<User> get(@PathVariable("id") Integer id) {
        return success(userService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('sys:user:save')")
    @OperationLog
    @Operation(summary ="添加用户")
    @PostMapping()
    public ApiResult<?> add(@RequestBody User user) {
        user.setStatus(0);
        user.setPassword(userService.encodePassword(user.getPassword()));
        if (userService.saveUser(user)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:user:update')")
    @OperationLog
    @Operation(summary ="修改用户")
    @PutMapping()
    public ApiResult<?> update(@RequestBody User user) {
        user.setStatus(null);
        user.setUsername(null);
        user.setPassword(null);
        if (userService.updateUser(user)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:user:remove')")
    @OperationLog
    @Operation(summary ="删除用户")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (userService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:user:update')")
    @OperationLog
    @Operation(summary ="批量修改用户")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<User> batchParam) {
        if (batchParam.update(userService, User::getUserId)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:user:remove')")
    @OperationLog
    @Operation(summary ="批量删除用户")
    @Parameters({
            @Parameter(name = "ids", description = "id数组", required = true)
    })
    @DeleteMapping("/batch")
    public ApiResult<?> deleteBatch(@RequestBody List<Integer> ids) {
        if (userService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:user:update')")
    @OperationLog
    @Operation(summary ="修改用户状态")
    @PutMapping("/status")
    public ApiResult<?> updateStatus(@RequestBody User user) {
        if (user.getUserId() == null || user.getStatus() == null || !Arrays.asList(0, 1).contains(user.getStatus())) {
            return fail("参数不正确");
        }
        User u = new User();
        u.setUserId(user.getUserId());
        u.setStatus(user.getStatus());
        if (userService.updateById(u)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:user:update')")
    @OperationLog
    @Operation(summary ="批量修改用户状态")
    @PutMapping("/status/batch")
    public ApiResult<?> updateStatusBatch(@RequestBody BatchParam<Integer> batchParam) {
        if (!Arrays.asList(0, 1).contains(batchParam.getData())) {
            return fail("状态值不正确");
        }
        if (userService.update(new LambdaUpdateWrapper<User>()
                .in(User::getUserId, batchParam.getIds())
                .set(User::getStatus, batchParam.getData()))) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:user:update')")
    @OperationLog
    @Operation(summary ="重置密码")
    @PutMapping("/password")
    public ApiResult<?> resetPassword(@RequestBody User user) {
        if (user.getUserId() == null || StrUtil.isBlank(user.getPassword())) {
            return fail("参数不正确");
        }
        User u = new User();
        u.setUserId(user.getUserId());
        u.setPassword(userService.encodePassword(user.getPassword()));
        if (userService.updateById(u)) {
            return success("重置成功");
        } else {
            return fail("重置失败");
        }
    }

    @PreAuthorize("hasAuthority('sys:user:update')")
    @OperationLog
    @Operation(summary ="批量重置密码")
    @PutMapping("/password/batch")
    public ApiResult<?> resetPasswordBatch(@RequestBody BatchParam<String> batchParam) {
        if (batchParam.getIds() == null || batchParam.getIds().size() == 0) {
            return fail("请选择用户");
        }
        if (batchParam.getData() == null) {
            return fail("请输入密码");
        }
        if (userService.update(new LambdaUpdateWrapper<User>()
                .in(User::getUserId, batchParam.getIds())
                .set(User::getPassword, userService.encodePassword(batchParam.getData())))) {
            return success("重置成功");
        } else {
            return fail("重置失败");
        }
    }

    @PreAuthorize("hasAuthority('sys:user:list')")
    @OperationLog
    @Operation(summary ="检查用户是否存在")
    @GetMapping("/existence")
    public ApiResult<?> existence(ExistenceParam<User> param) {
        if (param.isExistence(userService, User::getUserId)) {
            return success(param.getValue() + "已存在");
        }
        return fail(param.getValue() + "不存在");
    }

    /**
     * excel导入用户
     */
    @PreAuthorize("hasAuthority('sys:user:save')")
    @OperationLog
    @Operation(summary ="导入用户")
    @Transactional(rollbackFor = {Exception.class})
    @PostMapping("/import")
    public ApiResult<List<String>> importBatch(MultipartFile file) {
        ImportParams importParams = new ImportParams();
        try {
            List<UserImportParam> list = ExcelImportUtil.importExcel(file.getInputStream(),
                    UserImportParam.class, importParams);
            // 校验是否重复
            if (CommonUtil.checkRepeat(list, UserImportParam::getUsername)) {
                return fail("账号存在重复", null);
            }
            if (CommonUtil.checkRepeat(list, UserImportParam::getPhone)) {
                return fail("手机号存在重复", null);
            }
            // 校验是否存在
            List<User> usernameExists = userService.list(new LambdaQueryWrapper<User>().in(User::getUsername,
                    list.stream().map(UserImportParam::getUsername).collect(Collectors.toList())));
            if (usernameExists.size() > 0) {
                return fail("账号已经存在",
                        usernameExists.stream().map(User::getUsername).collect(Collectors.toList()));
            }
            List<User> phoneExists = userService.list(new LambdaQueryWrapper<User>().in(User::getPhone,
                    list.stream().map(UserImportParam::getPhone).collect(Collectors.toList())));
            if (phoneExists.size() > 0) {
                return fail("手机号已经存在",
                        phoneExists.stream().map(User::getPhone).collect(Collectors.toList()));
            }
            // 添加
            List<User> users = new ArrayList<>();
            for (UserImportParam one : list) {
                User u = new User();
                u.setStatus(0);
                u.setUsername(one.getUsername());
                u.setPassword(userService.encodePassword(one.getPassword()));
                u.setNickname(one.getNickname());
                u.setPhone(one.getPhone());
                Role role = roleService.getOne(new QueryWrapper<Role>()
                        .eq("role_name", one.getRoleName()), false);
                if (role == null) {
                    return fail("角色不存在", Collections.singletonList(one.getRoleName()));
                } else {
                    u.setRoles(Collections.singletonList(role));
                }
                Organization organization = organizationService.getOne(new QueryWrapper<Organization>()
                        .eq("organization_full_name", one.getOrganizationName()), false);
                if (organization == null) {
                    return fail("机构不存在", Collections.singletonList(one.getOrganizationName()));
                } else {
                    u.setOrganizationId(organization.getOrganizationId());
                }
                DictionaryData sex = dictionaryDataService.getByDictCodeAndName("sex", one.getSexName());
                if (sex == null) {
                    return fail("性别不存在", Collections.singletonList(one.getSexName()));
                } else {
                    u.setSex(sex.getDictDataCode());
                }
            }
            if (userService.saveBatch(users)) {
                return success("导入成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fail("导入失败", null);
    }

}
