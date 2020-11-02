package pers.sunny.test.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-09-29-10:25
 */
public class DataTest {

    public static void main(String[] args) {
        //1.设置文件输出路径
        String fileName = "C:/Users/Mumu/Desktop/student.xlsx";
//        //2.调用easyExcel方法实现写操作
//        EasyExcel.write(fileName,DemoData.class).sheet("学生信息表").doWrite(getData());

        //读操作
        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet("学生信息表").doRead();
    }

    public static List<DemoData> getData(){
        List<DemoData> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData demoData = new DemoData();
            demoData.setId(i);
            demoData.setName("sunny" + i + "号");
            data.add(demoData);
        }
        return data;
    }
}
