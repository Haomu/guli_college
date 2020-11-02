package pers.sunny.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pers.sunny.commonutils.Result;
import pers.sunny.servicebase.exception.GuliException;
import pers.sunny.vod.service.VodService;
import pers.sunny.vod.utils.ConstPropertiesUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-03-13:45
 */
@Service
public class VodServiceImpl implements VodService {

    @Override
    public String uploadVideo(MultipartFile file) {
        try {

            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0,fileName.lastIndexOf("."));
            InputStream is = file.getInputStream();

            UploadStreamRequest request = new UploadStreamRequest(ConstPropertiesUtils.KEY_ID, ConstPropertiesUtils.KEY_SECRET, title, fileName, is);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = null;
            if (response.isSuccess()){
                videoId = response.getVideoId();
            } else {
                System.out.println(response.getCode());
                System.out.println(response.getMessage());
            }
            return videoId;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteVideo(String videoId) {
        try {
            //初始化对象
            String regionId = "cn-shanghai";
            DefaultProfile profile = DefaultProfile.getProfile(regionId, ConstPropertiesUtils.KEY_ID, ConstPropertiesUtils.KEY_SECRET);
            DefaultAcsClient client = new DefaultAcsClient(profile);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(videoId);
            //调用初始化对象的删除方法
            client.getAcsResponse(request);
        } catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"删除视频失败");
        }
    }

    @Override
    public void deleteBatchVideo(List<String> videoIds) {
        try {
            //初始化对象
            String regionId = "cn-shanghai";
            DefaultProfile profile = DefaultProfile.getProfile(regionId, ConstPropertiesUtils.KEY_ID, ConstPropertiesUtils.KEY_SECRET);
            DefaultAcsClient client = new DefaultAcsClient(profile);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();

            String ids = StringUtils.join(videoIds.toArray(),",");
            //向request设置视频id
            request.setVideoIds(ids);
            //调用初始化对象的删除方法
            client.getAcsResponse(request);
        } catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"删除视频失败");
        }
    }

    @Override
    public String getAuth(String vid) {
        try {
            //初始化对象
            String regionId = "cn-shanghai";
            DefaultProfile profile = DefaultProfile.getProfile(regionId, ConstPropertiesUtils.KEY_ID, ConstPropertiesUtils.KEY_SECRET);
            DefaultAcsClient client = new DefaultAcsClient(profile);

            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(vid);
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);

            return response.getPlayAuth();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(20001,"获取凭证失败");
        }
    }
}
