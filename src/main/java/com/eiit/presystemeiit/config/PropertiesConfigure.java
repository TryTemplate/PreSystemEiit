package com.eiit.presystemeiit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 配置文件
 *
 * @author Liujingguang
 * @Date 2022年4月10日14:21:54
 */

@Component
@PropertySource(value = {"classpath:propert.properties"})
public class PropertiesConfigure {

    //** ----------------------------------------------------------------------------------------------------------------------- ∨∨∨∨∨∨∨∨∨∨∨∨ **//
    /***************************    swagger start   */
    //swagger生产地址
    @Value("${swagger_host}")
    public String swaggerHost;

    //swagger测试地址
    @Value("${swagger_test_host}")
    public String swaggerTestHost;

    //是否展示swagger文档
    @Value("${is_show_swagger_ui}")
    public boolean isShowSwaggerUi;
    /***************************    swagger end   */
    //** ----------------------------------------------------------------------------------------------------------------------- ∧∧∧∧∧∧∧∧∧∧∧∧ **//


    //** ----------------------------------------------------------------------------------------------------------------------- ∨∨∨∨∨∨∨∨∨∨∨∨ **//
    /***************************    system environment 系统环境_是否是测试环境 1.是 0.否   */
    @Value("${is_test}")
    public Integer isTest;

    /***************************    system test phone 测试环境_接收短信手机号    */
    @Value("${test_phone}")
    public String testPhone;
    //** ----------------------------------------------------------------------------------------------------------------------- ∧∧∧∧∧∧∧∧∧∧∧∧ **//


    //** ----------------------------------------------------------------------------------------------------------------------- ∨∨∨∨∨∨∨∨∨∨∨∨ **//


    /***************************    文件上传的临时文件缓存文件夹    */
    @Value("${upload_tmp_mkdir}")
    public String uploadTmpMkdir;

    /***************************    文件上传的存储地址    */
    @Value("${upload_mkdir_path}")
    public String uploadMkdirPath;

    /***************************    文件上传的访问前缀    */
    @Value("${visit_prefix}")
    public String visitPrefix;


    /***************************    广告发布下发的配置文件生成地址(包含滚动字幕)    */
    @Value("${adv_file_folder_path}")
    public String advFileFolderPath;

    /***************************    广告发布访问前缀    */
    @Value("${adv_xml_prefix}")
    public String advXmlPrefix;


    /***************************    设备截图上传文件夹    */
    @Value("${screenshot_mkdir_path}")
    public String screenshotMkdirPath;

    /***************************    广告发布访问前缀    */
    @Value("${screenshot_prefix}")
    public String screenshotPrefix;


    /***************************    设备截图上传文件夹    */
    @Value("${web_deploy_path}")
    public String webDeployPath;

    /***************************    前端访问前缀    */
    @Value("${web_visit_prefix}")
    public String webVisitPrefix;


    /***************************    客户配置相关文件夹    */
    @Value("${customer_mkdir_path}")
    public String customerMkdirPath;

    /***************************    客户配置相关访问前缀    */
    @Value("${customer_prefix}")
    public String customerPrefix;


    /***************************    客户端更新包apk相关文件夹    */
    @Value("${apk_mkdir_path}")
    public String apkMkdirPath;

    /***************************    客户端更新包apk相关访问前缀    */
    @Value("${apk_prefix}")
    public String apkPrefix;


    /***************************    设备logo的存储地址    */
    @Value("${logo_mkdir_path}")
    public String logoMkdirPath;

    /***************************    设备logo的访问前缀    */
    @Value("${logo_prefix}")
    public String logoPrefix;
    //** ----------------------------------------------------------------------------------------------------------------------- ∧∧∧∧∧∧∧∧∧∧∧∧ **//


    //服务器参数配置
    //服务器接入码
    public static Integer accessCode = 0;


    //服务器参数配置
    //服务器接入码
    @Value("${app_secret}")
    public String appSecret;
    @Value("${app_id}")
    public String appId;


    @Override
    public String toString() {
        return "PropertiesConfigure{" +
                "swaggerHost='" + swaggerHost + '\'' +
                ", swaggerTestHost='" + swaggerTestHost + '\'' +
                ", isShowSwaggerUi=" + isShowSwaggerUi +
                ", isTest=" + isTest +
                ", testPhone='" + testPhone + '\'' +
                ", uploadTmpMkdir='" + uploadTmpMkdir + '\'' +
                ", uploadMkdirPath='" + uploadMkdirPath + '\'' +
                ", visitPrefix='" + visitPrefix + '\'' +
                ", advFileFolderPath='" + advFileFolderPath + '\'' +
                ", advXmlPrefix='" + advXmlPrefix + '\'' +
                ", screenshotMkdirPath='" + screenshotMkdirPath + '\'' +
                ", screenshotPrefix='" + screenshotPrefix + '\'' +
                ", webDeployPath='" + webDeployPath + '\'' +
                ", webVisitPrefix='" + webVisitPrefix + '\'' +
                ", customerMkdirPath='" + customerMkdirPath + '\'' +
                ", customerPrefix='" + customerPrefix + '\'' +
                ", apkMkdirPath='" + apkMkdirPath + '\'' +
                ", apkPrefix='" + apkPrefix + '\'' +
                ", logoMkdirPath='" + logoMkdirPath + '\'' +
                ", logoPrefix='" + logoPrefix + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", appId='" + appId + '\'' +
                '}';
    }
}
