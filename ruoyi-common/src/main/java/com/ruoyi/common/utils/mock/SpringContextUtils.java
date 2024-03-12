package com.ruoyi.common.utils.mock;

import org.springframework.context.ApplicationContext;

/**
 * spring容器
 * @author Kindey.S [kindey123@163.com]
 * @date 2020/6/8 10:23
 */
public class SpringContextUtils {
    private static ApplicationContext applicationContext;

    //获取上下文
    public static ApplicationContext getApplicationContext () {
        return applicationContext;
    }

    //设置上下文
    public static void setApplicationContext (ApplicationContext applicationContext) {
        SpringContextUtils.applicationContext = applicationContext;
    }

    //通过名字获取上下文中的bean
    public static Object getBean (String name) {
        return applicationContext.getBean(name);
    }

    //通过类型获取上下文中的bean
    public static Object getBean (Class<?> requiredType) {
        return applicationContext.getBean(requiredType);
    }
}
