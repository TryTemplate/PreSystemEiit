package com.eiit.presystemeiit.exception;

import lombok.Data;

/**
 * @Ahtuor liujingguang
 * @Date 2022/5/9 0009 18:04
 * @ClassName TokenException
 * @description default
 */

@Data
public class PermissException extends Exception{

    private Integer exceptionCode;
    private String exceptionMsg;

    public PermissException(){}

    public PermissException(Integer exceptionCode){
        this.exceptionCode = exceptionCode;
    }

    public PermissException(String exceptionMsg){
        this.exceptionMsg = exceptionMsg;
    }

    public PermissException(Integer exceptionCode, String exceptionMsg){
        this.exceptionCode = exceptionCode;
        this.exceptionMsg = exceptionMsg;
    }

    public PermissException(String exceptionMsg, Integer exceptionCode){
        this.exceptionMsg = exceptionMsg;
        this.exceptionCode = exceptionCode;
    }

    @Override
    public String getMessage(){
        return this.exceptionMsg;
    }

    @Override
    public String toString() {
        return "PermissException{" +
                "exceptionCode=" + exceptionCode +
                ", exceptionMsg='" + exceptionMsg + '\'' +
                '}';
    }
}
