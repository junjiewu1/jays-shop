package com.jays.admin.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jays.admin.common.system.entity.Menu;
import com.jays.admin.common.system.mapper.MenuMapper;
import com.jays.admin.common.system.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * 菜单Service实现
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:10
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
