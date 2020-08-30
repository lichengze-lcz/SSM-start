package com.lcz.ssm.dao;

import com.lcz.ssm.domain.Member;
import com.lcz.ssm.domain.Orders;
import com.lcz.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

//订单持久层接口
public interface IOrdersDao {

    //查询订单
    @Select("select * from orders")   //查询本表。也需要查询对应的产品信息
    @Results({
            //     主键      实体类字段         数据库字段
            @Result(id=true,property = "id" ,column = "id"),
            //外键映射
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            //查询 Product 里的数据
            @Result(property = "product",column = "productId",javaType = Product.class,one=@One(select = "com.lcz.ssm.dao.IProductDao.findByid")),



    })
    public List <Orders> findAll() throws Exception;


    @Select("select * from orders where id=#{ordersId}")   //1.先查订单表 根据订单id
    @Results({
            //     主键      实体类字段         数据库字段
            @Result(id=true,property = "id" ,column = "id"),
            //外键映射
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
  //订单对应的产品 //查询 Product 里的数据        约束关系                                             查询一条
            @Result(property = "product",column = "productId",javaType = Product.class,one=@One(select = "com.lcz.ssm.dao.IProductDao.findByid")),
  //订单对应的会员 //查询会员信息 一对多
            @Result(property = "member", column = "memberId",javaType = Member.class,one = @One(select = "com.lcz.ssm.dao.IMemberDao.findById")),
  //订单对应的游客 //查询游客 多对多关系  两表之间通过中间表关联  //订单id
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,many = @Many(select = "com.lcz.ssm.dao.ITravellerDao.findByOrdersId")),


    })
    //查询订单的详细信息
    public Orders findById(String ordersId);


}
