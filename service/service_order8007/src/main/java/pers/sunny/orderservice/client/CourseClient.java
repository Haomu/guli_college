package pers.sunny.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pers.sunny.commonutils.ordervo.CourseWebOrderVo;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-17-17:08
 */
@FeignClient(value = "service-edu")
@Component
public interface CourseClient {

    @GetMapping("/eduservice/coursefront/getDto/{courseId}")
    CourseWebOrderVo getCourseInfo(@PathVariable("courseId") String courseId);

    @GetMapping("/eduservice/coursefront/updateBuyCount/{courseId}")
    void updateBuyCount(@PathVariable("courseId") String courseId);
}
