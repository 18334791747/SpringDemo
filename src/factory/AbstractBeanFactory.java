package factory;

import application_context.BeanFactoryAware;
import application_context.BeanPostProcessor;
import bean_definition.BeanDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Lfc on 2017/5/25.
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    //存储BeanName与BeanDefinition的键值对
    protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    //存储后置处理器
    protected List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String beanDefinitionName) {

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanDefinitionName);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("bean名称" + beanDefinitionName + "未被定义");
        }
        // beanDefinition的注册过程并没有为beanDefinitionObject设置值
        Object bean = beanDefinition.getBeanDefinitionObject();
        if (bean == null) {
            //实例化Bean，同时将bean中的依赖也解决
            bean = doCreateBean(beanDefinitionMap.get(beanDefinitionName));
            //遍历所有beanDefinitionMap中的切面，如果如当前的bean符合，则将其替换为当前bean的代理
            bean = initializeBean(bean, beanDefinitionName);
            //之所以在这里才为其设置值，是因为如果其为空的话，就没有初始化Bean的必要，以实现单例模式
            beanDefinition.setBeanDefinitionObject(bean);
        }
        return bean;
    }

    /**
     * 调用后置处理器对获取的bean进行处理
     * 这个的类的功能是为了解决bean初试话成功，如果存在AOP的情况是需要将bean偷梁换柱成代理bean
     *
     * @param bean
     * @param beanDefinitionName
     * @return
     */
    public Object initializeBean(Object bean, String beanDefinitionName) {
        //初始化过程，设置自身所在的IOC容器
        if (bean instanceof BeanFactoryAware) {
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }

        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, beanDefinitionName);
        }
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, beanDefinitionName);
        }

        return bean;
    }

    /**
     * 遍历Ioc容器，获取指定类型的bean集合，即类型是指定类型的Class的子类的对象
     *
     * @param requireType
     * @return
     */
    public List<Object> getBeanByType(Class requireType) {
        List<Object> list = new ArrayList<>();
        for (String beanDefinitionName : beanDefinitionMap.keySet()) {
            if (requireType.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanDefinitionClass())) {
                list.add(this.getBean(beanDefinitionName));
            }
        }
        return list;
    }

    public void addBeanPostProfessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.add(beanPostProcessor);
    }

    public void registerBeanDefinition(String beanDefinitionName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanDefinitionName, beanDefinition);
    }

    public abstract Object doCreateBean(BeanDefinition beanDefinition);
}
