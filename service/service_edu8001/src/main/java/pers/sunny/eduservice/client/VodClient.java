package pers.sunny.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pers.sunny.commonutils.Result;

import java.util.List;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-04-18:15
 */
@FeignClient(value = "service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {

    /**
     * @Author Sunny
     * @Description  根据id删除阿里云视频
     * @Date 2020/10/4
     * @Param [videoId]
     * @return pers.sunny.commonutils.Result
     **/
    @DeleteMapping("/eduvod/video/{videoId}")
    Result deleteVideo(@PathVariable("videoId") String videoId);

    /**
     * @Author Sunny
     * @Description  根据多个id删除阿里云视频
     * @Date 2020/10/4
     * @Param [videoIds]
     * @return pers.sunny.commonutils.Result
     **/
    @DeleteMapping("/eduvod/video/batch")
    Result deleteBatchVideo(@RequestParam("videoIds") List<String> videoIds);
}
