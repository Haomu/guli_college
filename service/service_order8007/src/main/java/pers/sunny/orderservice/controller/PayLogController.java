package pers.sunny.orderservice.controller;


import org.springframework.web.bind.annotation.*;

import pers.sunny.commonutils.Result;
import pers.sunny.orderservice.service.PayLogService;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author sunny
 * @since 2020-10-17
 */
@RestController
@RequestMapping("/orderservice/pay")
public class PayLogController {
    @Resource
    private PayLogService payLogService;

    /**
     * @Author Sunny
     * @Description  生成二维码
     * @Date 2020/10/17
     * @Param [orderNo]
     * @return pers.sunny.commonutils.Result
     **/
    @GetMapping("/createNative/{orderNo}")
    public Result createNative(@PathVariable String orderNo){
        Map<String,Object> map = payLogService.createNative(orderNo);

        return Result.ok().data(map);
    }

    @GetMapping("/queryPayStatus/{orderNo}")
    public Result queryPayStatus(@PathVariable String orderNo){
        Map<String, String> map = payLogService.queryPayStatus(orderNo);
        if (map == null){
            return Result.error().message("支付出错");
        }
        if ("SUCCESS".equals(map.get("trade_state"))) {
            payLogService.updateOrderStatus(map);
            return Result.ok().message("支付成功");
        }
        return Result.ok().code(25000).message("支付中");
    }

}

