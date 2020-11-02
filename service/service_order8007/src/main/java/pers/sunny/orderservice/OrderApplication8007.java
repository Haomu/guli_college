package pers.sunny.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-17-16:46
 */
@SpringBootApplication
@ComponentScan(basePackages = {"pers.sunny"})
@EnableDiscoveryClient
@EnableFeignClients
public class OrderApplication8007 {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication8007.class,args);
    }
}
