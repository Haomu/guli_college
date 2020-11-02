package pers.sunny.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;
import pers.sunny.eduservice.entity.pojo.EduTeacher;
import pers.sunny.eduservice.entity.vo.TeacherQuery;
import pers.sunny.eduservice.mapper.EduTeacherMapper;
import pers.sunny.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author sunny
 * @since 2020-09-23
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {
    @Resource
    private EduTeacherMapper eduTeacherMapper;

    @Override
    public Page<EduTeacher> pageTeachers(Long current, Long limit) {
        Page<EduTeacher> teacherPage = new Page<>(current, limit);
        return eduTeacherMapper.selectPage(teacherPage, null);
    }

    @Override
    public Page<EduTeacher> pageTeachersCondition(Long current, Long limit, TeacherQuery teacherQuery) {
        Page<EduTeacher> teacherPage = new Page<>(current, limit);
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        queryWrapper.like(!StringUtils.isEmpty(name), "name", name)
                .eq(!StringUtils.isEmpty(level), "level", level)
                .ge(!StringUtils.isEmpty(begin), "create_time", begin)
                .le(!StringUtils.isEmpty(end), "create_time", end)
                .orderByDesc("create_time");

        return eduTeacherMapper.selectPage(teacherPage, queryWrapper);
    }

    @Override
    public List<EduTeacher> listTeachers() {
        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("id")
                .last(" limit 4");
        return eduTeacherMapper.selectList(teacherQueryWrapper);
    }

    @Override
    public Map<String, Object> pageFrontTeachers(Long current, Long limit) {
        Page<EduTeacher> page = new Page<>(current, limit);

        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        Page<EduTeacher> eduTeachers = eduTeacherMapper.selectPage(page, queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("items",eduTeachers.getRecords());
        map.put("current", eduTeachers.getCurrent());
        map.put("pages", eduTeachers.getPages());
        map.put("size", eduTeachers.getSize());
        map.put("total", eduTeachers.getTotal());
        map.put("hasNext", eduTeachers.hasNext());
        map.put("hasPrevious", eduTeachers.hasPrevious());

        return map;
    }

    @Override
    public EduTeacher getTeacherById(String id) {
        return eduTeacherMapper.selectById(id);
    }


}
