package application_context;

import Proxy_Factory.ProxyFactory;
import Proxy_Factory.TargetSource;
import aspect.AspectJExpressionPointcut;
import aspect.AspectJExpressionPointcutAdvisor;
import factory.AutoWireCapableBeanFactory;
import factory.BeanFactory;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.List;

/**
 * 在后置处理器中自动执行的代理创建器，对“每一个生成的bean”进行检查，若对象存在对应的Advisor，
 * 则根据其是否存在接口调用Java动态代理或者Cglib进行处理，生成对应的动态代理对象
 * Created by Lfc on 2017/6/2.
 */
public class AspectJAwareAutoProxyCreater implements BeanPostProcessor, BeanFactoryAware {


    AutoWireCapableBeanFactory autoWireCapableBeanFactory;


    @Override
    public Object postProcessBeforeInitialization(Object bean, String name) {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String name) {

        //如果生成的bean为通知，直接通过
        if (bean instanceof MethodInterceptor) {
            return bean;
        }
        //如果生成的bean是切面，直接通过
        if (bean instanceof AspectJExpressionPointcutAdvisor) {
            return bean;
        }

        List<Object> list = autoWireCapableBeanFactory.getBeanByType(AspectJExpressionPointcutAdvisor.class);
        for (Object object : list) {
            AspectJExpressionPointcutAdvisor aspectJExpressionPointcutAdvisor = (AspectJExpressionPointcutAdvisor) object;
            AspectJExpressionPointcut aspectJExpressionPointcut = aspectJExpressionPointcutAdvisor.getAspectJExpressionPointcut();
            if (aspectJExpressionPointcut.matches(bean.getClass())) {
                TargetSource targetSource = new TargetSource(bean, bean.getClass(), bean.getClass().getInterfaces());
                ProxyFactory proxyFactory = new ProxyFactory(targetSource, aspectJExpressionPointcutAdvisor.getMethodInterceptor(), aspectJExpressionPointcut.getMethodMatcher());
                return proxyFactory.getProxy();
            }
        }
        return bean;
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.autoWireCapableBeanFactory = (AutoWireCapableBeanFactory) beanFactory;
    }

}
