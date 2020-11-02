package pers.sunny.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.sunny.eduservice.entity.pojo.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.sunny.eduservice.entity.vo.TeacherQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author sunny
 * @since 2020-09-23
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Page<EduTeacher> pageTeachers(Long current,Long limit);

    Page<EduTeacher> pageTeachersCondition(Long current, Long limit, TeacherQuery teacherQuery);

    List<EduTeacher> listTeachers();

    Map<String, Object> pageFrontTeachers(Long current, Long limit);

    EduTeacher getTeacherById(String id);
}
