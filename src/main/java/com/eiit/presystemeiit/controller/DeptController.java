package com.eiit.presystemeiit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eiit.presystemeiit.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Ahtuor liujingguang
 * @date 2022/8/10 17:57
 * @description 部门操作controller
 */

@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DepartmentService departmentService;


    @RequestMapping("/list")
    public String selDeptList(Model model, @RequestParam(value = "pageNum", required = false, defaultValue = "1") Long pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize){

        Page page = new Page(pageNum, pageSize);
        departmentService.page(page, null);
        model.addAttribute("deptList", page.getRecords());
        model.addAttribute("page", page.setRecords(null));

        return "index";
    }


    @RequestMapping("/del")
    public String selDeptList(Model model, Long id){

        boolean b = departmentService.removeById(id);

        return "redirect:/dept/list";
    }

}
