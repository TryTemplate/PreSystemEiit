package com.eiit.presystemeiit.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @Ahtuor liujingguang
 * @date 2022/8/6 21:35
 * @description default
 */

public class Generator {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/study_springboot_jsp?serverTimezone=GMT%2B8&characterEncoding=utf-8&userSSL=false", "root", "root")
                .globalConfig(builder -> {
                    builder.author("eiit developer")    /*设置作者*/
                            .enableSwagger()    /*开启swagger模式*/
                            .fileOverride()     /*覆盖已生成文件*/
                            .outputDir("E://DeveloperTools/SQL/MysqlBean"); /*输出目录*/
                })
                .packageConfig(builder -> {
                    builder.parent("com.eiit")
                            .moduleName("presystemeiit")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "E://DeveloperTools/SQL/MysqlBean"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("pre_emp", "pre_department")     /*设置需要生成的表名*/
                            .addTablePrefix("pre_", "adv_");    /*过滤/去掉表前缀*/
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
        ;
    }

}
