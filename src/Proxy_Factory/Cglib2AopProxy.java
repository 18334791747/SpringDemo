package Proxy_Factory;

import aopalliance.ReflectiveMethodInvocation;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 通过Cglib创建代理对象，不需要被代理的对象实现接口
 * Created by MiaoZhuang on 2016/6/3.
 */
public class Cglib2AopProxy implements AopProxy, MethodInterceptor {

    private AdvisedSupport advisedSupport;

    public Cglib2AopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        //设置被代理对象的类
        enhancer.setSuperclass(advisedSupport.getTargetSource().getTargetClass());
        //设置回调函数，该类需要实现net.sf.cglib.proxy.MethodInterceptor接口
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     * 所实现的回调函数，若该对象调用的method与表达式匹配，则代理，否则不代理，执行原来的函数
     *
     * @param object      生成的代理对象
     * @param method      原对象的方法“指针”
     * @param args        方法的参数
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if (advisedSupport.getMethodMatcher().matches(method, advisedSupport.getTargetSource().getTarget().getClass())) {
            org.aopalliance.intercept.MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(), method, args));
        } else {
            return method.invoke(advisedSupport.getTargetSource().getTarget(), args);
        }
    }
}
