package pers.sunny.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import pers.sunny.eduservice.entity.pojo.EduVideo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 章节
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-02-10:00
 */
@ApiModel(value = "章节信息")
@Data
public class ChapterVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;

    //小节
    private List<EduVideo> children = new ArrayList<>();
}
