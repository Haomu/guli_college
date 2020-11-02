package pers.sunny.eduservice.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import pers.sunny.commonutils.Result;
import pers.sunny.eduservice.entity.vo.FirstSubject;
import pers.sunny.eduservice.service.EduSubjectService;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author sunny
 * @since 2020-09-29
 */
@RestController
@RequestMapping("/eduservice")
@Api(tags = "课程分类管理")
public class EduSubjectController {

    @Resource
    private EduSubjectService eduSubjectService;

    @ApiOperation("添加课程分类")
    @PostMapping("/subjects")
    public Result getData(MultipartFile file){
        eduSubjectService.addSubject(file,eduSubjectService);
        return Result.ok();
    }

    @ApiOperation("课程分类列表查询")
    @GetMapping("/subjects")
    public Result listSubjects() {
        List<FirstSubject> list = eduSubjectService.listSubjects();
        return Result.ok().data("list",list);
    }

}

