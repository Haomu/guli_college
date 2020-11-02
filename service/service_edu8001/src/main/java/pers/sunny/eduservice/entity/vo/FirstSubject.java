package pers.sunny.eduservice.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 一级分类
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-09-29-13:41
 */
@Data
public class FirstSubject {

    private String id;
    private String title;
    private List<SecondSubject> children = new ArrayList<>();
}
