package pers.sunny.staservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pers.sunny.commonutils.Result;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-18-17:28
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    /**
     * @Author Sunny
     * @Description  查询某一天注册人数
     * @Date 2020/10/18
     * @Param [day]
     * @return pers.sunny.commonutils.Result
     **/
    @GetMapping("/educenter/member/countRegister/{day}")
    Result countRegister(@PathVariable("day") String day);
}
