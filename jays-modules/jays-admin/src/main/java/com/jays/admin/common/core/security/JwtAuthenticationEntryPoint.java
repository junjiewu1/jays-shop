package com.jays.admin.common.core.security;

import com.jays.common.core.constant.Constants;
import com.jays.common.core.utils.CommonUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 没有登录异常处理
 *
 * @author EleAdmin
 * @since 2020-03-25 00:35:03
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException, ServletException {
        CommonUtil.responseError(response, Constants.UNAUTHENTICATED_CODE, Constants.UNAUTHENTICATED_MSG,
                e.getMessage());
    }

}
