package pers.sunny.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-02-17:13
 */
@ApiModel(value = "课程发布信息")
@Data
public class CoursePublishVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String firstSubject;
    private String secondSubject;
    private String teacherName;
    private String price;//只用于显示
}
