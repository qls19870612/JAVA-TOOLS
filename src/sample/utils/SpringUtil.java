package sample.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

import sample.fxml.controllers.client.handlers.base.Handler;

@Component
public class SpringUtil implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(SpringUtil.class);
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    public static Map<String, Object> getHandlers() {
        Map<String, Object> beansWithAnnotation = getApplicationContext().getBeansWithAnnotation(Handler.class);
        logger.debug("getHandlers beansWithAnnotation.size:{}", beansWithAnnotation.size());
        return beansWithAnnotation;
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    //    @ApolloConfigChangeListener
    //    public void onChangeConfig() {
    //
    //    }

}
