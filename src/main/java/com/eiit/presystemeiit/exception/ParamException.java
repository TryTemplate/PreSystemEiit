package com.eiit.presystemeiit.exception;

import lombok.Data;

/**
 * @Ahtuor liujingguang
 * @Date 2022-5-13 10:30:08
 * @ClassName ParamException
 * @description default
 */

@Data
public class ParamException extends Exception{

    private Integer exceptionCode;
    private String exceptionMsg;
    private Object param;

    public ParamException(){}

    public ParamException(Integer exceptionCode){
        this.exceptionCode = exceptionCode;
    }

    public ParamException(String exceptionMsg){
        this.exceptionMsg = exceptionMsg;
    }

    public ParamException(String exceptionMsg, Object param){
        this.exceptionMsg = exceptionMsg;
        this.param = param;
    }

    public ParamException(Integer exceptionCode, String exceptionMsg, Object param){
        this.exceptionCode = exceptionCode;
        this.exceptionMsg = exceptionMsg;
        this.param = param;
    }

    public ParamException(String exceptionMsg, Integer exceptionCode, Object param){
        this.exceptionMsg = exceptionMsg;
        this.exceptionCode = exceptionCode;
        this.param = param;
    }

    @Override
    public String getMessage(){
        return this.exceptionMsg;
    }

    @Override
    public String toString() {
        return "ParamException{" +
                "exceptionCode=" + exceptionCode +
                ", exceptionMsg='" + exceptionMsg + '\'' +
                ", param=" + param +
                '}';
    }
}
