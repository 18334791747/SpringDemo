package Proxy_Factory;

/**
 * 包含了被代理类的基本信息：对象，类，接口
 * Created by MiaoZhuang on 2016/6/1.
 */
public class TargetSource {

    private Object target;
    private Class<?> targetClass;
    private Class<?>[] targetInterfaces;

    public TargetSource(Object target, Class<?> targetClass, Class<?>[] targetInterfaces) {
        this.target = target;
        this.targetClass = targetClass;
        this.targetInterfaces = targetInterfaces;
    }

    public Object getTarget() {
        return target;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Class<?>[] getTargetInterfaces() {
        return targetInterfaces;
    }
}
