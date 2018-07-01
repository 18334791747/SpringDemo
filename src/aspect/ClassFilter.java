package aspect;

/**
 * 类匹配接口，判断指定类的对象是否与expression切点表达式匹配
 * Created by Lfc on 2017/6/1.
 */
public interface ClassFilter {

    boolean matches(Class clazz);
}
