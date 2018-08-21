package com.seda.dailyReport.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 手动从Spring WebApplicationContext 容器中获得bean
 */
@Component
public final class SpringBeanUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static  <T> T getBean(String beanName, Class<T> clazz) {
        return applicationContext.getBean(beanName, clazz);
    }

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
    	SpringBeanUtils.applicationContext = applicationContext;
    }

}
