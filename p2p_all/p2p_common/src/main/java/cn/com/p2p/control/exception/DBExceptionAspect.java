package cn.com.p2p.control.exception;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.com.p2p.framework.exception.DBAccessException;

@Aspect
@Component
public class DBExceptionAspect {
	private final static Logger logger = LoggerFactory.getLogger(DBExceptionAspect.class);
    /**
     * 切入点：表示在哪个类的哪个方法进行切入。配置有切入点表达式
     */
    @Pointcut("execution(* cn.com.p2p.domain.*.*(..))")
    public void pointcutExpression() {
         
    }

    /**
     * 异常通知
     * 
     * 在目标方法出现异常时会执行的代码.
     * 可以访问到异常对象; 且可以指定在出现特定异常时在执行通知代码
     * 
     * @param joinPoint
     * @param e
     * @throws Exception 
     */
    @AfterThrowing(value = "pointcutExpression()", throwing = "e")
    public void afterThrowingMethod(JoinPoint joinPoint, Exception e) throws Exception {
    	//log
    	logger.error("DB访问出现异常 ：" + e.getMessage());
        
        throw new DBAccessException("DCE001");
    }

}
