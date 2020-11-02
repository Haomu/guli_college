package pers.sunny.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import pers.sunny.commonutils.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-17-15:04
 */
@FeignClient(value = "service-ucenter")
@Component
public interface UcenterClient {

    /**
     * @Author Sunny
     * @Description  根据token获取用户信息
     * @Date 2020/10/17
     * @Param [request]
     * @return pers.sunny.commonutils.Result
     **/
    @GetMapping("/educenter/member/auth/getLoginInfo")
    Result getLoginInfo(HttpServletRequest request);

}
