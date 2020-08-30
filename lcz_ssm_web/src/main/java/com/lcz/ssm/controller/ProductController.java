package com.lcz.ssm.controller;


import com.lcz.ssm.domain.Product;
import com.lcz.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

//main页面控制器
@Controller    //是一个SpringMvc Controller对象
@RequestMapping("/product")
public class ProductController {


    @Autowired  //注入资源
    private IProductService productService;

    //查询全部订单

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();  //把查到的东西放到  ModelAndView中

        List<Product> ps = productService.findAll();
        mv.addObject("productList",ps); //域对象 名字
        mv.setViewName("product-list1");   //跳转jsp视图名字
        return mv;
    }


    //产品添加
    @RequestMapping("/save.do")
    @Secured("ROLE_USER")//权限控制，只有USER这个角色可以操作
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll.do";
    }

}
