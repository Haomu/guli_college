package pers.sunny.msmservice.controller;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import pers.sunny.commonutils.RandomUtil;
import pers.sunny.commonutils.Result;
import pers.sunny.msmservice.service.MsmService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-09-19:26
 */
@RestController
@RequestMapping("/edumsm/msm")
public class MsmController {
    @Resource
    private MsmService msmService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping(value = "/send/{phone}")
    public Result code(@PathVariable String phone) {
        //1.从redis获取验证码，如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)){
            return Result.ok();
        }
        //2.如果redis获取不到，进行阿里云发送
        //生成随机值，传递阿里云进行发送
        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code", code);
        boolean isSend = msmService.send(phone, param);
        if(isSend) {
            //发送成功，把验证码放到redis中
            redisTemplate.opsForValue().set(phone, code,5,TimeUnit.MINUTES);
            return Result.ok();
        } else {
            return Result.error().message("发送短信失败");
        }
    }
}
