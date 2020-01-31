package cn.piesat.biserver.common.filter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决跨域
 * @author: zk
 * @date 2018/10/20 14:03
 */
@Component
public class HttpHeaderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        /* 允许请求的外域 */
        res.setHeader("Access-Control-Allow-Origin",
                req.getHeader("Origin"));
        res.setHeader("Access-Control-Allow-Credentials", "true");

        /* 允许客户端携带的请求头 */
        res.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept");

        /* 服务器允许浏览器访问的请求头 */
        res.setHeader("Access-Control-Expose-Headers", "*");

        /* 同一请求的有效时间 */
        res.setHeader("Access-Control-Max-Age", "3600");

        /* 允许的请求方式 */
        res.setHeader("Access-Control-Allow-Methods",
                "POST, GET, OPTIONS, DELETE,PUT,HEAD");
        chain.doFilter(request, response);

    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if(request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            response.setStatus(HttpStatus.OK.value());
            return false;
        }
        return true;
    }
    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }
}
