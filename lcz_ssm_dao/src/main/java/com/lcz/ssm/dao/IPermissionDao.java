package com.lcz.ssm.dao;

import com.lcz.ssm.domain.Pemission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//用于用户详细的 角色信息查询
public interface IPermissionDao {

    //根据中间表里面 根据角色idroleId， 去查询出所有的权限id permissionId
    @Select("select * from permission where id in(select permissionId from role_permission where roleId=#{id})")
    public List<Pemission> findPermissionByIdRoleId(String id) throws Exception;

    //查询permission表，用于资源权限管理
    @Select("select * from permission")
    List<Pemission> findAll() throws Exception;

    //添加权限 （本人开始创mysql数据库permission表id没有设置自增，默认值，id需自己添加）
    @Insert("insert into permission(id,permissionName,url)values(4,#{permissionName},#{url})")
    void save(Pemission permission) throws Exception;
}
