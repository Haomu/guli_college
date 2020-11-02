package pers.sunny.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import pers.sunny.eduservice.entity.pojo.EduSubject;
import pers.sunny.eduservice.entity.SubjectData;
import pers.sunny.eduservice.service.EduSubjectService;
import pers.sunny.servicebase.exception.GuliException;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-09-29-11:21
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    private EduSubjectService eduSubjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new GuliException(20001,"文件数据为空");
        }
        EduSubject existFirstSubject = existFirstSubject(eduSubjectService, subjectData.getFirstSubjectName());
        if (existFirstSubject == null){  //没有相同一级分类，进行添加
            existFirstSubject = new EduSubject();
            existFirstSubject.setParentId("0");
            existFirstSubject.setTitle(subjectData.getFirstSubjectName());
            eduSubjectService.save(existFirstSubject);
        }

        String pid = existFirstSubject.getId();
        //添加二级分类
        EduSubject existSecondSubject = existSecondSubject(eduSubjectService, subjectData.getSecondSubjectName(), pid);
        if (existSecondSubject == null) { //没有相同二级分类，进行添加
            existSecondSubject = new EduSubject();
            existSecondSubject.setParentId(pid);
            existSecondSubject.setTitle(subjectData.getSecondSubjectName());
            eduSubjectService.save(existSecondSubject);
        }
    }
    /**
     * @Author Sunny
     * @Description  判断一级分类是否存在
     * @Date 2020/9/29
     * @Param [eduSubjectService]
     * @return pers.sunny.eduservice.entity.SubjectData
     **/
    public EduSubject existFirstSubject(EduSubjectService eduSubjectService,String name){
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",name).eq("parent_id","0");
        return eduSubjectService.getOne(queryWrapper);
    }

    public EduSubject existSecondSubject(EduSubjectService eduSubjectService,String name,String pid) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",name).eq("parent_id",pid);
        return eduSubjectService.getOne(queryWrapper);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
