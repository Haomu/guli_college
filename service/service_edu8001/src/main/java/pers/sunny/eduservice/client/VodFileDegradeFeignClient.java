package pers.sunny.eduservice.client;

import org.springframework.stereotype.Component;
import pers.sunny.commonutils.Result;

import java.util.List;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-04-21:40
 */
@Component
public class VodFileDegradeFeignClient implements VodClient{
    /**
     * @param videoId
     * @return pers.sunny.commonutils.Result
     * @Author Sunny
     * @Description 根据id删除阿里云视频
     * @Date 2020/10/4
     * @Param [videoId]
     */
    @Override
    public Result deleteVideo(String videoId) {
        return Result.error().message("删除视频出错了！");
    }

    /**
     * @param videoIds
     * @return pers.sunny.commonutils.Result
     * @Author Sunny
     * @Description 根据多个id删除阿里云视频
     * @Date 2020/10/4
     * @Param [videoIds]
     */
    @Override
    public Result deleteBatchVideo(List<String> videoIds) {
        return Result.error().message("删除多个视频出错了！");
    }
}
