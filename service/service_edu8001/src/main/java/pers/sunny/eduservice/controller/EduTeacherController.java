package pers.sunny.eduservice.controller;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import pers.sunny.commonutils.Result;
import pers.sunny.eduservice.entity.pojo.EduTeacher;
import pers.sunny.eduservice.entity.vo.TeacherQuery;
import pers.sunny.eduservice.service.EduTeacherService;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author sunny
 * @since 2020-09-23
 */
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Resource
    private EduTeacherService eduTeacherService;

    @ApiOperation("查询所有讲师")
    @GetMapping("/teachers")
    public Result listTeachers(){
        List<EduTeacher> teachers = eduTeacherService.list();
        return Result.ok().data("teachers",teachers);
    }

    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("/{id}")
    public Result deleteTeacher(@ApiParam(name = "id",value = "讲师ID",required = true)
                                    @PathVariable("id") Long id){
        boolean flag = eduTeacherService.removeById(id);
        if (!flag){
            return Result.error().data("id",id);
        }
        return Result.ok().data("id",id);
    }

    @ApiOperation("分页查询讲师")
    @GetMapping("/teachers/{current}/{limit}")
    public Result pageTeachers(@PathVariable("current") Long current,
                              @PathVariable("limit") Long limit){
        Page<EduTeacher> teacherPage = eduTeacherService.pageTeachers(current, limit);

        long total = teacherPage.getTotal();
        List<EduTeacher> records = teacherPage.getRecords();

        return Result.ok().data("total",total).data("records",records);
    }

    @ApiOperation("条件分页查询")
    @PostMapping("/teachersCondition/{current}/{limit}")
    public Result pageTeachersCondition(@PathVariable("current") Long current,
                                        @PathVariable("limit") Long limit,
                                        @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> teacherPage = eduTeacherService.pageTeachersCondition(current, limit, teacherQuery);
        long total = teacherPage.getTotal();
        List<EduTeacher> records = teacherPage.getRecords();

        return Result.ok().data("total",total).data("records",records);
    }

    @ApiOperation("添加讲师")
    @PostMapping("/teachers")
    public Result insertTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.save(eduTeacher);
        if (flag) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    @ApiOperation("根据id查询讲师")
    @GetMapping("/teachers/{id}")
    public Result getTeacherById(@PathVariable Long id){
        EduTeacher teacher = eduTeacherService.getById(id);

        return Result.ok().data("teacher",teacher);
    }

    @ApiOperation("修改讲师信息")
    @PutMapping("/teachers")
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

}

