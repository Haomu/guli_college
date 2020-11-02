package pers.sunny.eduservice.controller;


import io.swagger.annotations.Api;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import pers.sunny.commonutils.Result;
import pers.sunny.eduservice.client.VodClient;
import pers.sunny.eduservice.entity.pojo.EduVideo;
import pers.sunny.eduservice.mapper.EduVideoMapper;
import pers.sunny.eduservice.service.EduVideoService;

import javax.annotation.Resource;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author sunny
 * @since 2020-09-29
 */
@RestController
@RequestMapping("/eduservice/video")
@Api(tags = "课程视频")
public class EduVideoController {

    @Resource
    private EduVideoService eduVideoService;

    @Resource
    private VodClient vodClient;

    /**
     * @Author Sunny
     * @Description  添加小节
     * @Date 2020/10/2
     * @Param [eduVideo]
     * @return pers.sunny.commonutils.Result
     **/
    @PostMapping("/")
    public Result insertVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return Result.ok();
    }
    /**
     * @Author Sunny
     * @Description  删除小节
     * @Date 2020/10/2
     * @Param [videoId]
     * @return pers.sunny.commonutils.Result
     **/
    @DeleteMapping("/{videoId}")
    public Result deleteVideo(@PathVariable String videoId){
        //根据小节id获取视频id
        EduVideo eduVideo = eduVideoService.getById(videoId);
        String videoSourceId = eduVideo.getVideoSourceId();
        //删除视频
        if (!StringUtils.isEmpty(videoSourceId)){
            vodClient.deleteVideo(videoSourceId);
        }
        //删除小节
        eduVideoService.removeById(videoId);
        return Result.ok();
    }

    /**
     * @Author Sunny
     * @Description  修改小节
     * @Date 2020/10/2
     * @Param [videoId]
     * @return pers.sunny.commonutils.Result
     **/
    @PutMapping("/")
    public Result updateVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.updateById(eduVideo);
        return Result.ok();
    }

    @GetMapping("/{videoId}")
    public Result getVideo(@PathVariable String videoId){
        EduVideo video = eduVideoService.getById(videoId);
        return Result.ok().data("video",video);
    }

}

