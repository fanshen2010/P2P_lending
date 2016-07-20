package cn.com.p2p.control.exception;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.com.p2p.framework.exception.BusinessException;
import cn.com.p2p.framework.exception.DBAccessException;
import cn.com.p2p.framework.exception.SystemException;
//import cn.com.p2p.workflow.exception.WfException;

@Aspect
@Component
public class SysExceptionAspect {
	private final static Logger logger = LoggerFactory.getLogger(SysExceptionAspect.class);
    /**
     * 切入点：表示在哪个类的哪个方法进行切入。配置有切入点表达式
     */
    @Pointcut("execution(* cn.com.p2p..service.*.*(..))")
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
    	logger.error("Business Part Error：" + e.getMessage());
        if (e instanceof BusinessException
        		|| e instanceof DBAccessException
//        		|| e instanceof WfException
        		) {
        	throw e;
        } else {
        	throw new SystemException("SCE001");
        }
    }

}
