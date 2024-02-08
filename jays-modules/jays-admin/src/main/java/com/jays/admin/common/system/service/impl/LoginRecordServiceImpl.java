package com.jays.admin.common.system.service.impl;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jays.common.core.web.PageParam;
import com.jays.common.core.web.PageResult;
import com.jays.admin.common.system.entity.LoginRecord;
import com.jays.admin.common.system.mapper.LoginRecordMapper;
import com.jays.admin.common.system.param.LoginRecordParam;
import com.jays.admin.common.system.service.LoginRecordService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Enumeration;
import java.util.List;

/**
 * 登录日志Service实现
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:14
 */
@Service
public class LoginRecordServiceImpl extends ServiceImpl<LoginRecordMapper, LoginRecord>
        implements LoginRecordService {

    @Override
    public PageResult<LoginRecord> pageRel(LoginRecordParam param) {
        PageParam<LoginRecord, LoginRecordParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        return new PageResult<>(baseMapper.selectPageRel(page, param), page.getTotal());
    }

    @Override
    public List<LoginRecord> listRel(LoginRecordParam param) {
        PageParam<LoginRecord, LoginRecordParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(baseMapper.selectListRel(param));
    }

    @Override
    public LoginRecord getByIdRel(Integer id) {
        LoginRecordParam param = new LoginRecordParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

    @Async
    @Override
    public void saveAsync(String username, Integer type, String comments, Integer tenantId,
                          HttpServletRequest request) {
        if (username == null) {
            return;
        }
        LoginRecord loginRecord = new LoginRecord();
        loginRecord.setUsername(username);
        loginRecord.setLoginType(type);
        loginRecord.setComments(comments);
        loginRecord.setTenantId(tenantId);
        UserAgent ua = UserAgentUtil.parse(getHeaderIgnoreCase(request, "User-Agent"));
        if (ua != null) {
            if (ua.getPlatform() != null) {
                loginRecord.setOs(ua.getPlatform().toString());
            }
            if (ua.getOs() != null) {
                loginRecord.setDevice(ua.getOs().toString());
            }
            if (ua.getBrowser() != null) {
                loginRecord.setBrowser(ua.getBrowser().toString());
            }
        }
        loginRecord.setIp(getClientIP(request));
        baseMapper.insert(loginRecord);
    }

    public static String getHeaderIgnoreCase(HttpServletRequest request, String nameIgnoreCase) {
        Enumeration<String> names = request.getHeaderNames();

        String name;
        do {
            if (!names.hasMoreElements()) {
                return null;
            }

            name = (String)names.nextElement();
        } while(name == null || !name.equalsIgnoreCase(nameIgnoreCase));

        return request.getHeader(name);
    }

    public static String getClientIP(HttpServletRequest request, String... otherHeaderNames) {
        String[] headers = new String[]{"X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};
        if (ArrayUtil.isNotEmpty(otherHeaderNames)) {
            headers = (String[])ArrayUtil.addAll(new String[][]{headers, otherHeaderNames});
        }

        return getClientIPByHeader(request, headers);
    }

    public static String getClientIPByHeader(HttpServletRequest request, String... headerNames) {
        String[] var3 = headerNames;
        int var4 = headerNames.length;

        String ip;
        for(int var5 = 0; var5 < var4; ++var5) {
            String header = var3[var5];
            ip = request.getHeader(header);
            if (!NetUtil.isUnknown(ip)) {
                return NetUtil.getMultistageReverseProxyIp(ip);
            }
        }

        ip = request.getRemoteAddr();
        return NetUtil.getMultistageReverseProxyIp(ip);
    }

}
