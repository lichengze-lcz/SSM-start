package com.lcz.ssm.controller;

import com.lcz.ssm.domain.SysLog;
import com.lcz.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint; //#aspectj
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component  //日志
@Aspect     //切面
public class LogAop {

    @Autowired
    private ISysLogService sysLogService;

    @Autowired
    private HttpServletRequest request;  //request 用于获取请求的IP地址

    private Date visitTime;//访问开始时间
    private Class clazz;//访问的类
    private Method method;//访问的方法


    //前置通知，主要获取开始时间，执行的类是哪一个，执行的是哪一个方法
    @Before("execution(* com.lcz.ssm.controller.*.*(..))")  //拦截了当前 Controller 下所有的方法
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {

        visitTime = new Date();//获取开始访问的时间
        clazz = jp.getTarget().getClass();//获取当前具体访问的类
        String methodName = jp.getSignature().getName();//获取访问当前方法的名字

        Object[] args = jp.getArgs(); //#获取访问方法的参数

        //通过方法名--获取了具体执行方法的Method对象
        if (args==null || args.length == 0){  //该方法无参
            method=clazz.getMethod(methodName); //通过方法名获取方法（只能是无参的）
        }else{
            //用于 方法的参数， getMethod第二个参数是Class数组
            Class[] classArgs = new Class[args.length];
            for(int i = 0; i < args.length;i++){
                classArgs[i] = args[i].getClass();//把每一个方法参数拿到，找到classArgs数组里面
            }
            clazz.getMethod(methodName,classArgs);
        }

    }


    //后置通知
    @After("execution(* com.lcz.ssm.controller.*.*(..))")  //拦截了当前 Controller 下所有的方法
    public void doAfter(JoinPoint jp) throws Exception {

        //#获取访问时长   总时长=后置时间-前置时间
        long time = new Date().getTime()-visitTime.getTime();


        //#获取访问的url
        String url="";
        //操作类不为空      操作方法不为空      ?（LogApp是当前操作日志类）
        if(clazz != null && method != null && clazz != LogAop.class){
            //获取类上的@RequestMapper(v"/xxx")
                                                                   //获取注解
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation != null){
                String[] clazzvalue = clazzAnnotation.value();
                //获取方法上的@RequestMapper(v"/xxx")
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null){
                    String[] methodvalue = methodAnnotation.value();
                    //组合
                    url = clazzvalue[0]+methodvalue[0];


                   //#获取访问的 IP地址  （反射）
                    String ip = request.getRemoteAddr();

                    //#获取当前操作者
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取了当前登录的用户
                    User user = (User) context.getAuthentication().getPrincipal(); //Security里面的User
                    String username = user.getUsername();

                    //将日志相关信息封装到SysLog 对象里
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名] "+clazz.getName()+"[方法名 ]"+method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);

                    //调用Service业务层，完成日志记录操作
                    sysLogService.save(sysLog);


                }
            }
        }

    }
}
