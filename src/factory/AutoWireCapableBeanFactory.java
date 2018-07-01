package factory;

import bean_definition.BeanDefinition;
import bean_definition.BeanReference;
import bean_definition.PropertyValue;
import bean_definition.PropertyValues;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * Created by Lfc on 2017/5/25.
 */
public class AutoWireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    public Object doCreateBean(BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            //创建Bean
            bean = createBeanInstance(beanDefinition);
            //依赖注入
            applyPropertyValues(bean, beanDefinition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 利用Java的反射机制创建指定BeanDefinition的对象
     */
    public Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanDefinitionClass().newInstance();
    }

    /**
     * 使用java的内省机制给bean中的属性赋值
     */
    public void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {

        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            if (propertyValue.getObject() instanceof String) {
                Method declaredMethod = bean.getClass().getDeclaredMethod("set" + propertyValue.getName().substring(0, 1).toUpperCase() + propertyValue.getName().substring(1), String.class);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(bean, (String) propertyValue.getObject());
            } else {
                // 当需要注入的是一个对象，而不是一个简单的字符串时
                BeanReference beanReference = (BeanReference) propertyValue.getObject();
                String refName = beanReference.getName();
                //由于不是一个简单的字符串，所以这个对象需要去BeanFactory里面去找
                Object object = getBean(refName);
                //构造一个方法对象，虽然不像上面的那个直接，这个是根据参数1的内容构建set和get方法，
                //参数2则为调用者的class对象
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyValue.getName(), beanDefinition.getBeanDefinitionClass());
                Method method = propertyDescriptor.getWriteMethod();
                //第二个参数就是通过反射的机制将Object类型的对象向下转型成需要的类型
                method.invoke(bean, beanDefinitionMap.get(refName).getBeanDefinitionClass().cast(object));
            }
        }
    }
}
