package pers.sunny.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.sunny.commonutils.Result;
import pers.sunny.servicebase.exception.GuliException;
import pers.sunny.vod.service.VodService;
import pers.sunny.vod.utils.ConstPropertiesUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-03-13:45
 */
@RestController
@RequestMapping("/eduvod/video")
public class VodController {
    @Resource
    private VodService vodService;

    @PostMapping("/uploadVideo")
    public Result uploadVideo(MultipartFile file){
        String videoId = vodService.uploadVideo(file);
        return Result.ok().data("videoId",videoId);
    }
    @DeleteMapping("/{videoId}")
    public Result deleteVideo(@PathVariable String videoId){
        vodService.deleteVideo(videoId);
        return Result.ok();
    }

    @DeleteMapping("/batch")
    public Result deleteBatchVideo(@RequestParam("videoIds") List<String> videoIds){
        vodService.deleteBatchVideo(videoIds);
        return Result.ok();
    }

    //根据视频id获取凭证
    @GetMapping("/getPlayAuth/{vid}")
    public Result getPlayAuth(@PathVariable String vid){
        String playAuth = vodService.getAuth(vid);
        return Result.ok().data("playAuth",playAuth);
    }
}
