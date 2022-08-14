package com.eiit.presystemeiit.netty.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class SocketResultDataModel implements Serializable {

    private String fileExtend = "";

    private String fileName = "";

    private String fileUrl = "";

    private long fileLength  = 0;

}
