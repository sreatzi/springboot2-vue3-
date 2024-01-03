package com.lantu;



import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        String url = "jdbc:mysql:///hotel";
        String username = "root";
        String password = "123456";
        String modulename = "sys";
        String mapperLocation = "E:\\我的接口\\lxxD\\src\\main\\resources\\mapper\\" + modulename;
        String tables = "admin,allpay,book,recording,room,serve,user,commit,chabook";

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("sreat")
                            .outputDir("E:\\我的接口\\lxxD\\src\\main\\java");
                })

                .packageConfig(builder -> {
                    builder.parent("com.lantu") // 设置父包名
                            .moduleName(modulename) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}