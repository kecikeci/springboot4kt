package me.forxx.springboot4kt.aop;


import com.alibaba.fastjson.JSON;
import me.forxx.springboot4kt.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by GaoMingQiang on 2017/12/12 0019 11:19.
 */
@Component
@Aspect
public class Logs {

    private Logger log = LogManager.getLogger();
    @Autowired
    private HttpServletRequest res;

    @Before("execution(* me.forxx.springboot4kt.service..*(..)) || execution(* me.forxx.springboot4kt.web..*(..))")
    public void before(JoinPoint join){
        StringBuffer userName = new StringBuffer("游客");
        String ip = Util.getIp(res);
        String className = join.getTarget().getClass().getName();
        String function = join.getSignature().getName();
        String args = Arrays.asList(join.getArgs()).toString();
        StringBuffer msg = new StringBuffer();
        msg.append("当前用户 【");
        msg.append(userName);
        msg.append("】 IP 【");
        msg.append(ip);
        msg.append("】 正在访问 【类：<");
        msg.append(className);
        msg.append(">，方法：<");
        msg.append(function);
        msg.append(">，参数：<");
        msg.append(args);
        msg.append(">】");
        log.info(msg);
    }

    @AfterThrowing(pointcut="execution(* me.forxx.springboot4kt.service..*(..)) || execution(* me.forxx.springboot4kt.web..*(..))",throwing="e")
    public void exception(JoinPoint join, Exception e) {
        StringBuffer userName = new StringBuffer("游客");
        String ip = Util.getIp(res);
        String className = join.getTarget().getClass().getName();
        String function = join.getSignature().getName();
        String args = Arrays.asList(join.getArgs()).toString();
        StringBuffer msg = new StringBuffer();
        //记录报错内容
        StackTraceElement[] elems = e.getStackTrace();
        String text = "\n";
        for(StackTraceElement elem : elems) {
            text +=  "\t"+elem.toString()+"\n";
        }
        msg.append("当前用户 【");
        msg.append(userName);
        msg.append("】 IP 【");
        msg.append(ip);
        msg.append("】 正在访问 【类：<");
        msg.append(className);
        msg.append(">，方法：<");
        msg.append(function);
        msg.append(">，参数：<");
        msg.append(args);
        msg.append(">】 出现异常：【");
        msg.append(e.toString());
        msg.append("】 \n\t >> ------------------ 异常详情 -----------------<< \n");
        msg.append(text);
        log.error(msg);

        StringBuffer msgs = new StringBuffer();
        msgs.append("当前用户 【");
        msgs.append(userName);
        msgs.append("】 IP 【");
        msgs.append(ip);
        msgs.append("】 正在访问 【类：<");
        msgs.append(className);
        msgs.append(">，方法：<");
        msgs.append(function);
        msgs.append(">，参数：<");
        msgs.append(args);
        msgs.append(">】 出现异常：【");
        msgs.append(e.toString());
        msgs.append("】");
        log.info(msgs);
    }

    @AfterReturning(returning = "result",pointcut = "execution(* me.forxx.springboot4kt.service..*(..)) || execution(* me.forxx.springboot4kt.web..*(..))")
    public void After(JoinPoint join, Object result){
        StringBuffer userName = new StringBuffer("游客");
        String ip = Util.getIp(res);
        String className = join.getTarget().getClass().getName();
        String function = join.getSignature().getName();
        String args = JSON.toJSONString(result);
        StringBuffer msg = new StringBuffer();
        msg.append("当前用户 【");
        msg.append(userName);
        msg.append("】 IP 【");
        msg.append(ip);
        msg.append("】 返回结果 【类：<");
        msg.append(className);
        msg.append(">，方法：<");
        msg.append(function);
        msg.append(">，结果：<");
        msg.append(args);
        msg.append(">】");
        log.info(msg);
    }

}
