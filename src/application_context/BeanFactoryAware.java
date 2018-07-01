package application_context;

import factory.BeanFactory;

/**
 * Bean对IOC容器的感知，定义的方法可以在Bean中得到Bean所在的IOC容器，从而在Bean中直接使用IOC容器的服务
 * Created by MiaoZhuang on 2016/6/2.
 */
public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory beanFactory);
}
