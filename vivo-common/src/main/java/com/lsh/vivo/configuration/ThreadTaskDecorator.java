package com.lsh.vivo.configuration;

import com.lsh.vivo.bean.constant.GlobalConstant;
import com.lsh.vivo.util.OauthContext;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskDecorator;

import java.util.Objects;

/**
 * ThreadTaskDecorator类
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/24 18:45
 */
@Slf4j
public class ThreadTaskDecorator implements TaskDecorator {

    @NonNull
    @Override
    public Runnable decorate(@NonNull Runnable runnable) {
        //获取父线程的loginVal
        Object userId = OauthContext.get(GlobalConstant.HTTP_USER_ID);
        Object user = OauthContext.get(GlobalConstant.HTTP_USER);
        return () -> {
            try {
                // 将主线程的请求信息，设置到子线程中
                if (!Objects.isNull(userId)) {
                    OauthContext.set(GlobalConstant.HTTP_USER_ID, userId);
                }
                if (!Objects.isNull(user)) {
                    OauthContext.set(GlobalConstant.HTTP_USER, user);
                }
                // 执行子线程，这一步不要忘了
                runnable.run();
            } finally {
                // 线程结束，清空这些信息，否则可能造成内存泄漏
                OauthContext.clear();
            }
        };
    }
}
