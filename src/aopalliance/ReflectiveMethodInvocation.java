package aopalliance;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * Created by MiaoZhuang on 2016/6/1.
 */
public class ReflectiveMethodInvocation implements MethodInvocation {

    private Object target;
    private Method method;
    private Object[] args;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }


    /**
     * 获取被调用的方法
     *
     * @return
     */
    @Override
    public Method getMethod() {
        return method;
    }

    /**
     * 获取参数
     *
     * @return
     */
    @Override
    public Object[] getArguments() {
        return args;
    }

    /**
     * 执行方法至下一个拦截器链
     *
     * @return
     * @throws Exception
     */
    @Override
    public Object proceed() throws Exception {
        return method.invoke(target, args);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public Method getStaticPart() {
        return method;
    }
}
