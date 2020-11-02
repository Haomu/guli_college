package pers.sunny.eduservice.service;

import pers.sunny.eduservice.entity.pojo.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author sunny
 * @since 2020-09-29
 */
public interface EduVideoService extends IService<EduVideo> {

    void deleteVideoByCourseId(String id);
}
