package com.lcz.ssm.service.Impl;

import com.github.pagehelper.PageHelper;
import com.lcz.ssm.dao.IOrdersDao;
import com.lcz.ssm.domain.Orders;
import com.lcz.ssm.service.IOrderService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//订单 业务层实现类接口
@Service  //加入IOC容器
@Transactional //事物
public class OrdersServiceImpl implements IOrderService {

    @Autowired  //注入  持久层数据
    private IOrdersDao ordersDao;


    @Override //查询  分页
    public List<Orders> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);   //从哪页开始，每页几条（必须写在查询语句的前一页）
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String ordersId) throws Exception {
        return ordersDao.findById(ordersId);
    }
}
