package com.eiit.presystemeiit.thread;

import com.eiit.presystemeiit.service.TestInterface;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Ahtuor liujingguang
 * @date 2022/8/18 23:23
 * @description default
 */

public class TestThread {

    public void run() {
        System.out.println("my self run fundtion");
    }

    public static void main(String[] args) {
        //String s = null;
        //System.out.println("s=" + s);
        //System.out.println(getValue(2));

        TestInterface testInterface = new TestInterface() {
            @Override
            public Integer mlit(int parm1, int parm2) {
                return parm1 * parm2;
            }
        };

        final int parm1 = 2, parm2 = 6;

//        System.out.println(testInterface.add(parm1, parm2));
//        System.out.println(testInterface.mlit(parm1, parm2));

        List<String> names = Arrays.asList("d", "f", "a", "e", "b");

        Collections.sort(names, Comparator.naturalOrder());

        names.forEach(System.out::println);
        
        
        

    }

    public static int getValue(int i) {
        int result = 0;
        switch (i) {
            case 1:
                result = result + i;
            case 2:
                result = result + i * 2;
            case 3:
                result = result + i * 3;
        }
        return result;
    }

    TestThread(){}

    TestThread(String str, Integer id){}

    public void TestThread(){
        Double d = 0.3663d;
        double dd = 06332D;
    }

}
