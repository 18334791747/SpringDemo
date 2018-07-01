package aspect;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * 切面，包含了两个元素，Advice（通知）与PointCut（切点），通Advice用MethodInterceptor替代
 * Created by MiaoZhuang on 2016/6/2.
 */
public class AspectJExpressionPointcutAdvisor {

    private MethodInterceptor methodInterceptor;

    private String expression;

    private AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();


    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public void setExpression(String expression) {
        this.expression = expression;
        aspectJExpressionPointcut.setExpression(this.expression);
    }

    public AspectJExpressionPointcut getAspectJExpressionPointcut() {
        return this.aspectJExpressionPointcut;
    }
}
