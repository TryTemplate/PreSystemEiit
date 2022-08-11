package com.eiit.presystemeiit.mapper;

import com.eiit.presystemeiit.model.Emp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author Ljggg
* @description 针对表【pre_emp(员工表)】的数据库操作Mapper
* @createDate 2022-08-07 09:29:45
* @Entity com.eiit.presystemeiit.model.Emp
*/
public interface EmpMapper extends BaseMapper<Emp> {

    Map<String, Object> selectEmpByIdToMap(@Param("id") long id);

}




