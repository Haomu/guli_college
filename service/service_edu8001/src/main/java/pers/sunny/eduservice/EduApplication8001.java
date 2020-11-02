package pers.sunny.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-09-23-21:34
 */
@SpringBootApplication
@ComponentScan(basePackages = {"pers.sunny"})
@EnableDiscoveryClient
@EnableFeignClients
public class EduApplication8001 {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication8001.class,args);
    }
}
