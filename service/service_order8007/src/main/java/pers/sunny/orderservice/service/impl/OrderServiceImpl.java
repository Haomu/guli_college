package pers.sunny.orderservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import pers.sunny.commonutils.OrderNoUtil;
import pers.sunny.commonutils.ordervo.CourseWebOrderVo;
import pers.sunny.commonutils.ordervo.UcenterMemberOrderVo;
import pers.sunny.orderservice.client.CourseClient;
import pers.sunny.orderservice.client.UcenterClient;
import pers.sunny.orderservice.entity.Order;
import pers.sunny.orderservice.mapper.OrderMapper;
import pers.sunny.orderservice.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author sunny
 * @since 2020-10-17
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private UcenterClient ucenterClient;
    @Resource
    private CourseClient courseClient;
    @Resource
    private OrderMapper orderMapper;

    /**
     * @Author Sunny
     * @Description  生成订单并返回订单号
     * @Date 2020/10/17
     * @Param [courseId, memberId]
     * @return java.lang.String
     **/
    @Override
    public String saveOrder(String courseId, String memberId) {
        //远程调用课程服务，根据课程id获取课程信息
        CourseWebOrderVo courseDto = courseClient.getCourseInfo(courseId);
        //远程调用用户服务，根据用户id获取用户信息
        UcenterMemberOrderVo ucenter = ucenterClient.getUserInfo(memberId);

        //创建订单
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseDto.getTitle());
        order.setCourseCover(courseDto.getCover());
        order.setTeacherName("test");
        order.setTotalFee(courseDto.getPrice());
        order.setMemberId(memberId);
        order.setMobile(ucenter.getMobile());
        order.setNickname(ucenter.getNickname());
        order.setStatus(0);
        order.setPayType(1);

        orderMapper.insert(order);

        return order.getOrderNo();
    }

    @Override
    public Order getOrder(String orderId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderId);

        return orderMapper.selectOne(queryWrapper);
    }

    @Override
    public Boolean isBuyCourse(String courseId, String memberId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId)
                .eq("member_id",memberId)
                .eq("status",1);//1代表已经支付
        Integer count = orderMapper.selectCount(queryWrapper);
        //已经支付
        return count > 0;
    }
}
