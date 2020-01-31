package cn.piesat.biserver.common.handler;

import cn.piesat.biserver.common.MyException;
import cn.piesat.biserver.common.Response;
import com.alibaba.druid.wall.Violation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * 拦截controller层请求参数
 * 用于参数校验
 * @author zk
 * @date 2019/8/28 17:00
 */
@Aspect
@Component
public class ValidHandler {

    @Before("execution(* cn.piesat.biserver.controller..*(..))")
    public void doBefore(JoinPoint joinPoint) {
        for (Object o : joinPoint.getArgs()) {
            if (o instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) o;
                validParams(bindingResult);
            }
        }
    }
    public void validParams(BindingResult bindingResult) {
        Map<String,String> map = new HashMap<>();
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                String key = error.getField();
                String val = error.getDefaultMessage();
                map.put(key,val);
            }
            throw new MyException(map);
        }
    }
}
