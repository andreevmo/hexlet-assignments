package exercise;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

    private static Map<Object, String> beansForLog = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Inspect.class)) {
            String level = bean.getClass().getAnnotation(Inspect.class).level();
            beansForLog.put(bean, level);
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beansForLog.containsKey(bean)) {
            bean = Proxy.newProxyInstance(bean.getClass().getClassLoader(),
                    bean.getClass().getInterfaces(),
                    new Handler(bean, beansForLog.get(bean)));
        }
        return bean;
    }

    static class Handler implements InvocationHandler {
        private Logger logger;
        private String level;
        private Object bean;
        Handler(Object bean, String level) {
            this.bean = bean;
            this.level = level;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
            logger = LoggerFactory.getLogger(proxy.getClass());
            String nameMethod = method.getName();
            method.setAccessible(true);

            if (level.equalsIgnoreCase("INFO")) {
                logger.info("Was called method: " + nameMethod + "() with arguments: " + Arrays.toString(objects));
            } else if (level.equalsIgnoreCase("DEBUG")) {
                logger.debug("Was called method: " + nameMethod + "() with arguments: " + Arrays.toString(objects));
            }
            return method.invoke(bean, objects);
        }
    }
}
// END
