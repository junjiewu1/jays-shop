package com.jays.admin.common.core.security;

import cn.hutool.core.util.StrUtil;
import com.jays.admin.common.core.config.ConfigProperties;
import com.jays.admin.common.system.entity.LoginRecord;
import com.jays.admin.common.system.entity.Menu;
import com.jays.admin.common.system.entity.User;
import com.jays.admin.common.system.service.LoginRecordService;
import com.jays.admin.common.system.service.UserService;
import com.jays.common.core.constant.Constants;
import com.jays.common.core.utils.CommonUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 处理携带token的请求过滤器
 *
 * @author EleAdmin
 * @since 2020-03-30 20:48:05
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Resource
    private ConfigProperties configProperties;
    @Resource
    private UserService userService;
    @Resource
    private LoginRecordService loginRecordService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String access_token = JwtUtil.getAccessToken(request);
        if (StrUtil.isNotBlank(access_token)) {
            try {
                // 解析token
                Claims claims = JwtUtil.parseToken(access_token, configProperties.getTokenKey());
                JwtSubject jwtSubject = JwtUtil.getJwtSubject(claims);
                User user = userService.getByUsername(jwtSubject.getUsername(), jwtSubject.getTenantId());
                if (user == null) {
                    throw new UsernameNotFoundException("Username not found");
                }
                List<Menu> authorities = user.getAuthorities().stream()
                        .filter(m -> StrUtil.isNotBlank(m.getAuthority())).collect(Collectors.toList());
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        user, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                // token将要过期签发新token, 防止突然退出登录
                long expiration = (claims.getExpiration().getTime() - new Date().getTime()) / 1000 / 60;
                if (expiration < configProperties.getTokenRefreshTime()) {
                    String token = JwtUtil.buildToken(jwtSubject, configProperties.getTokenExpireTime(),
                            configProperties.getTokenKey());
                    response.addHeader(Constants.TOKEN_HEADER_NAME, token);
                    loginRecordService.saveAsync(user.getUsername(), LoginRecord.TYPE_REFRESH, null,
                            user.getTenantId(), request);
                }
            } catch (ExpiredJwtException e) {
                CommonUtil.responseError(response, Constants.TOKEN_EXPIRED_CODE, Constants.TOKEN_EXPIRED_MSG,
                        e.getMessage());
                return;
            } catch (Exception e) {
                CommonUtil.responseError(response, Constants.BAD_CREDENTIALS_CODE, Constants.BAD_CREDENTIALS_MSG,
                        e.toString());
                return;
            }
        }
        chain.doFilter(request, response);
    }

}
