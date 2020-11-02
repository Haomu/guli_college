package pers.sunny.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import pers.sunny.commonutils.JwtUtils;
import pers.sunny.commonutils.Result;
import pers.sunny.commonutils.ordervo.CourseWebOrderVo;
import pers.sunny.commonutils.ordervo.UcenterMemberOrderVo;
import pers.sunny.eduservice.client.OrderClient;
import pers.sunny.eduservice.entity.pojo.EduCourse;
import pers.sunny.eduservice.entity.vo.ChapterVo;
import pers.sunny.eduservice.entity.vo.CourseQueryVo;
import pers.sunny.eduservice.entity.vo.CourseWebVo;
import pers.sunny.eduservice.service.EduChapterService;
import pers.sunny.eduservice.service.EduCourseService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-16-20:09
 */
@RestController
@RequestMapping("/eduservice/coursefront")
@Api(tags = "前台课程数据")
public class CourseFrontController {
    @Resource
    private EduCourseService eduCourseService;
    @Resource
    private EduChapterService eduChapterService;
    @Resource
    private OrderClient orderClient;

    @RequestMapping("/{current}/{limit}")
    @ApiOperation("前台课程页面分页条件查询")
    public Result pageCourses(@PathVariable Long current, @PathVariable Long limit,
                              @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                              @RequestBody(required = false) CourseQueryVo courseQuery) {
        Map<String, Object> map = eduCourseService.pageFrontCourses(current,limit,courseQuery);

        return Result.ok().data(map);
    }

    @GetMapping("/{courseId}")
    @ApiOperation("前台课程详情数据查询")
    public Result getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request){
        //查询课程和讲师信息
        CourseWebVo courseWebVo = eduCourseService.getFrontCourseInfo(courseId);
        //查询当前课程章节信息
        List<ChapterVo> chapterVoList = eduChapterService.listChapters(courseId);
        //根据课程id和用户id查询当前课程是否已经支付过了
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        Boolean isBuyCourse = orderClient.isBuyCourse(courseId, memberId);
        System.out.println(isBuyCourse);
        return Result.ok().data("course",courseWebVo).data("chapterVoList",chapterVoList).data("isBuyCourse",isBuyCourse);
    }

    /**
     * @Author Sunny
     * @Description  根据课程id查询课程信息
     * @Date 2020/10/17
     * @Param [courseId]
     * @return pers.sunny.commonutils.ordervo.UcenterMemberOrder
     **/
    @GetMapping("/getDto/{courseId}")
    @ApiOperation("查询课程信息")
    public CourseWebOrderVo getCourseInfo(@PathVariable String courseId){
        //查询课程和讲师信息
        CourseWebVo courseWebVo = eduCourseService.getFrontCourseInfo(courseId);

        CourseWebOrderVo orderVo = new CourseWebOrderVo();
        BeanUtils.copyProperties(courseWebVo,orderVo);

        return orderVo;
    }
    @GetMapping("/updateBuyCount/{courseId}")
    public void updateBuyCount(@PathVariable("courseId") String courseId){
        EduCourse course = eduCourseService.getById(courseId);
        course.setBuyCount(course.getBuyCount() + 1);
        eduCourseService.updateById(course);
    }

}
