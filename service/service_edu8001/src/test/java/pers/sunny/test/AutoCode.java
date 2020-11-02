package pers.sunny.test;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-07-29-22:53
 */
@SpringBootTest
public class AutoCode {
    public static void main(String[] args) {
        //代码生成器
        AutoGenerator mpg = new AutoGenerator();
        //配置策略
        //1.全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath+"/service/service_edu8001/src/main/java");
        gc.setAuthor("sunny");
        gc.setOpen(false);//生成后是否打开资源管理器
        gc.setFileOverride(false);//重新生成时文件是否覆盖
        gc.setServiceName("%sService");//去掉Service接口的首字母I
        gc.setIdType(IdType.ASSIGN_ID);//主键生成策略
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
        gc.setSwagger2(true);//开启Swagger2模式
        mpg.setGlobalConfig(gc);

        //2.数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/guli_college");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        //3.包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("eduservice");
        pc.setParent("pers.sunny");
        pc.setEntity("entity");//实体类模块
        pc.setMapper("mapper");//dao层模块
        pc.setService("service");//service层模块
        pc.setController("controller");//controller模块
        mpg.setPackageInfo(pc);

        //4.策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("edu_comment");//设置需要的表名
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setTablePrefix(pc.getModuleName()+"_");//生成实体时去掉表前缀
        strategy.setEntityLombokModel(true);//是否使用Lombok

        strategy.setRestControllerStyle(true);//是否使用@RestController注解
        strategy.setControllerMappingHyphenStyle(true);//是否开启驼峰

        mpg.setStrategy(strategy);

        //5.执行
        mpg.execute();
    }
}
