package application_context;

import resource.Resource;
import resource.URLResource;

/**
 * 并未实现类加载器
 * Created by MiaoZhuang on 2016/5/27.
 */
public class ClassPathXmlApplicationContext extends AbstractRefreshableApplicationContext {

    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) {
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    public Object getBean(String beanName) {
        return this.autoWireCapableBeanFactory.getBean(beanName);
    }

    @Override
    public Resource getConfigResources() {
        return new URLResource(configLocation);
    }
}
