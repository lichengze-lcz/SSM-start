package com.lcz.ssm.dao;

import com.lcz.ssm.domain.Pemission;
import com.lcz.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

//用于 userinfo 用户 查询当前角色
public interface IRoleDao {

    //根据用户id 查询出角色 返回值是List集合
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            //对应的角色信息                    会根据当前id查询                        可以根绝一个角色查询出多个权限
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.lcz.ssm.dao.IPermissionDao.findPermissionByIdRoleId")),
    })
    //查询所有用户信息，对应出的角色信息，以及角色所有对应的权限信息查出来 permission
    public List<Role> findRoleByUserId(String userId) throws Exception;


    //查询所有角色
    @Select("select * from role")
    List<Role> finAll() throws Exception;


    //添加角色（注意一下自己建的mysql 数据库的表，id段是否有默认值）
    @Insert("insert into role(id,roleName,roleDesc) values(4,#{roleName},#{roleDesc})")
    void save(Role role);


    //角色详情信息
    @Select("select * from role where id=#{roleId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property ="roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.lcz.ssm.dao.IPermissionDao.findPermissionByIdRoleId")
            ),
    })
    Role findById(String id);


    //在 role_permission 角色权限关联
    @Delete("delete from users_role where roleId=#{roleId}")
    void deleteFromRole_PermissionByRoleId(String roleId);
    //在 user_role  用户角色关联
    @Delete("delete from role_permission where roleId=#{roleId}")
    void deleteFromUser_RoleByRoleId(String roleId);
    //在 role表删除
    @Delete("delete from role where id=#{roleId}")
    void deleteRoleById(String roleId);


    //添加权限，
    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
    //通过roleid 查找出对应的id  （###not 没有在这个关联表的id）        （括号里是所有关联的信息，加not 取反）
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Pemission> findOtherPermissions(String roleId);
}
