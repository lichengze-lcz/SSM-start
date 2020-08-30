package com.lcz.ssm.dao;

import com.lcz.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//AOP日志持久层
public interface ISysLogDao {

    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method)" +
            "values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(SysLog sysLog)throws Exception;

    @Select("select * from syslog")
    List<SysLog> findAll() throws Exception;

}
