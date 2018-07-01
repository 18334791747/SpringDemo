package main;

import application_context.ClassPathXmlApplicationContext;

/**
 * Created by MiaoZhuang on 2016/5/25.
 */
public class Main {
    public static void main(String[] args) {

        //BeanFactory测试
//        AutoWireCapableBeanFactory autoWireCapableBeanFactory = new AutoWireCapableBeanFactory();
//        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(autoWireCapableBeanFactory);
//        xmlBeanDefinitionReader.loadBeanDefinitions(new URLResource("spring.xml"));
//        MySpringTest mySpringTest = (MySpringTest)autoWireCapableBeanFactory.getBean("MySpringTest");
//        mySpringTest.getOutputService().outputMessage();

        //ApplicationContext的IOC与AOP测试
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring.xml");
//        MySpringTest mySpringTest1 = (MySpringTest)classPathXmlApplicationContext.getBean("MySpringTest");
//        mySpringTest1.getOutputService().outputMessage();
//通过getBean的方式获取bean，还未做到注解那般
        Knight knight = (Knight) classPathXmlApplicationContext.getBean("Knight");
        Person scientist = (Person) classPathXmlApplicationContext.getBean("Scientist");
        knight.say();
        knight.walk();
        scientist.say();
        scientist.walk();
    }
}
