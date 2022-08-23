package com.eiit.presystemeiit.service;

public interface TestInterface {

    Integer mlit(int parm1, int parm2);

    default Integer add(int parm1, int parm2){
        return parm1 + parm2;
    }

}
