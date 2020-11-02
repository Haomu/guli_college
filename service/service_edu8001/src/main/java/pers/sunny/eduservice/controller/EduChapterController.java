package pers.sunny.eduservice.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import pers.sunny.commonutils.Result;
import pers.sunny.eduservice.entity.pojo.EduChapter;
import pers.sunny.eduservice.entity.vo.ChapterVo;
import pers.sunny.eduservice.service.EduChapterService;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程章节 前端控制器
 * </p>
 *
 * @author sunny
 * @since 2020-09-29
 */
@RestController
@RequestMapping("/eduservice/chapter")
@Api(tags = "课程章节管理")
public class EduChapterController {
    @Resource
    private EduChapterService eduChapterService;

    /**
     * @Author Sunny
     * @Description  根据课程id查询章节信息
     * @Date 2020/10/2
     * @Param [courseId]
     * @return pers.sunny.commonutils.Result
     **/
    @GetMapping("/{courseId}")
    public Result listChapters(@PathVariable String courseId){
        List<ChapterVo> list = eduChapterService.listChapters(courseId);

        return Result.ok().data("list",list);
    }

    /**
     * @Author Sunny
     * @Description  添加章节
     * @Date 2020/10/2
     * @Param
     * @return
     **/
    @PostMapping("/")
    public Result insertChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.save(eduChapter);
        return Result.ok();
    }

    /**
     * @Author Sunny
     * @Description  根据章节id查询章节信息
     * @Date 2020/10/2
     * @Param [chapterId]
     * @return pers.sunny.commonutils.Result
     **/
    @GetMapping("/getChapterInfo/{chapterId}")
    public Result listChaptersByChapterId(@PathVariable String chapterId){
        EduChapter chapter = eduChapterService.getById(chapterId);
        return Result.ok().data("chapter",chapter);
    }

    /**
     * @Author Sunny
     * @Description  修改章节
     * @Date 2020/10/2
     * @Param [eduChapter]
     * @return pers.sunny.commonutils.Result
     **/
    @PutMapping("/")
    public Result updateChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.updateById(eduChapter);
        return Result.ok();
    }

    @DeleteMapping("/{chapterId}")
    public Result deleteChapter(@PathVariable String chapterId){
        int chapter = eduChapterService.deleteChapter(chapterId);
        if (chapter == 0){
            return Result.error();
        }
        return Result.ok();
    }

}

