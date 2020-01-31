package cn.piesat.biserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zk
 * @date 2018/8/28 09:21
 */
@SpringBootApplication
@MapperScan("cn.piesat.biserver.dao")
@EnableAspectJAutoProxy(exposeProxy = true)
public class BiServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiServerApplication.class, args);
    }

}
