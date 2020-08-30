package com.lcz.ssm.dao;

import com.lcz.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface IMemberDao {

    //根据 member表里id 查询member
    @Select("select * from member where id=#{id}")
    public Member findById(String id) throws Exception;
}
