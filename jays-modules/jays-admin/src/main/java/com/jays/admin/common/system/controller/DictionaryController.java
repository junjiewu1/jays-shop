package com.jays.admin.common.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jays.common.core.annotation.OperationLog;
import com.jays.common.core.utils.CommonUtil;
import com.jays.admin.common.system.entity.Dictionary;
import com.jays.admin.common.system.param.DictionaryParam;
import com.jays.admin.common.system.service.DictionaryService;
import com.jays.common.core.web.ApiResult;
import com.jays.common.core.web.BaseController;
import com.jays.common.core.web.PageParam;
import com.jays.common.core.web.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 字典控制器
 *
 * @author EleAdmin
 * @since 2020-03-14 11:29:03
 */
@Tag(name = "字典管理")
@RestController
@RequestMapping("/system/dictionary")
public class DictionaryController extends BaseController {
    @Resource
    private DictionaryService dictionaryService;

    @PreAuthorize("hasAuthority('sys:dict:list')")
    @OperationLog
    @Operation(summary ="分页查询字典")
    @GetMapping("/page")
    public ApiResult<PageResult<Dictionary>> page(DictionaryParam param) {
        PageParam<Dictionary, DictionaryParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number");
        return success(dictionaryService.page(page, page.getWrapper()));
    }

    @PreAuthorize("hasAuthority('sys:dict:list')")
    @OperationLog
    @Operation(summary ="查询全部字典")
    @GetMapping()
    public ApiResult<List<Dictionary>> list(DictionaryParam param) {
        PageParam<Dictionary, DictionaryParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number");
        return success(dictionaryService.list(page.getOrderWrapper()));
    }

    @PreAuthorize("hasAuthority('sys:dict:list')")
    @OperationLog
    @Operation(summary ="根据id查询字典")
    @GetMapping("/{id}")
    public ApiResult<Dictionary> get(@PathVariable("id") Integer id) {
        return success(dictionaryService.getById(id));
    }

    @PreAuthorize("hasAuthority('sys:dict:save')")
    @Operation(summary ="添加字典")
    @PostMapping()
    public ApiResult<?> add(@RequestBody Dictionary dictionary) {
        if (dictionaryService.count(new LambdaQueryWrapper<Dictionary>()
                .eq(Dictionary::getDictCode, dictionary.getDictCode())) > 0) {
            return fail("字典标识已存在");
        }
        if (dictionaryService.count(new LambdaQueryWrapper<Dictionary>()
                .eq(Dictionary::getDictName, dictionary.getDictName())) > 0) {
            return fail("字典名称已存在");
        }
        if (dictionaryService.save(dictionary)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:dict:update')")
    @OperationLog
    @Operation(summary ="修改字典")
    @PutMapping()
    public ApiResult<?> update(@RequestBody Dictionary dictionary) {
        if (dictionaryService.count(new LambdaQueryWrapper<Dictionary>()
                .eq(Dictionary::getDictCode, dictionary.getDictCode())
                .ne(Dictionary::getDictId, dictionary.getDictId())) > 0) {
            return fail("字典标识已存在");
        }
        if (dictionaryService.count(new LambdaQueryWrapper<Dictionary>()
                .eq(Dictionary::getDictName, dictionary.getDictName())
                .ne(Dictionary::getDictId, dictionary.getDictId())) > 0) {
            return fail("字典名称已存在");
        }
        if (dictionaryService.updateById(dictionary)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:dict:remove')")
    @OperationLog
    @Operation(summary ="删除字典")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (dictionaryService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:dict:save')")
    @OperationLog
    @Operation(summary ="批量添加字典")
    @PostMapping("/batch")
    public ApiResult<List<String>> saveBatch(@RequestBody List<Dictionary> list) {
        if (CommonUtil.checkRepeat(list, Dictionary::getDictCode)) {
            return fail("字典标识不能重复", null);
        }
        if (CommonUtil.checkRepeat(list, Dictionary::getDictName)) {
            return fail("字典名称不能重复", null);
        }
        List<Dictionary> list1 = dictionaryService.list();
        System.out.println(list1);
        List<Dictionary> codeExists = dictionaryService.list(new LambdaQueryWrapper<Dictionary>()
                .in(Dictionary::getDictCode, list.stream().map(Dictionary::getDictCode)
                        .collect(Collectors.toList())));
        if (codeExists.size() > 0) {
            return fail("字典标识已存在", codeExists.stream().map(Dictionary::getDictCode)
                    .collect(Collectors.toList())).setCode(2);
        }
        List<Dictionary> nameExists = dictionaryService.list(new LambdaQueryWrapper<Dictionary>()
                .in(Dictionary::getDictName, list.stream().map(Dictionary::getDictCode)
                        .collect(Collectors.toList())));
        if (nameExists.size() > 0) {
            return fail("字典名称已存在", nameExists.stream().map(Dictionary::getDictName)
                    .collect(Collectors.toList())).setCode(3);
        }
        if (dictionaryService.saveBatch(list)) {
            return success("添加成功", null);
        }
        return fail("添加失败", null);
    }

    @PreAuthorize("hasAuthority('sys:dict:remove')")
    @OperationLog
    @Operation(summary ="批量删除字典")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (dictionaryService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
