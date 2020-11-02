package pers.sunny.eduservice.service;

import pers.sunny.eduservice.entity.pojo.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.sunny.eduservice.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程章节 服务类
 * </p>
 *
 * @author sunny
 * @since 2020-09-29
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> listChapters(String courseId);

    int deleteChapter(String chapterId);
}
