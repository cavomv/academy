package com.emaolv.academy.teacher;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;


/**
 * @author: liu jia
 * @description: MyBatis Plus 代码生成器
 * @date: Created in 2021/5/11 09:42
 */
public class CodeGenerator {
    @Test
    public void genCode(){
        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 2、全局配置
        GlobalConfig gc =new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath+"/src/main/java");
        gc.setAuthor("Jia");
        // 3、生成后是否打开资源管理器
        gc.setOpen(false);
        // 4、去掉Service接口的首字母I
        gc.setServiceName("%sService");
        // 5、主键策略 雪花算法
        gc.setIdType(IdType.ASSIGN_ID);
        // 6、开启swagger2 模式
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);
        // 7、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/db_20210510_edu_core?serverTimezone=GMT%2b8&characterEncoding=utf-8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("1a!SY!5om8");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);
        // 8、包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.emaolv.academy.teacher");
        pc.setEntity("entity");
        mpg.setPackageInfo(pc);
        // 9、策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        // 数据库表映射到实体的命名策略
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        // 数据库表字段映射到实体的命名策略
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        // lombok
        strategyConfig.setEntityLombokModel(true);
        // 逻辑删除字段名
        strategyConfig.setLogicDeleteFieldName("is_deleted");
        // 去掉布尔值的is_前缀
        strategyConfig.setEntityBooleanColumnRemoveIsPrefix(true);
        // restful api 风格
        strategyConfig.setRestControllerStyle(true);
        mpg.setStrategy(strategyConfig);
        // 10、执行
        mpg.execute();
    }
}
