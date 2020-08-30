package com.lcz.ssm.dao;

import com.lcz.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//用于查询游客信息
public interface ITravellerDao {

    //通过 ordersId查询   先从中间表先把 ordersId 对应的所有 travellerid 查出来，然后在从 traveller里面查
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{ordersId})")
    public List<Traveller> findByOrdersId(String ordersId) throws Exception;

}
