package com.jays.common.core.utils;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.jays.common.core.constant.Constants;
import com.jays.common.core.web.ApiResult;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 常用工具方法
 *
 * @author EleAdmin
 * @since 2017-06-10 10:10:22
 */
public class CommonUtil {
    // 生成uuid的字符
    private static final String[] chars = new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    /**
     * 生成8位uuid
     *
     * @return String
     */
    public static String randomUUID8() {
        StringBuilder sb = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            sb.append(chars[x % 0x3E]);
        }
        return sb.toString();
    }

    /**
     * 生成16位uuid
     *
     * @return String
     */
    public static String randomUUID16() {
        StringBuilder sb = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 16; i++) {
            String str = uuid.substring(i * 2, i * 2 + 2);
            int x = Integer.parseInt(str, 16);
            sb.append(chars[x % 0x3E]);
        }
        return sb.toString();
    }

    /**
     * 检查List是否有重复元素
     *
     * @param list   List
     * @param mapper 获取需要检查的字段的Function
     * @param <T>    数据的类型
     * @param <R>    需要检查的字段的类型
     * @return boolean
     */
    public static <T, R> boolean checkRepeat(List<T> list, Function<? super T, ? extends R> mapper) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i != j && mapper.apply(list.get(i)).equals(mapper.apply(list.get(j)))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * List转为树形结构
     *
     * @param data           List
     * @param parentId       顶级的parentId
     * @param parentIdMapper 获取parentId的Function
     * @param idMapper       获取id的Function
     * @param consumer       赋值children的Consumer
     * @param <T>            数据的类型
     * @param <R>            parentId的类型
     * @return List<T>
     */
    public static <T, R> List<T> toTreeData(List<T> data, R parentId,
                                            Function<? super T, ? extends R> parentIdMapper,
                                            Function<? super T, ? extends R> idMapper,
                                            BiConsumer<T, List<T>> consumer) {
        List<T> result = new ArrayList<>();
        for (T d : data) {
            R dParentId = parentIdMapper.apply(d);
            if (ObjectUtil.equals(parentId, dParentId)) {
                R dId = idMapper.apply(d);
                List<T> children = toTreeData(data, dId, parentIdMapper, idMapper, consumer);
                consumer.accept(d, children);
                result.add(d);
            }
        }
        return result;
    }

    /**
     * 遍历树形结构数据
     *
     * @param data     List
     * @param consumer 回调
     * @param mapper   获取children的Function
     * @param <T>      数据的类型
     */
    public static <T> void eachTreeData(List<T> data, Consumer<T> consumer, Function<T, List<T>> mapper) {
        for (T d : data) {
            consumer.accept(d);
            List<T> children = mapper.apply(d);
            if (children != null && children.size() > 0) {
                eachTreeData(children, consumer, mapper);
            }
        }
    }

    /**
     * 获取集合中的第一条数据
     *
     * @param records 集合
     * @return 第一条数据
     */
    public static <T> T listGetOne(List<T> records) {
        return records == null || records.size() == 0 ? null : records.get(0);
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



    /**
     * 获取请求参数
     *
     * @param joinPoint JoinPoint
     * @param request   HttpServletRequest
     * @return String
     */
    public static String getParams(JoinPoint joinPoint, HttpServletRequest request) {
        String params;
        Map<String, String> paramsMap = getParamMap(request);
        if (paramsMap.keySet().size() > 0) {
            params = JSONUtil.toJSONString(paramsMap);
        } else {
            StringBuilder sb = new StringBuilder();
            for (Object arg : joinPoint.getArgs()) {
                if (ObjectUtil.isNull(arg)
                        || arg instanceof MultipartFile
                        || arg instanceof HttpServletRequest
                        || arg instanceof HttpServletResponse) {
                    continue;
                }
                sb.append(JSONUtil.toJSONString(arg)).append(" ");
            }
            params = sb.toString();
        }
        return params;
    }

    public static Map<String, String[]> getParams(ServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getParamMap(ServletRequest request) {
        Map<String, String> params = new HashMap();
        Iterator var2 = getParams(request).entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<String, String[]> entry = (Map.Entry)var2.next();
            params.put(entry.getKey(), ArrayUtil.join((Object[])entry.getValue(), ","));
        }

        return params;
    }

    /**
     * 支持跨域
     *
     * @param response HttpServletResponse
     */
    public static void addCrossHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Expose-Headers", Constants.TOKEN_HEADER_NAME);
    }

    /**
     * 输出错误信息
     *
     * @param response HttpServletResponse
     * @param code     错误码
     * @param message  提示信息
     * @param error    错误信息
     */
    public static void responseError(HttpServletResponse response, Integer code, String message, String error) {
        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.write(JSONUtil.toJSONString(new ApiResult<>(code, message, null, error)));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
