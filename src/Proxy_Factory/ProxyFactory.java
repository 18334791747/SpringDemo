package Proxy_Factory;

import aspect.MethodMatcher;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * 生成Proxy的工厂类，可动态的判断通过Cglib还是Jdk动态代理生成
 * Created by MiaoZhuang on 2016/6/6.
 */
public class ProxyFactory extends AdvisedSupport implements AopProxy {

    public ProxyFactory(TargetSource targetSource, MethodInterceptor methodInterceptor, MethodMatcher methodMatcher) {
        super(targetSource, methodInterceptor, methodMatcher);
    }

    public Object getProxy() {
        return getDefaultAopProxyFactory().createAopProxy(this).getProxy();
    }
}
