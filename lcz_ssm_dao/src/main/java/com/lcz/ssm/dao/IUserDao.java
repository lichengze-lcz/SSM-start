package com.lcz.ssm.dao;

import com.lcz.ssm.domain.Role;
import com.lcz.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//操作用户持久层
public interface IUserDao {

       //用于Security验证用户
        @Select("select * from users where username =#{username}")
        @Results({
                @Result(id = true,property = "id",column = "id"),
                @Result(property = "username",column = "username"),
                @Result(property = "email",column = "email"),
                @Result(property = "password",column = "password"),
                @Result(property = "phoneNum",column = "phoneNum"),
                @Result(property = "status",column = "status"),
                //                         根据当前用户id去查询 （有中间表）
                @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.lcz.ssm.dao.IRoleDao.findRoleByUserId"))}

        )
        public UserInfo findByUsername(String username)throws Exception;


        //查询用户所有信息
        @Select("select * from users")
        List<UserInfo> findAll() throws Exception;

        //添加用户
        @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
        void save(UserInfo userInfo) throws Exception;

        //根据用户id查询
        @Select("select * from users where id=#{id}")
        @Results({
                @Result(id = true,property = "id",column = "id"),
                @Result(property = "username",column = "username"),
                @Result(property = "email",column = "email"),
                @Result(property = "password",column = "password"),
                @Result(property = "phoneNum",column = "phoneNum"),
                @Result(property = "status",column = "status"),
                //查询出 用户对应的角色
                @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.lcz.ssm.dao.IRoleDao.findRoleByUserId"))

        })
        UserInfo findById(String id);

        //查询出当前用户的其他角色 (根据userId向中间表查，把中间表没有的userId查出来 )
        @Select("select * from role where id not in(select roleId from users_role where userId=#{userid})")
        List<Role> findOtherRoles(String userid);
        //添加users和role中间表信息
        @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
        void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId); //多参数要指定名字
}

