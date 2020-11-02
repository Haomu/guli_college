package pers.sunny.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-09-20:33
 */
@SpringBootApplication
@ComponentScan({"pers.sunny"})
@MapperScan("pers.sunny.educenter.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class UcenterApplication8150 {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication8150.class,args);
    }
}
