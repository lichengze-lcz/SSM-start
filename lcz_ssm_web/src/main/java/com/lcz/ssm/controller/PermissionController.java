package com.lcz.ssm.controller;

import com.lcz.ssm.domain.Pemission;
import com.lcz.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//实现资源权限的管理
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    //查询资源权限
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {

        ModelAndView mv = new ModelAndView();
        List<Pemission> permissionList = permissionService.findAll();
        mv.addObject("permissionList",permissionList);
        mv.setViewName("permission-list");
        return mv;
    }

    //添加资源权限信息
    @RequestMapping("/save.do")
    public String save(Pemission permission) throws Exception {

        permissionService.save(permission);
        return "redirect:findAll.do";
    }
}
