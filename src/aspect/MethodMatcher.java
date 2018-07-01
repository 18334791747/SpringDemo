package aspect;

import java.lang.reflect.Method;

/**
 * 方法匹配接口，用于判断制定方法是否与expression切点表达式匹配
 * Created by MiaoZhuang on 2016/6/1.
 */
public interface MethodMatcher {

    boolean matches(Method method, Class targetClass);
}
