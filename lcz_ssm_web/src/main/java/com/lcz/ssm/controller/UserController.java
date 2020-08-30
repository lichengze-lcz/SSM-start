package com.lcz.ssm.controller;

//对User用户进行操作，进行增删改查...

import com.lcz.ssm.domain.Role;
import com.lcz.ssm.domain.UserInfo;
import com.lcz.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller   //是一个SpringMvc Controller对象
@RequestMapping("/user")
public class UserController {

    //注入 业务层
    @Autowired
    private IUserService userService;

    //查询用户所有信息
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();

        List<UserInfo> userlist =  userService.findAll();
        mv.addObject("userList",userlist);
        mv.setViewName("user-list");
        return mv;

    }

    //用户添加
    @RequestMapping("save.do")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    //根据id查询用户信息，及对应的角色信息，角色对应的权限信息   （用于用户的详情展示）
    @RequestMapping("findById")
    public ModelAndView  findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;

    }



    //根据参数id 查询户哪个用户在进行添加角色(而展示的页面是，用户没有的角色，可以选择是否添加)
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userid) throws Exception {

        ModelAndView mv = new ModelAndView();
        //1.根据用户id查用户
        UserInfo userInfo = userService.findById(userid);
        //2.根据用户的id查询可以添加的角色
        List<Role> otherRoles = userService.findOtherRoles(userid);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }
    //添加用户
    @RequestMapping("/addRoleToUser.do")
    @Secured("ROLE_USER")//权限控制，只有USER这个角色可以操作
    public String addRoleToUser(@RequestParam(name = "userId",required = true)String userId,@RequestParam(name = "ids",required = true) String[] roleIds){
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }

}
