package factory;

/**
 * Spring IOC 顶层接口，对IOC容器的基本行为进行了定义
 * Created by MiaoZhuang on 2016/5/25.
 */
public interface BeanFactory {

    /**
     * 根据id获取bean
     *
     * @param beanName
     * @return
     */
    Object getBean(String beanName);

}
