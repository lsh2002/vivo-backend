package com.lsh.vivo.util;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * OauthContext类
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/22 21:49
 */
@Slf4j
public class OauthContext {
    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = new ThreadLocal<>();

    public static Map<String, Object> get() {
        return THREAD_LOCAL.get();
    }

    /**
     * 获取参数值
     *
     * @param key 键
     * @return 值
     */
    public static Object get(String key) {
        Map<String, Object> map = get();
        return map == null ? null : map.get(key);
    }

    public static void set(String key, Object value) {
        Map<String, Object> map = get();
        if (map == null) {
            map = new HashMap<>(16);
        }
        map.put(key, value);
        set(map);
    }

    public static void set(Map<String, Object> map) {
        if (map == null) {
            return;
        }
        THREAD_LOCAL.set(map);
    }

    public static void clear() {
        THREAD_LOCAL.remove();
    }
}
