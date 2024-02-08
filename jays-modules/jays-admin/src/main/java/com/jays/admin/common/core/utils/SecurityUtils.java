package com.jays.admin.common.core.utils;

import com.jays.admin.common.system.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    /**
     * 获取当前登录的user
     *
     * @return User
     */
    public static User getLoginUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                Object object = authentication.getPrincipal();
                if (object instanceof User) {
                    return (User) object;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * 获取当前登录的userId
     *
     * @return userId
     */
    public static Integer getLoginUserId() {
        User loginUser = getLoginUser();
        return loginUser == null ? null : loginUser.getUserId();
    }
}
