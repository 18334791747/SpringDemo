package Proxy_Factory;

import aspect.MethodMatcher;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * 包含了需要插入切面的全部信息：对象，类，接口，通知，切点
 * Created by MiaoZhuang on 2016/6/1.
 */
public class AdvisedSupport {

    private TargetSource targetSource;

    private MethodInterceptor methodInterceptor;

    private MethodMatcher methodMatcher;

    private DefaultAopProxyFactory defaultAopProxyFactory;


    public AdvisedSupport(TargetSource targetSource, MethodInterceptor methodInterceptor, MethodMatcher methodMatcher) {
        this.targetSource = targetSource;
        this.methodInterceptor = methodInterceptor;
        this.methodMatcher = methodMatcher;
        defaultAopProxyFactory = new DefaultAopProxyFactory();
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public AopProxyFactory getDefaultAopProxyFactory() {
        return defaultAopProxyFactory;
    }
}
