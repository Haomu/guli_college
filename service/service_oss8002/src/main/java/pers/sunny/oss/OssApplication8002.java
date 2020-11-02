package pers.sunny.oss;


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
 * @Date 2020-09-28-16:35
 */
@SpringBootApplication
@ComponentScan(basePackages = {"pers.sunny"})
@EnableDiscoveryClient
@EnableFeignClients
public class OssApplication8002 {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication8002.class,args);
    }
}
