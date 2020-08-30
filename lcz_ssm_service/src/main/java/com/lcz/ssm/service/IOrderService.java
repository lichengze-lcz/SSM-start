package com.lcz.ssm.service;

import com.lcz.ssm.domain.Orders;

import java.util.List;

//订单业务层接口
public interface IOrderService {

    List<Orders> findAll(int page,int size) throws Exception;


    Orders findById(String ordersId)throws Exception;
}
