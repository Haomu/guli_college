package pers.sunny.eduservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import pers.sunny.commonutils.Result;
import pers.sunny.eduservice.entity.CourseQuery;
import pers.sunny.eduservice.entity.pojo.EduCourse;
import pers.sunny.eduservice.entity.vo.CourseInfoVo;
import pers.sunny.eduservice.entity.vo.CoursePublishVo;
import pers.sunny.eduservice.entity.vo.TeacherQuery;
import pers.sunny.eduservice.service.EduCourseService;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author sunny
 * @since 2020-09-29
 */
@RestController
@RequestMapping("/eduservice/course")
@Api(tags = "课程管理")
public class EduCourseController {
    @Resource
    private EduCourseService eduCourseService;

    /**
     * @Author Sunny
     * @Description  添加课程基本信息
     * @Date 2020/9/29
     * @Param [courseInfoVo]
     * @return pers.sunny.commonutils.Result
     **/
    @PostMapping("/")
    public Result insertCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = eduCourseService.insertCourseInfo(courseInfoVo);
        return Result.ok().data("id",id);
    }

    /**
     * @Author Sunny
     * @Description  根据课程id查询CourseInfoVo
     * @Date 2020/10/2
     * @Param [courseId]
     * @return pers.sunny.commonutils.Result
     **/
    @GetMapping("/{courseId}")
    public Result getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfo = eduCourseService.getCourseInfo(courseId);
        return Result.ok().data("courseInfo",courseInfo);
    }

    /**
     * @Author Sunny
     * @Description  更新课程信息
     * @Date 2020/10/2
     * @Param [courseInfo]
     * @return pers.sunny.commonutils.Result
     **/
    @PostMapping("/updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseInfoVo courseInfo){
        eduCourseService.updateCourseInfo(courseInfo);

        return Result.ok();
    }

    /**
     * @Author Sunny
     * @Description  信息最终确认
     * @Date 2020/10/2
     * @Param [id]
     * @return pers.sunny.commonutils.Result
     **/
    @GetMapping("/PublishCourseInfo/{id}")
    public Result getPublishCourseInfo(@PathVariable String id){
        CoursePublishVo coursePublishVo = eduCourseService.publishCourseInfo(id);

        return Result.ok().data("publishCourse",coursePublishVo);
    }

    /**
     * @Author Sunny
     * @Description  最终发布
     * @Date 2020/10/3
     * @Param [id]
     * @return pers.sunny.commonutils.Result
     **/
    @PostMapping("/publish/{id}")
    public Result publish(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return Result.ok();
    }

    @ApiOperation("条件分页查询")
    @PostMapping("/CourseCondition/{current}/{limit}")
    public Result pageCourseCondition(@PathVariable("current") Long current,
                                      @PathVariable("limit") Long limit,
                                      @RequestBody(required = false) CourseQuery courseQuery){
        Page<EduCourse> coursePage = eduCourseService.pageCourseCondition(current,limit,courseQuery);
        long total = coursePage.getTotal();
        List<EduCourse> records = coursePage.getRecords();

        return Result.ok().data("total",total).data("records",records);
    }

    @ApiOperation("删除课程")
    @DeleteMapping("/{id}")
    public Result deleteCourse(@PathVariable String id){
        eduCourseService.deleteById(id);
        return Result.ok();
    }

}

