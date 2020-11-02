package pers.sunny.staservice.utils;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pers.sunny.staservice.service.StatisticsDailyService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-18-17:57
 */
@Component
public class ScheduledTask {

    @Resource
    private StatisticsDailyService dailyService;

    /**
     * 每天凌晨1点执行定时
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void task2() {
        //获取上一天的日期
        String day = DateUtil.formatDate(DateUtil.addDays(new Date(), -1));
        dailyService.createStatisticsByDay(day);

    }
}
