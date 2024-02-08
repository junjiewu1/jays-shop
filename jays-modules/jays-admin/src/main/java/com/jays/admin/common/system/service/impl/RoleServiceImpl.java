package com.jays.admin.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jays.admin.common.system.entity.Role;
import com.jays.admin.common.system.mapper.RoleMapper;
import com.jays.admin.common.system.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * 角色服务实现类
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:11
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
