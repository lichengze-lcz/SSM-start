package com.lcz.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.lcz.ssm.domain.Orders;
import com.lcz.ssm.service.IOrderService;
import com.lcz.ssm.util.DateStringEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

//订单控制器
@Controller  //是一个SpringMvc Controller对象
@RequestMapping("/orders")
public class OrderControler {

    @Autowired//注入业务层数据
    private IOrderService orderService;


    //类型转换
//    @InitBinder
//    public void  initBinder(WebDataBinder binder){
//        //注册一个转换器              转换的类型   转换器对象
//        binder.registerCustomEditor(Date.class,new DateStringEditor());
//
//    }

    //查询全部订单，未分页
//    @RequestMapping(path = "/findAll.do")
//    public ModelAndView findAll() throws Exception {
//
//        ModelAndView mv = new ModelAndView();
//        List<Orders> ordersList = orderService.findAll();  //订单查询得到一集合
//        mv.addObject("ordersList",ordersList);
//        mv.setViewName("orders-list");
//        return mv;
//    }


    //查询全部订单，未分页
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam (name = "page",required = true,defaultValue="1") Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "4") Integer size) throws Exception {

        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = orderService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(ordersList);        //是一个写好的pageBean
        mv.addObject("pageinfo",pageInfo);
        mv.setViewName("orders-page-list");

        return mv;
    }

    //订单详细信息查询
    @RequestMapping("/findById.do")
    @Secured("ROLE_USER")//权限控制，只有USER这个角色可以操作
    public  ModelAndView findById(@RequestParam (name = "id",required = true)String ordersId) throws Exception {

        ModelAndView mv = new ModelAndView();
        Orders orders = orderService.findById(ordersId);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");

        return mv;
    }

}
