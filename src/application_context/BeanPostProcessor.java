package application_context;

/**
 * 定义了后置处理器接口的操作
 * Created by MiaoZhuang on 2016/6/2.
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName);

    Object postProcessAfterInitialization(Object bean, String beanName);
}
