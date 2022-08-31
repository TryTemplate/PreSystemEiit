package com.eiit.presystemeiit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eiit.presystemeiit.model.Department;
import org.apache.ibatis.annotations.Param;

/**
* @author Ljggg
* @description 针对表【pre_department(部门表)】的数据库操作Mapper
* @createDate 2022-08-07 09:29:45
* @Entity com.eiit.presystemeiit.model.Department
*/
public interface DepartmentMapper extends BaseMapper<Department> {

    int deleteByIdAndDeptNameAndDeptDescription(@Param("id") Long id, @Param("deptName") String deptName, @Param("deptDescription") String deptDescription);

}




