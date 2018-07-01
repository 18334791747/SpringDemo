package application_context;

import factory.AutoWireCapableBeanFactory;

/**
 * 定义了refresh的基本框架
 * Created by MiaoZhuang on 2016/5/27.
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    protected AutoWireCapableBeanFactory autoWireCapableBeanFactory;

    public void refresh() {
        //BeanDefinition的加载注册在此步完成
        refreshBeanFactory();
        //注册bean的后置处理器
        registerBeanPostProcessors(autoWireCapableBeanFactory);
    }

    abstract void registerBeanPostProcessors(AutoWireCapableBeanFactory autoWireCapableBeanFactory);

    abstract void refreshBeanFactory();
}
