package pers.sunny.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-18-15:23
 */
@Component
@FeignClient(value = "service-order")
public interface OrderClient {

    @GetMapping("/orderservice/order/isBuyCourse/{courseId}/{memberId}")
    Boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);
}
