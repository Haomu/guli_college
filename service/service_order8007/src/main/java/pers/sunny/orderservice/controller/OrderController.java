package pers.sunny.orderservice.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import pers.sunny.commonutils.JwtUtils;
import pers.sunny.commonutils.Result;
import pers.sunny.orderservice.entity.Order;
import pers.sunny.orderservice.service.OrderService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author sunny
 * @since 2020-10-17
 */
@RestController
@RequestMapping("/orderservice/order")
@Api(tags = "订单管理")
public class OrderController {
    @Resource
    private OrderService orderService;

    /**
     * @Author Sunny
     * @Description  根据课程id和用户id创建订单，并返回订单id
     * @Date 2020/10/17
     * @Param [courseId, request]
     * @return pers.sunny.commonutils.Result
     **/
    @PostMapping("/{courseId}")
    @ApiOperation("创建订单")
    public Result saveOrder(@PathVariable String courseId, HttpServletRequest request){
        String orderId = orderService.saveOrder(courseId, JwtUtils.getMemberIdByJwtToken(request));

        return Result.ok().data("orderId",orderId);
    }

    @GetMapping("/{orderId}")
    @ApiOperation("获取订单")
    public Result getOrder(@PathVariable String orderId) {
        Order order = orderService.getOrder(orderId);
        return Result.ok().data("item",order);
    }

    @GetMapping("/isBuyCourse/{courseId}/{memberId}")
    @ApiOperation("查询订单状态")
    public Boolean isBuyCourse(@PathVariable("courseId") String courseId,@PathVariable("memberId") String memberId) {
        return orderService.isBuyCourse(courseId,memberId);
    }
}

