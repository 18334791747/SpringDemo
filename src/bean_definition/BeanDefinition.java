package bean_definition;

/**
 * 存储通过反射创建对象所需要的信息
 * Created by MiaoZhuang on 2016/5/25.
 */
public class BeanDefinition {

    //类对象
    private Object beanDefinitionObject;

    //类名
    private Class beanDefinitionClass;

    //参数容器
    private PropertyValues propertyValues = new PropertyValues();

    public Class getBeanDefinitionClass() {
        return beanDefinitionClass;
    }

    public void setBeanDefinitionClass(String beanDefinitionClass) {
        try {
            this.beanDefinitionClass = Class.forName(beanDefinitionClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Object getBeanDefinitionObject() {
        return this.beanDefinitionObject;
    }

    public void setBeanDefinitionObject(Object beanDefinitionObject) {
        this.beanDefinitionObject = beanDefinitionObject;
    }

    public PropertyValues getPropertyValues() {
        return this.propertyValues;
    }
}
