package pers.sunny.staservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.RandomUtils;
import pers.sunny.staservice.client.UcenterClient;
import pers.sunny.staservice.entity.StatisticsDaily;
import pers.sunny.staservice.mapper.StatisticsDailyMapper;
import pers.sunny.staservice.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author sunny
 * @since 2020-10-18
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
    @Resource
    private UcenterClient ucenterClient;
    @Resource
    private StatisticsDailyMapper statisticsDailyMapper;

    @Override
    public void createStatisticsByDay(String day) {
        //删除已存在的统计对象
        QueryWrapper<StatisticsDaily> dayQueryWrapper = new QueryWrapper<>();
        dayQueryWrapper.eq("date_calculated", day);
        statisticsDailyMapper.delete(dayQueryWrapper);


        //获取统计信息
        Integer registerNum = (Integer) ucenterClient.countRegister(day).getData().get("countRegister");
        Integer loginNum = RandomUtils.nextInt(100, 200);
        Integer videoViewNum = RandomUtils.nextInt(100, 200);
        Integer courseNum = RandomUtils.nextInt(100, 200);

        //创建统计对象
        StatisticsDaily daily = new StatisticsDaily();
        daily.setRegisterNum(registerNum);
        daily.setLoginNum(loginNum);
        daily.setVideoViewNum(videoViewNum);
        daily.setCourseNum(courseNum);
        daily.setDateCalculated(day);

        statisticsDailyMapper.insert(daily);
    }
    /**
     * @Author Sunny
     * @Description  图表显示，返回两部分数据，日期json数组，数量json数组
     * @Date 2020/10/21
     * @Param [type, begin, end]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("date_calculated",begin,end);
        queryWrapper.select(type,"date_calculated");

        List<StatisticsDaily> staList = statisticsDailyMapper.selectList(queryWrapper);

        List<String> dateList = new ArrayList<>();
        List<Integer> numDataList = new ArrayList<>();
        for (StatisticsDaily daily : staList) {
            dateList.add(daily.getDateCalculated());
            switch (type) {
                case "register_num": {
                    numDataList.add(daily.getRegisterNum());
                    break;
                }
                case "login_num": {
                    numDataList.add(daily.getLoginNum());
                    break;
                }
                case "video_view_num": {
                    numDataList.add(daily.getVideoViewNum());
                    break;
                }
                case "course_num": {
                    numDataList.add(daily.getCourseNum());
                    break;
                }
                default:
                    break;
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("dateList",dateList);
        map.put("numDataList",numDataList);

        return map;
    }
}
