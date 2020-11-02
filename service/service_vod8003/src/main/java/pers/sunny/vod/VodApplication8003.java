package pers.sunny.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-03-13:40
 */
@SpringBootApplication
@ComponentScan(basePackages = {"pers.sunny"})
@EnableDiscoveryClient
@EnableFeignClients
public class VodApplication8003 {
    public static void main(String[] args) {
        SpringApplication.run(VodApplication8003.class,args);
    }
}
