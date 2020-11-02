package pers.sunny.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.sunny.eduservice.entity.pojo.EduComment;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author sunny
 * @since 2020-10-17
 */
public interface EduCommentService extends IService<EduComment> {
    Page<EduComment> pageComment(Long current, Long limit, String courseId);

    void insertComment(EduComment eduComment, HttpServletRequest request);
}
