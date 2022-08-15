package com.eiit.presystemeiit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 * @Ahtuor liujingguang
 * @date 2022/5/22 14:35
 * @description 配置文件上传的临时缓存文件夹
 */

@Configuration
public class UploadTmpfolderConfig {

    @Autowired
    private PropertiesConfigure configure;

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        String location = configure.uploadTmpMkdir;
        File tmpFile = new File(location);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        factory.setLocation(location);
        return factory.createMultipartConfig();
    }

}
