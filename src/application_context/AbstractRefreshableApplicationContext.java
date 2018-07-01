package application_context;

import bean_definition_reader.XmlBeanDefinitionReader;
import factory.AutoWireCapableBeanFactory;
import resource.Resource;

import java.util.List;

/**
 * Created by MiaoZhuang on 2016/5/27.
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    /**
     * 载入与注册BeanDefinition
     */
    @Override
    public void refreshBeanFactory() {
        autoWireCapableBeanFactory = new AutoWireCapableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(autoWireCapableBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(getConfigResources());
    }

    /**
     * 注册BeanPostProcessor
     *
     * @param autoWireCapableBeanFactory
     */
    @Override
    public void registerBeanPostProcessors(AutoWireCapableBeanFactory autoWireCapableBeanFactory) {
        List<Object> list = autoWireCapableBeanFactory.getBeanByType(BeanPostProcessor.class);
        for (Object object : list) {
            autoWireCapableBeanFactory.addBeanPostProfessor((BeanPostProcessor) object);
        }
    }

    public abstract Resource getConfigResources();
}
