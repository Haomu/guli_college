package pers.sunny.test.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-09-29-10:50
 */
public class ExcelListener extends AnalysisEventListener<DemoData> {
    //一行一行读取excel中内容
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println("******" + demoData);
    }
    //读取表头内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("****" + headMap);
    }

    //读取完成之后做xxx
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
