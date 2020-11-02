package pers.sunny.eduservice.entity;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-09-29-11:18
 */
@Data
public class SubjectData {
    @ExcelProperty(index = 0)
    private String firstSubjectName;
    @ExcelProperty(index = 1)
    private String secondSubjectName;
}
