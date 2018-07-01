package aspect;


import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 切点，调用AspectJ的库函数对expression所代表的切点进行判断匹配
 * Created by MiaoZhuang on 2016/6/1.
 */
public class AspectJExpressionPointcut implements Pointcut, MethodMatcher, ClassFilter {

    //生成切点解析对象所需要的集合
    private static final Set<PointcutPrimitive> DEFAULT_SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();

    static {
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
    }

    //用户配置的切点
    private String expression;
    //AspectJ中对用户切点字符串进行解析的类
    private PointcutParser pointcutParser;
    //解析出的切点表达
    private PointcutExpression pointcutExpression;


    public AspectJExpressionPointcut() {
        this(DEFAULT_SUPPORTED_PRIMITIVES);
    }

    public AspectJExpressionPointcut(Set supportedPrimitives) {
        pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(supportedPrimitives);
    }

    /**
     * 对切点表达式进行注入
     *
     * @param expression
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * 判断切点表达式是否被解析
     */
    public void checkReadyToMatch() {
        if (pointcutExpression == null) {
            pointcutExpression = pointcutParser.parsePointcutExpression(expression);
        }
    }

    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }

    /**
     * 判断切点表达式与指定类是否匹配
     *
     * @param clazz
     * @return
     */
    @Override
    public boolean matches(Class clazz) {
        checkReadyToMatch();
        return pointcutExpression.couldMatchJoinPointsInType(clazz);
    }

    /**
     * 判断切点表达式与指定方法是否匹配
     *
     * @param method
     * @param targetClass
     * @return
     */
    @Override
    public boolean matches(Method method, Class targetClass) {
        checkReadyToMatch();
        ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
        if (shadowMatch.alwaysMatches()) {
            return true;
        } else if (shadowMatch.maybeMatches()) {
            return false;
        }
        return false;
    }
}
