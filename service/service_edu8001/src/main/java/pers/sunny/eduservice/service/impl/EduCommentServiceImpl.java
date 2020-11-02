package pers.sunny.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.sunny.commonutils.Result;
import pers.sunny.commonutils.ordervo.UcenterMemberOrderVo;
import pers.sunny.eduservice.client.UcenterClient;
import pers.sunny.eduservice.entity.pojo.EduComment;
import pers.sunny.eduservice.mapper.EduCommentMapper;
import pers.sunny.eduservice.service.EduCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author sunny
 * @since 2020-10-17
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {
    @Resource
    private EduCommentMapper eduCommentMapper;
    @Resource
    private UcenterClient ucenterClient;

    /**
     * @Author Sunny
     * @Description  分页查询课程评论
     * @Date 2020/10/17
     * @Param [current, limit, courseId]
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<pers.sunny.eduservice.entity.pojo.EduComment>
     **/
    @Override
    public Page<EduComment> pageComment(Long current, Long limit, String courseId) {
        Page<EduComment> page = new Page<>(current, limit);

        QueryWrapper<EduComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);

        return eduCommentMapper.selectPage(page,queryWrapper);
    }
    /**
     * @Author Sunny
     * @Description  添加课程评论
     * @Date 2020/10/17
     * @Param [eduComment, request]
     * @return void
     **/
    @Override
    public void insertComment(EduComment eduComment, HttpServletRequest request) {
        Result info = ucenterClient.getLoginInfo(request);
        UcenterMemberOrderVo userInfo = (UcenterMemberOrderVo) info.getData().get("userInfo");
        eduComment.setMemberId(userInfo.getId());
        eduComment.setAvatar(userInfo.getAvatar());
        eduComment.setNickname(userInfo.getNickname());

        eduCommentMapper.insert(eduComment);
    }
}
