package com.jays.user.service.service.impl;

import com.jays.user.service.entity.User;
import com.jays.user.service.mapper.UserMapper;
import com.jays.user.service.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author junjie
 * @since 2024-02-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
