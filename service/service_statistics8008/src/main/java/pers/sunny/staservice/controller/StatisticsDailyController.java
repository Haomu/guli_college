package pers.sunny.staservice.controller;



import org.springframework.web.bind.annotation.*;

import pers.sunny.commonutils.Result;
import pers.sunny.staservice.service.StatisticsDailyService;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author sunny
 * @since 2020-10-18
 */
@RestController
@RequestMapping("/staservice/daily")
public class StatisticsDailyController {
    @Resource
    private StatisticsDailyService dailyService;

    @PostMapping("/{day}")
    public Result createStatisticsByDate(@PathVariable String day) {
        dailyService.createStatisticsByDay(day);
        return Result.ok();
    }
    /**
     * @Author Sunny
     * @Description  图表显示，返回两部分数据，日期json数组，数量json数组
     * @Date 2020/10/21
     * @Param [begin, end, type]
     * @return pers.sunny.commonutils.Result
     **/
    @GetMapping("/showChart/{type}/{begin}/{end}")
    public Result showChart(@PathVariable String type,@PathVariable String begin,
                            @PathVariable String end) {
        Map<String,Object> map = dailyService.getShowData(type,begin,end);

        return Result.ok().data(map);
    }
}

