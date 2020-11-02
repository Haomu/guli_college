package pers.sunny.test.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-09-29-10:25
 */
@Data
public class DemoData {
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer id;

    @ExcelProperty(value = "学生姓名",index = 1)
    private String name;
}
