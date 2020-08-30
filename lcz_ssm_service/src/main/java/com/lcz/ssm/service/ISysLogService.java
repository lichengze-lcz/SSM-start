package com.lcz.ssm.service;

import com.lcz.ssm.domain.SysLog;

import java.util.List;

//AOP日志 业务层
public interface ISysLogService {


    public void save(SysLog sysLog) throws Exception;


    List<SysLog> findAll() throws Exception;
}
