package com.eiit.presystemeiit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eiit.presystemeiit.common.Constants;
import com.eiit.presystemeiit.common.ResultBean;
import com.eiit.presystemeiit.model.Department;
import com.eiit.presystemeiit.redis.RedisHelper;
import com.eiit.presystemeiit.redis.RedisHelperImpl;
import com.eiit.presystemeiit.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Ahtuor liujingguang
 * @date 2022/8/10 17:57
 * @description 部门操作controller
 */

@Api(tags = "【部门】【REST API】")
@RestController
@RequestMapping("/dt")
@CacheConfig(cacheNames = {"dept"})
public class DeptRestController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisHelperImpl redisHelper;

    @Autowired
    private RedissonClient redisson;


    @Cacheable(key ="'dept_list_'.concat(#pageNum).concat('_').concat(#pageSize)",unless = "#result=null")
    @ApiOperation(value = "【部门】【查询部门列表】", notes = "查询部门列表", produces="application/json", position = 1)
    @GetMapping("/list")
    public ResultBean selDeptList(
            HttpServletRequest request,
            @ApiParam(name = "pageNum", value = "pageNum", required = false) @RequestParam(value = "pageNum", required = false, defaultValue = "1") Long pageNum,
            @ApiParam(name = "pageSize", value = "pageSize", required = false) @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize
    ){

        Page page = new Page(pageNum, pageSize);
        try {
            departmentService.page(page, null);
            redisHelper.valuePut("test", "redisHelper.test");
        }catch (Exception e){
            return new ResultBean<>(null).setRetCode(Constants.RET_CODE.FAIL);
        }

        return new ResultBean<>(page).setRetCode(Constants.RET_CODE.SUCCESS);
    }


    @ApiOperation(value = "【部门】【修改部门】", notes = "根据id修改部门", produces="application/json", position = 2)
    @GetMapping("/update")
    public ResultBean updateDeptList(
            HttpServletRequest request,
            @ApiParam(name = "id", value = "id", required = true) Long id,
            @ApiParam(name = "name", value = "name", required = true) String name
    ){

        Boolean b = null;
        try {
            Department dept = new Department();
            dept.setId(id);
            dept.setDeptName(name);
            b = departmentService.updateById(dept);
        }catch (Exception e){
            return new ResultBean<>(b).setRetCode(Constants.RET_CODE.FAIL);
        }

        return new ResultBean<>(b).setRetCode(Constants.RET_CODE.SUCCESS);
    }


    @ApiOperation(value = "【部门】【删除部门】", notes = "根据id删除部门", produces="application/json", position = 3)
    @GetMapping("/del")
    public ResultBean selDeptList(
            HttpServletRequest request,
            @ApiParam(name = "id", value = "id", required = true) Long id
    ){

        Boolean b = null;
        try {
            b = departmentService.removeById(id);
        }catch (Exception e){
            return new ResultBean<>(b).setRetCode(Constants.RET_CODE.FAIL);
        }

        return new ResultBean<>(b).setRetCode(Constants.RET_CODE.SUCCESS);
    }


    @ApiOperation(value = "【Redis Get】", notes = "Get", produces="application/json", position = 4)
    @GetMapping("/get")
    public ResultBean getRedis(){

        String str = null;
        try {
            str = (String) redisHelper.getValue("test");
        }catch (Exception e){
            return new ResultBean<>(null).setRetCode(Constants.RET_CODE.FAIL);
        }

        return new ResultBean<>(str).setRetCode(Constants.RET_CODE.SUCCESS);
    }


    @ApiOperation(value = "【Test】", notes = "Test", produces="application/json", position = 5)
    @GetMapping("/test")
    public ResultBean testRedission(){

        String str = null;
        try {

            RBloomFilter<String> bloom = redisson.getBloomFilter("name");
            bloom.tryInit(1000000L, 0.01);

            bloom.add("zhangsan");
            bloom.add("lisi");
            bloom.add("wangwu");
            bloom.add("zhaoliu");

            System.out.println(bloom.contains("ljg"));
            System.out.println(bloom.contains("wangwu"));

            str = (String) redisHelper.getValue("test");
        }catch (Exception e){
            e.printStackTrace();
            return new ResultBean<>().setRetCode(Constants.RET_CODE.FAIL);
        }

        return new ResultBean<>(str).setRetCode(Constants.RET_CODE.SUCCESS);
    }

}
