package pers.sunny.cmsservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-06-20:41
 */
@SpringBootApplication
@ComponentScan({"pers.sunny"})
@MapperScan("pers.sunny.cmsservice.mapper")
public class CmsApplication8004 {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication8004.class,args);
    }
}
