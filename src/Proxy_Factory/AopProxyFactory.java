package Proxy_Factory;

/**
 * 定义了AopProxyFactory的方法：获取AopProxy
 * Created by MiaoZhuang on 2016/6/7.
 */
public interface AopProxyFactory {

    AopProxy createAopProxy(AdvisedSupport advisedSupport);
}
