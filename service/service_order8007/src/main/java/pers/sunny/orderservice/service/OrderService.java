package pers.sunny.orderservice.service;

import pers.sunny.orderservice.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author sunny
 * @since 2020-10-17
 */
public interface OrderService extends IService<Order> {
    /**
     * @Author Sunny
     * @Description  生成订单并返回订单号
     * @Date 2020/10/17
     * @Param [courseId, memberId]
     * @return java.lang.String
     **/
    String saveOrder(String courseId, String memberId);

    Order getOrder(String orderId);

    Boolean isBuyCourse(String courseId, String memberId);
}
