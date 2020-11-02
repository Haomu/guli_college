package pers.sunny.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import pers.sunny.eduservice.entity.CourseQuery;
import pers.sunny.eduservice.entity.pojo.EduChapter;
import pers.sunny.eduservice.entity.pojo.EduCourse;
import pers.sunny.eduservice.entity.pojo.EduCourseDescription;
import pers.sunny.eduservice.entity.vo.CourseInfoVo;
import pers.sunny.eduservice.entity.vo.CoursePublishVo;
import pers.sunny.eduservice.entity.vo.CourseQueryVo;
import pers.sunny.eduservice.entity.vo.CourseWebVo;
import pers.sunny.eduservice.mapper.EduCourseMapper;
import pers.sunny.eduservice.service.EduChapterService;
import pers.sunny.eduservice.service.EduCourseDescriptionService;
import pers.sunny.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.sunny.eduservice.service.EduVideoService;
import pers.sunny.servicebase.exception.GuliException;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author sunny
 * @since 2020-09-29
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Resource
    private EduCourseMapper eduCourseMapper;

    @Resource
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Resource
    private EduVideoService eduVideoService;

    @Resource
    private EduChapterService eduChapterService;

    /**
     * @param courseInfoVo
     * @return void
     * @Author Sunny
     * @Description 添加课程基本信息
     * @Date 2020/9/29
     * @Param [courseInfoVo]
     */
    @Override
    public String insertCourseInfo(CourseInfoVo courseInfoVo) {
        //1.向课程表添加课程基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = eduCourseMapper.insert(eduCourse);
        if (insert == 0) {
            throw new GuliException(20001, "添加课程信息失败");
        }

        //获取添加课程id
        String cid = eduCourse.getId();

        //2.向课程简介表添加课程简介
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        //设置描述id就是课程id
        eduCourseDescription.setId(cid);
        eduCourseDescriptionService.save(eduCourseDescription);
        return cid;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //1.查询课程表
        EduCourse eduCourse = eduCourseMapper.selectById(courseId);
        //2.查询描述表
        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(courseId);
        //3.封装
        CourseInfoVo courseInfo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfo);
        BeanUtils.copyProperties(courseDescription, courseInfo);

        return courseInfo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfo) {
        //1.修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo, eduCourse);
        int update = eduCourseMapper.updateById(eduCourse);
        if (update == 0) {
            throw new GuliException(20001, "修改课程信息失败");
        }
        //2.修改描述表
        EduCourseDescription courseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfo, courseDescription);
        eduCourseDescriptionService.updateById(courseDescription);
    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        return eduCourseMapper.getPublishCourseInfo(id);
    }

    /**
     * @Author Sunny
     * @Description  条件分页查询
     * @Date 2020/10/4
     * @Param [current, limit, courseQuery]
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<pers.sunny.eduservice.entity.pojo.EduCourse>
     **/
    @Override
    public Page<EduCourse> pageCourseCondition(Long current, Long limit, CourseQuery courseQuery) {
        Page<EduCourse> coursePage = new Page<>(current, limit);

        String subjectId = courseQuery.getSubjectId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String teacherId = courseQuery.getTeacherId();
        String title = courseQuery.getTitle();

        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq(!StringUtils.isEmpty(subjectId), "subject_id", subjectId)
                .eq(!StringUtils.isEmpty(subjectParentId), "subject_parent_id", subjectParentId)
                .eq(!StringUtils.isEmpty(teacherId), "teacher_id", teacherId)
                .like(!StringUtils.isEmpty(title), "title", title)
                .orderByDesc("create_time");


        return eduCourseMapper.selectPage(coursePage, courseQueryWrapper);
    }

    @Override
    public void deleteById(String id) {
        //删除小节
        eduVideoService.deleteVideoByCourseId(id);
        //删除章节
        eduChapterService.remove(new UpdateWrapper<EduChapter>().eq("course_id",id));
        //删除描述
        eduCourseDescriptionService.removeById(id);

        //删除课程
        int result = eduCourseMapper.deleteById(id);
        if (result == 0){
            throw new GuliException(20001,"删除失败");
        }
    }

    @Override
    public List<EduCourse> listCourse() {
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("id")
                .last(" limit 8");
        return eduCourseMapper.selectList(courseQueryWrapper);

    }

    @Override
    public List<EduCourse> getByTeacherId(String id) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id",id)
                .orderByDesc("update_time");
        return eduCourseMapper.selectList(queryWrapper);
    }

    @Override
    public Map<String, Object> pageFrontCourses(Long current, Long limit, CourseQueryVo courseQuery) {
        String subjectParentId = courseQuery.getSubjectParentId();
        String subjectId = courseQuery.getSubjectId();
        String buyCountSort = courseQuery.getBuyCountSort();
        String createTimeSort = courseQuery.getCreateTimeSort();
        String priceSort = courseQuery.getPriceSort();

        //条件构造，动态sql拼接
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(subjectParentId),"subject_parent_id", subjectParentId)
                .eq(!StringUtils.isEmpty(subjectId),"subject_id",subjectId)
                .orderByDesc(!StringUtils.isEmpty(buyCountSort),"buy_count")
                .orderByDesc(!StringUtils.isEmpty(createTimeSort),"create_time")
                .orderByDesc(!StringUtils.isEmpty(priceSort),"price");
        //分页查询
        Page<EduCourse> page = new Page<>(current,limit);
        Page<EduCourse> coursePage = eduCourseMapper.selectPage(page, queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("items",coursePage.getRecords());
        map.put("current", coursePage.getCurrent());
        map.put("pages", coursePage.getPages());
        map.put("size", coursePage.getSize());
        map.put("total", coursePage.getTotal());
        map.put("hasNext", coursePage.hasNext());
        map.put("hasPrevious", coursePage.hasPrevious());

        return map;
    }

    @Override
    public CourseWebVo getFrontCourseInfo(String courseId) {
        this.updatePageViewCount(courseId);
        return eduCourseMapper.getFrontCourseInfo(courseId);
    }

    @Override
    public void updatePageViewCount(String courseId) {
        EduCourse course = eduCourseMapper.selectById(courseId);
        course.setViewCount(course.getViewCount() + 1);
        eduCourseMapper.updateById(course);
    }
}
