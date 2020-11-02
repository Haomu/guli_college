package pers.sunny.staservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-18-16:18
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan("pers.sunny")
@EnableScheduling
public class StaApplication8008 {
    public static void main(String[] args) {
        SpringApplication.run(StaApplication8008.class,args);
    }
}
