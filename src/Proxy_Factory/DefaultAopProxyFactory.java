package Proxy_Factory;

/**
 * AopProxyFactory的实现，根据代理目标是否实现接口返回不同的AopProxy
 * Created by MiaoZhuang on 2016/6/7.
 */
public class DefaultAopProxyFactory implements AopProxyFactory {

    @Override
    public AopProxy createAopProxy(AdvisedSupport advisedSupport) {
        if (advisedSupport.getTargetSource().getTargetInterfaces().length == 0) {
            Cglib2AopProxy cglib2AopProxy = new Cglib2AopProxy(advisedSupport);
            return cglib2AopProxy;
        } else {
            JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
            return jdkDynamicAopProxy;
        }
    }
}
