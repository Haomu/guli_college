package pers.sunny.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-09-28-17:25
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
