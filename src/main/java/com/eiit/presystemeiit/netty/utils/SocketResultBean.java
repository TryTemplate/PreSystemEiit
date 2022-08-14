package com.eiit.presystemeiit.netty.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SocketResultBean implements Serializable {

    private String token = "";
    private int type = 0; //0:普通消息;1:指令:2:文件下载 3.接收序列号

    private String command = ""; //type=1时有效 value: reboot:重启, device_info:设备信息, set_system_time:同步时间
    private String commandValue = ""; //type =1时，指令的值，如同步时间，服务器时间的值。

    private int recordId = 0;// 发布或者撤销时的记录id

    private int model = 0;//type=1时有效 value:1:追加-默认﹔2:替换（待当前播放结束后替换）﹔3∶立即替换（收到消患后立刻替换)
    private int fileType = 0; //type=2时，文件类型:0:广告计划；1：跑马灯；2：第三方广告（只有一个区域，并且是最上层显示）;3:定时开关机文件。shutdown.xml; 4:device_logo;5:文件下载，支持多个文件

    private String message = "";//消息内容
    private List<SocketResultDataModel> data;

}
