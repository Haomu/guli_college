package pers.sunny.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import pers.sunny.eduservice.entity.pojo.EduChapter;
import pers.sunny.eduservice.entity.pojo.EduVideo;
import pers.sunny.eduservice.entity.vo.ChapterVo;
import pers.sunny.eduservice.mapper.EduChapterMapper;
import pers.sunny.eduservice.mapper.EduVideoMapper;
import pers.sunny.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.sunny.servicebase.exception.GuliException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程章节 服务实现类
 * </p>
 *
 * @author sunny
 * @since 2020-09-29
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Resource
    private EduChapterMapper eduChapterMapper;

    @Resource
    private EduVideoMapper eduVideoMapper;

    @Override
    public List<ChapterVo> listChapters(String courseId) {
        //1.根据courseId查询所有章节
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",courseId);

        List<EduChapter> chapters = eduChapterMapper.selectList(chapterQueryWrapper);

        //2.根据courseId查询所有小节
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",courseId);

        List<EduVideo> videos = eduVideoMapper.selectList(videoQueryWrapper);

        //3.遍历章节集合进行封装
        List<ChapterVo> finalChapter = new ArrayList<>();

        for (EduChapter chapter : chapters) {
            //把chapter的值赋值给chapterVo
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);

            //4.遍历小节集合进行封装
            List<EduVideo> finalVideo = new ArrayList<>();
            for (EduVideo video : videos) {
                if (video.getChapterId().equals(chapter.getId())){
                    EduVideo videoVo = new EduVideo();
                    BeanUtils.copyProperties(video,videoVo);
                    finalVideo.add(videoVo);
                }
            }
            //将小节集合放到章节里
            chapterVo.setChildren(finalVideo);

            //将章节放到章节集合里
            finalChapter.add(chapterVo);

        }

        return finalChapter;
    }

    @Override
    public int deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("chapter_id",chapterId);
        Integer count = eduVideoMapper.selectCount(videoQueryWrapper);
        if (count > 0) {  //此时章节包含小节
            throw new GuliException(20001,"章节包含小节，非法删除");
        } else {
            return eduChapterMapper.deleteById(chapterId);
        }
    }
}
