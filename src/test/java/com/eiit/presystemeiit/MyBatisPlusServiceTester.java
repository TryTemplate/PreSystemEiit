package com.eiit.presystemeiit;

/**
 * @Ahtuor liujingguang
 * @date 2022/8/5 22:36
 * @description default
 */

import com.eiit.presystemeiit.model.Emp;
import com.eiit.presystemeiit.service.EmpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisPlusServiceTester {

    @Autowired
    private EmpService empService;

    @Test
    public void testSelectEmp(){
        Emp emp = empService.getById(1555568226252066817L);
        long count = empService.count();
        System.out.println("emp: " + emp);
        System.out.println("count: " + count);
    }


}
