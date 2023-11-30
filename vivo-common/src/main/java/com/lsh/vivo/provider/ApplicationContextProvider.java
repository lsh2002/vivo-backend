package com.lsh.vivo.provider;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 上下文
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 11:21
 */
@Slf4j
@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    /**
     * 上下文对象实例
     * -- GETTER --
     *  获取applicationContext
     *
     */
    @Getter
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextProvider.applicationContext = applicationContext;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name 实例名
     * @return 实例对象
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz 实例类型
     * @return 实例对象
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name 实例名
     * @param clazz 实例类型
     * @return 实例对象
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 获取当前运行环境
     * @return dev-开发模式
     */
    public static String getActiveProfile(){
        return getApplicationContext().getEnvironment().getActiveProfiles()[0];
    }
}