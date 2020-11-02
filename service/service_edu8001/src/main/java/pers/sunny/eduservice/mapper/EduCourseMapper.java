package pers.sunny.eduservice.mapper;

import pers.sunny.eduservice.entity.pojo.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.sunny.eduservice.entity.vo.CoursePublishVo;
import pers.sunny.eduservice.entity.vo.CourseWebVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author sunny
 * @since 2020-09-29
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishVo getPublishCourseInfo(String courseId);

    CourseWebVo getFrontCourseInfo(String courseId);
}
