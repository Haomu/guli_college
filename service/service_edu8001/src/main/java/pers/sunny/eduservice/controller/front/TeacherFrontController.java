package pers.sunny.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import pers.sunny.commonutils.Result;
import pers.sunny.eduservice.entity.pojo.EduCourse;
import pers.sunny.eduservice.entity.pojo.EduTeacher;
import pers.sunny.eduservice.service.EduCourseService;
import pers.sunny.eduservice.service.EduTeacherService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-16-17:32
 */
@RestController
@RequestMapping("/eduservice/teacherfront")
@Api(tags = "前台讲师数据")
public class TeacherFrontController {
    @Resource
    private EduTeacherService eduTeacherService;
    @Resource
    private EduCourseService eduCourseService;

    @ApiOperation(value = "分页查询讲师")
    @GetMapping(value = "/{current}/{limit}")
    public Result pageTeachers(@PathVariable Long current,@PathVariable Long limit) {
        Map<String,Object> map = eduTeacherService.pageFrontTeachers(current,limit);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping(value = "/{id}")
    public Result getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        //查询讲师信息
        EduTeacher teacher = eduTeacherService.getTeacherById(id);

        //根据讲师id查询这个讲师的课程列表
        List<EduCourse> courseList = eduCourseService.getByTeacherId(id);

        return Result.ok().data("teacher", teacher).data("courseList", courseList);
    }
}
