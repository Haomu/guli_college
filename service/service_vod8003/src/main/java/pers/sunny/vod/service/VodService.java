package pers.sunny.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-03-13:45
 */
public interface VodService {
    String uploadVideo(MultipartFile file);

    void deleteVideo(String videoId);

    void deleteBatchVideo(List<String> videoIds);

    String getAuth(String vid);
}
