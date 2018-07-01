package aspect;

/**
 * 切点接口，定义了类型匹配与方法匹配
 * Created by MiaoZhuang on 2016/6/1.
 */
public interface Pointcut {

    MethodMatcher getMethodMatcher();

    ClassFilter getClassFilter();
}
