package com.eiit.presystemeiit;

/**
 * @Ahtuor liujingguang
 * @date 2022/8/5 22:36
 * @description default
 */

import com.eiit.presystemeiit.mapper.EmpMapper;
import com.eiit.presystemeiit.model.Department;
import com.eiit.presystemeiit.model.Emp;
import com.eiit.presystemeiit.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class MyBatisPlusTester {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void testInsertEmp() {
        Emp emp = new Emp();
        emp.setName("zhangsan");
        emp.setAge(25);
        emp.setPhone("13035225405");
        emp.setEmail("Ljguang0401@163.com");
        int result = empMapper.insert(emp);
        System.out.println(emp);
    }

    @Test
    public void testInsertDept() {

        for (int i = 6; i < 500; i++) {
            Department dept = new Department();
            dept.setDeptName("总裁办" + i);
            dept.setDeptDescription("eiit总裁办" + i);
            boolean save = departmentService.save(dept);
            System.out.println(dept);
        }
    }

    @Test
    public void testUpdateEmp() {
        Emp emp = new Emp();
        emp.setName("zhangsantwo");
        emp.setAge(22);
        emp.setPhone("13035225406");
        emp.setEmail("Ljguang0401@163.com1");
        emp.setId(1555568226252066817L);
        int result = empMapper.updateById(emp);
    }

    @Test
    public void testSelectEmp() {
        System.out.println("in to test sel emp");
        List<Emp> empList = empMapper.selectList(null);
        empList.forEach(System.out::println);
    }

    @Test
    public void querySelectEmp() {
        System.out.println("in to test querySelectEmp");
        Map<String, Object> map = empMapper.selectEmpByIdToMap(1555568226252066817L);
        System.out.println(map);
    }


}
