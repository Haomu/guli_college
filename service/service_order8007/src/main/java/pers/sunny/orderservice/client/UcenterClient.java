package pers.sunny.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pers.sunny.commonutils.ordervo.UcenterMemberOrderVo;


/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-17-15:04
 */
@FeignClient(value = "service-ucenter")
@Component
public interface UcenterClient {

    @GetMapping("/educenter/member/getUserInfoOrder/{id}")
    UcenterMemberOrderVo getUserInfo(@PathVariable("id") String id);
}
