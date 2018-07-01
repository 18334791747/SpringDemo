package factory;

import bean_definition_reader.XmlBeanDefinitionReader;
import resource.Resource;

/**
 * 对AutoWireCapableBeanFactory进行了简单的封装，使其能直接处理Resource类型
 * Created by MiaoZhuang on 2016/6/5.
 */
public class XmlBeanFactory extends AutoWireCapableBeanFactory {

    private XmlBeanDefinitionReader xmlBeanDefinitionReader;

    public XmlBeanFactory(Resource resource) {
        xmlBeanDefinitionReader = new XmlBeanDefinitionReader(this);
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
    }
}
