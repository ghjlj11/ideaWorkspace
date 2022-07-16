package com.ghj;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.SQLException;
import java.util.Collections;

/**
 * 代码自动生成
 */
public class GhjCode {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/malajava?useSSL=false&useUnicode=true&characterEncoding=utf-8", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("ghj") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:/my-study/ideaWorkspace/mybatis-plus-study/src/main/java/com/ghj/blog"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.ghj")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:/my-study/ideaWorkspace/mybatis-plus-study/src/main/java/com/ghj/blog")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("blog") ;// 设置需要生成的表名
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
