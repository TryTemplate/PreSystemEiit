package com.eiit.presystemeiit.config;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swaggerui配置
 *
 * @Date    2022年4月10日14:01:31
 * @Author  Liujingguang
 */

@Configuration
public class SwaggerConfig {

    @Autowired
    private PropertiesConfigure configure;

    @Bean
    public Docket webAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(configure.isShowSwaggerUi)
//                .host(configure.swaggerHost)        //固定swagger访问地址
                .apiInfo(apiInfo())
                .select()
                .apis(Predicates.or(
                        RequestHandlerSelectors.basePackage("com.eiit.presystemeiit.controller"),
                        RequestHandlerSelectors.basePackage("com.eiit.presystemeiit.controllermvc")
                ))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Eiit Pre System Api")
                .description("EIIT Team, If you have any questions, please call 13035225405.")
//                .termsOfServiceUrl(configure.swaggerHost)       //删除host配置使用系统自动跳转
                .contact("EIIT Team")
                .version("1.0.1")
                .build();
    }
}
