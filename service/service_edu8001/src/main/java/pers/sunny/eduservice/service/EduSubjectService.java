package pers.sunny.eduservice.service;

import org.springframework.web.multipart.MultipartFile;
import pers.sunny.eduservice.entity.pojo.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.sunny.eduservice.entity.vo.FirstSubject;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author sunny
 * @since 2020-09-29
 */
public interface EduSubjectService extends IService<EduSubject> {

    void addSubject(MultipartFile file, EduSubjectService eduSubjectService);

    List<FirstSubject> listSubjects();
}
