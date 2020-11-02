package pers.sunny.eduservice.controller.front;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.sunny.commonutils.Result;
import pers.sunny.eduservice.entity.pojo.EduCourse;
import pers.sunny.eduservice.entity.pojo.EduTeacher;
import pers.sunny.eduservice.service.EduCourseService;
import pers.sunny.eduservice.service.EduTeacherService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-07-15:00
 */
@RestController
@RequestMapping("/eduservice/index")
@Api(tags = "前台首页数据")
public class IndexFrontController {

    @Resource
    private EduCourseService eduCourseService;
    @Resource
    private EduTeacherService eduTeacherService;

    /**
     * @return pers.sunny.commonutils.Result
     * @Author Sunny
     * @Description 查询前8条热门课程，查询前4位名师
     * @Date 2020/10/7
     * @Param []
     **/
    @GetMapping("/")
    public Result index() {
        List<EduCourse> courses = eduCourseService.listCourse();

        List<EduTeacher> teachers = eduTeacherService.listTeachers();

        return Result.ok().data("courses",courses).data("teachers",teachers);
    }
}
