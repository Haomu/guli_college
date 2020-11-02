package pers.sunny.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import pers.sunny.eduservice.client.VodClient;
import pers.sunny.eduservice.entity.pojo.EduVideo;
import pers.sunny.eduservice.mapper.EduVideoMapper;
import pers.sunny.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author sunny
 * @since 2020-09-29
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Resource
    private EduVideoMapper eduVideoMapper;

    @Resource
    private VodClient vodClient;

    @Override
    public void deleteVideoByCourseId(String id) {
        //1.根据课程id查询课程所有的视频id
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",id)
                    .select("video_source_id");
        List<EduVideo> videoSourceIds = eduVideoMapper.selectList(queryWrapper);
        //将查询出来的集合转为ids集合
        List<String> videoIds = new ArrayList<>();
        for (EduVideo video : videoSourceIds) {
            videoIds.add(video.getVideoSourceId());
        }
        //根据多个视频id删除
        if (videoIds.size() > 0){
            vodClient.deleteBatchVideo(videoIds);
        }

        //删除课程小节
        UpdateWrapper<EduVideo> videoUpdateWrapper = new UpdateWrapper<>();
        videoUpdateWrapper.eq("course_id",id);
        eduVideoMapper.delete(videoUpdateWrapper);
    }
}
