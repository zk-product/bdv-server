package cn.piesat.biserver.common.handler;

import cn.piesat.biserver.common.MyException;
import cn.piesat.biserver.common.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一异常处理
 * @author zk
 * @date 2019/8/28 15:22
 */
@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(MyException.class)
    public Object handleException(MyException e, HttpServletRequest request, HttpServletResponse response){
        Response result = Response.getInstance();
        result.setOk(10000, null, "失败！",  e.getMap());
        return result;
    }
}
