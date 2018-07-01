package bean_definition_reader;

import bean_definition.BeanDefinition;
import bean_definition.BeanReference;
import bean_definition.PropertyValue;
import bean_definition.PropertyValues;
import factory.AbstractBeanFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import resource.Resource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * Created by MiaoZhuang on 2016/5/25.
 */
public class XmlBeanDefinitionReader implements BeanDefinitionReader {

    private AbstractBeanFactory abstractBeanFactory = null;

    public XmlBeanDefinitionReader(AbstractBeanFactory abstractBeanFactory) {
        this.abstractBeanFactory = abstractBeanFactory;
    }


    @Override
    public void loadBeanDefinitions(Resource resource) {
        doLoadBeanDefinitions(resource.getInputStream());
    }

    /**
     * 将输入流转化为Document对象
     */
    private void doLoadBeanDefinitions(InputStream inputStream) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        Document document;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(inputStream);
            registerBeanDefinitions(document);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 原程序调用实现了BeanDefinitionDocumentReader接口的对象进行处理，
     * 这里直接对Document对象进行处理
     */
    private void registerBeanDefinitions(Document document) {
        Element root = document.getDocumentElement();
        parseBeanDefinitions(root);
    }

    /**
     * 寻找根节点下的元素，调用processBeanDefinition进行处理
     * 原程序需要对import的xml文件进行处理，也要对alias标签进
     * 行处理，这里略去这些步骤，直接调用processBeanDefinition
     * 进行处理
     * 此外目前也未对bean的属性配置进行解析
     */
    private void parseBeanDefinitions(Element root) {
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                processBeanDefinition(element);
            }
        }
    }

    /**
     * 原程序中调用BeanDefinitionParserDelegate对象的
     * parseBeanDefinitionElement方法进行处理，这里直接
     * 进行处理
     */
    private void processBeanDefinition(Element element) {
        String id = element.getAttribute("id");
        String className = element.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        processProperty(element, beanDefinition);
        beanDefinition.setBeanDefinitionClass(className);
        abstractBeanFactory.registerBeanDefinition(id, beanDefinition);
    }

    /**
     * 将对应的初始化参数装入PropertyValues对象中
     */
    private void processProperty(Element element, BeanDefinition beanDefinition) {
        NodeList nodeList = element.getElementsByTagName("property");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element ele = (Element) node;
                String name = ele.getAttribute("name");
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                if (ele.getAttribute("value") != null && ele.getAttribute("value").length() > 0) {
                    String value = ele.getAttribute("value");
                    propertyValues.add(new PropertyValue(name, value));
                } else {
                    String refName = ele.getAttribute("ref");
                    BeanReference beanReference = new BeanReference();
                    beanReference.setName(refName);
                    propertyValues.add(new PropertyValue(name, beanReference));
                }
            }
        }
    }
}
