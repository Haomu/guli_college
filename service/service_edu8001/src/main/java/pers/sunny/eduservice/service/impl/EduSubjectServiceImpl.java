package pers.sunny.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;
import pers.sunny.eduservice.entity.pojo.EduSubject;
import pers.sunny.eduservice.entity.SubjectData;
import pers.sunny.eduservice.entity.vo.FirstSubject;
import pers.sunny.eduservice.entity.vo.SecondSubject;
import pers.sunny.eduservice.listener.SubjectExcelListener;
import pers.sunny.eduservice.mapper.EduSubjectMapper;
import pers.sunny.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author sunny
 * @since 2020-09-29
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Resource
    private EduSubjectMapper eduSubjectMapper;

    @Override
    public void addSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            InputStream is = file.getInputStream();
            EasyExcel.read(is, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<FirstSubject> listSubjects() {
        //1.获取一级分类
        QueryWrapper<EduSubject> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("parent_id","0");
        List<EduSubject> firstSubjectList = eduSubjectMapper.selectList(wrapper1);

        //2.获取二级分类
        QueryWrapper<EduSubject> wrapper2 = new QueryWrapper<>();
        wrapper2.ne("parent_id","0");
        List<EduSubject> secondSubjectList = eduSubjectMapper.selectList(wrapper2);

        //最终结果集
        List<FirstSubject> finalSubjectList1 = new ArrayList<>();

        //3.封装一级分类
        for (EduSubject subject1 : firstSubjectList) {
            //得到firstSubjectList每个subject1对象值
            //把subject1值给firstSubject
            FirstSubject firstSubject = new FirstSubject();
            BeanUtils.copyProperties(subject1,firstSubject);

            //4.封装二级分类
            //创建List集合封装每个一级分类的二级分类
            List<SecondSubject> finalSubjectList2 = new ArrayList<>();
            for (EduSubject subject2 : secondSubjectList) {
                if (subject2.getParentId().equals(subject1.getId())){
                    //此时说明subject1为subject2的一级分类
                    //将subject2的值复制到secondSubject中
                    SecondSubject secondSubject = new SecondSubject();
                    BeanUtils.copyProperties(subject2,secondSubject);
                    finalSubjectList2.add(secondSubject);
                }
            }

            //把一级下面的所有分类放到一级Children里
            firstSubject.setChildren(finalSubjectList2);

            //多个firstSubject放到finalSubjectList里
            finalSubjectList1.add(firstSubject);
        }


        return finalSubjectList1;
    }
}
