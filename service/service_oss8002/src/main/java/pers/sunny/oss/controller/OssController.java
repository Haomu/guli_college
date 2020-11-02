package pers.sunny.oss.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pers.sunny.oss.service.OssService;
import pers.sunny.commonutils.Result;

import javax.annotation.Resource;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-09-28-18:39
 */
@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {
    @Resource
    private OssService ossService;

    /**
     * @Author Sunny
     * @Description  上传头像方法
     * @Date 2020/9/28
     * @Param
     * @return
     **/
    @ApiOperation("头像上传")
    @PostMapping("/avatar")
    public Result uploadOssFile(MultipartFile file){
        //获取上传文件 MultipartFile
        String url = ossService.uploadFileAvatar(file);

        return Result.ok().data("url",url);
    }
}
