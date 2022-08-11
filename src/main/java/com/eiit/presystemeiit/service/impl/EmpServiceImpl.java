package com.eiit.presystemeiit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eiit.presystemeiit.model.Emp;
import com.eiit.presystemeiit.service.EmpService;
import com.eiit.presystemeiit.mapper.EmpMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author Ljggg
* @description 针对表【pre_emp(员工表)】的数据库操作Service实现
* @createDate 2022-08-07 09:29:45
*/
@Service
@Transactional
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp>
    implements EmpService{

}




