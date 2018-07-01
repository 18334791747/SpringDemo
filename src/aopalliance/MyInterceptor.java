package aopalliance;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by MiaoZhuang on 2016/6/1.
 */
public class MyInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("方法调用之前");
        Object object = methodInvocation.proceed();
        System.out.println("方法调用之后");
        return object;
    }
}
