package cn.com.p2p.control.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationLogAspect {
	private final static Logger logger = LoggerFactory.getLogger(ApplicationLogAspect.class);
    /**
     * 切入点：表示在哪个类的哪个方法进行切入。配置有切入点表达式
     */
    @Pointcut("execution(* cn.com.p2p..service.*.*(..))")
    public void pointcutExpression() {
         
    }
     
    /**
     * 1 前置通知
     * @param joinPoint
     */
    @Before("pointcutExpression()")
    public void beforeMethod(JoinPoint joinPoint) {
    	joinPoint.getArgs();
    	String className = joinPoint.getTarget().getClass().getName();
    	String methodName = joinPoint.getSignature().getName();
    	logger.info("执行方法开始: " + className + ": " + methodName);
        String params = "";
        for (Object arg : joinPoint.getArgs()) {
        	params = params + "  " + arg;
        }
        logger.debug("执行方法参数: " + params);
    }
     
    /**
     * 2 后置通知
     */
    @After("pointcutExpression()") // 在方法执行之后执行的代码. 无论该方法是否出现异常
    public void afterMethod(JoinPoint joinPoint) {
    	
    	String className = joinPoint.getTarget().getClass().getName();
    	String methodName = joinPoint.getSignature().getName();
    	logger.info("执行方法结束: " + className + ": " + methodName);
    }
     
    /**
     * 3 返回通知
     * 
     * 在方法法正常结束受执行的代码
     * 返回通知是可以访问到方法的返回值的!
     * 
     * @param joinPoint
     * @param returnValue
     * 
     */
    @AfterReturning(value = "pointcutExpression()", returning = "returnValue")
    public void afterRunningMethod(JoinPoint joinPoint, Object returnValue) {
    	String className = joinPoint.getTarget().getClass().getName();
    	String methodName = joinPoint.getSignature().getName();
    	
        logger.debug("返回通知执行结果: " + className + ":" + methodName + ":" + returnValue);
    }
     

}
