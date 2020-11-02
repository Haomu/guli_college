package pers.sunny.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.sunny.eduservice.entity.CourseQuery;
import pers.sunny.eduservice.entity.pojo.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.sunny.eduservice.entity.vo.CourseInfoVo;
import pers.sunny.eduservice.entity.vo.CoursePublishVo;
import pers.sunny.eduservice.entity.vo.CourseQueryVo;
import pers.sunny.eduservice.entity.vo.CourseWebVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author sunny
 * @since 2020-09-29
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * @Author Sunny
     * @Description  添加课程基本信息
     * @Date 2020/9/29
     * @Param [courseInfoVo]
     * @return void
     **/
    String insertCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfo);

    CoursePublishVo publishCourseInfo(String id);

    Page<EduCourse> pageCourseCondition(Long current, Long limit, CourseQuery courseQuery);

    void deleteById(String id);

    List<EduCourse> listCourse();

    List<EduCourse> getByTeacherId(String id);

    Map<String, Object> pageFrontCourses(Long current, Long limit, CourseQueryVo courseQuery);

    CourseWebVo getFrontCourseInfo(String courseId);

    void updatePageViewCount(String courseId);
}
