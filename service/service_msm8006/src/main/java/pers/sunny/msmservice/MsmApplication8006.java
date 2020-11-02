package pers.sunny.msmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-09-19:16
 */
@SpringBootApplication
@ComponentScan({"pers.sunny"})
public class MsmApplication8006 {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication8006.class,args);
    }
}
