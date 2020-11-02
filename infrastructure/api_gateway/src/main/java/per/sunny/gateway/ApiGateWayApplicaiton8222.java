package per.sunny.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-21-18:32
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGateWayApplicaiton8222 {
    public static void main(String[] args) {
        SpringApplication.run(ApiGateWayApplicaiton8222.class,args);
    }
}
