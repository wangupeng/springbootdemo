package com.cars.plat.common.aop;

import com.cars.plat.sys.model.SysLog;
import com.cars.plat.sys.model.SysUser;
import com.cars.plat.util.string.IPUtil;
import com.cars.plat.util.string.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by wangyupeng on 2018/4/3 18:08
 */
@Aspect
@Component
public class LogAspect {
    ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 定义一个切入点.
     * 解释下：
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 任意包名
     * ~ 第三个 * 代表任意方法.
     * ~ 第四个 * 定义在web包或者子包
     * ~ 第五个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */
    @Pointcut("execution(public * com..controller..*.*(..))")
    public void webLog(){}

    /**
     * 管理员登录方法的切入点
     */
    @Pointcut("execution(* com.cars.plat.sys.controller.*.login(..))")
    public void login(){
    }

    /**
     * 添加业务逻辑方法切入点
     *//*
    @Pointcut("execution(* com.yangjf.service.*.save(..))")
    public void insertCell() {
    }

    *//**
     * 修改业务逻辑方法切入点
     *//*
    @Pointcut("execution(* com.yangjf.service.*.update(..))")
    public void updateCell() {
    }

    *//**
     * 删除业务逻辑方法切入点
     *//*
    @Pointcut("execution(* com.yangjf.service.*.delete(..))")
    public void deleteCell() {
    }*/



    @Before(value = "login()")
    public void doBefore(JoinPoint joinPoint){
        startTime.set(System.currentTimeMillis());
    }

    /**
     * 登录操作(后置通知)
     * @param joinPoint
     * @throws Throwable
     */
    @AfterReturning(value = "login()")
    public void loginLog(JoinPoint joinPoint) throws Throwable {
        sendToMq(joinPoint);
    }


    /**
     * 将信息防撞发送到mq
     * @param joinPoint
     */
    private void sendToMq(JoinPoint joinPoint){
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("userSession");
        if(sysUser!=null){
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            SysLog sysLog = new SysLog();
            sysLog.setLogId(StringUtil.uuid());
            sysLog.setUserName(sysUser.getUserName());
            sysLog.setOperaIp(IPUtil.getIp());
            sysLog.setOperaUrl(request.getRequestURL().toString());
            sysLog.setOperaDate(new Date());
            sysLog.setMethodName(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            sysLog.setDealTime(System.currentTimeMillis() - startTime.get());
            rabbitTemplate.convertAndSend("sysLogQueue",sysLog);
        }
    }







    //    @Before("@annotation(UserAccessAnnotation)")
    /*@Before("login()")
    public void doBefore(JoinPoint joinPoint){
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
//        logger.info("URL : " + request.getRequestURL().toString());
//        logger.info("HTTP_METHOD : " + request.getMethod());
//        logger.info("IP : " + request.getRemoteAddr());
//        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        System.out.println("URL : " + request.getRequestURL().toString());
        System.out.println("HTTP_METHOD : " + request.getMethod());
        System.out.println("IP : " + request.getRemoteAddr());
        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        //获取所有参数方法一：
        Enumeration<String> enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            System.out.println(paraName+": "+request.getParameter(paraName));
        }
    }*/
    /*@AfterReturning("login()")
    public void  doAfterReturning(JoinPoint joinPoint){
        // 处理完请求，返回内容
//        logger.info("WebLogAspect.doAfterReturning()");
        System.out.println("WebLogAspect.doAfterReturning()");
        System.out.println(System.currentTimeMillis() - startTime.get());
    }*/





    /**
     * 使用Java反射来获取被拦截方法(insert、update)的参数值， 将参数值拼接为操作内容
     * String methodName = joinPoint.getSignature().getName();
     * @param args
     * @param mName
     * @return
     */
    public String optionContent(Object[] args, String mName) {
        if (args == null) {
            return null;
        }
        StringBuffer rs = new StringBuffer();
        rs.append(mName);
        String className = null;
        int index = 1;
        // 遍历参数对象
        for (Object info : args) {
            // 获取对象类型
            className = info.getClass().getName();
            className = className.substring(className.lastIndexOf(".") + 1);
            rs.append("[参数" + index + "，类型:" + className + "，值:");
            // 获取对象的所有方法
            Method[] methods = info.getClass().getDeclaredMethods();
            // 遍历方法，判断get方法
            for (Method method : methods) {
                String methodName = method.getName();
                // 判断是不是get方法
                if (methodName.indexOf("get") == -1) {// 不是get方法
                    continue;// 不处理
                }
                Object rsValue = null;
                try {
                    // 调用get方法，获取返回值
                    rsValue = method.invoke(info);
                } catch (Exception e) {
                    continue;
                }
                // 将值加入内容中
                rs.append("(" + methodName + ":" + rsValue + ")");
            }
            rs.append("]");
            index++;
        }
        return rs.toString();
    }
}
