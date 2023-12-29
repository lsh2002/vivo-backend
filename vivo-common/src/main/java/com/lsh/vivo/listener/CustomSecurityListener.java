package com.lsh.vivo.listener;

import com.lsh.vivo.util.AESUtils;
import com.mybatisflex.annotation.SetListener;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * CustomSecurityListener类
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/10/3 0:18
 */
@Slf4j
@Component
public class CustomSecurityListener implements SetListener {

    @Resource
    private AESUtils aesUtils;

    @Override
    public Object onSet(Object entity, String property, Object value) {

        return value;
    }
}
