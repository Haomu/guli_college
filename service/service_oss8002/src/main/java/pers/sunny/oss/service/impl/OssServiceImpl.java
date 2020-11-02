package pers.sunny.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pers.sunny.oss.service.OssService;
import pers.sunny.oss.utils.ConstantPropertiesUtils;


import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-09-28-17:25
 */
@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 获取上传文件输入流。
            InputStream inputStream = file.getInputStream();

            //获取文件名称,将其转为唯一值
            String name = file.getOriginalFilename();
            String extention = name.substring(name.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString().replace("-","") + extention;

            //把文件按照日期分类
            String datePath = new DateTime().toString("yyyy/MM/dd");

            //拼接 2020/09/28/01.jpg
            fileName = datePath + "/" + fileName;

            //第一个参数  Bucket名称
            //第二个参数  上传到oss文件路径和名称  /aa/bb/1.jpg
            //第三个参数  上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //把上传之后文件路径返回
            return "https://" + bucketName + "." + endpoint + "/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
