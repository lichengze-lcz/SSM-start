package com.lcz.ssm.service.Impl;


import com.lcz.ssm.dao.IUserDao;
import com.lcz.ssm.domain.UserInfo;
import com.lcz.ssm.domain.Role;
import com.lcz.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {
    //实现了IUserService 接口，间接实现了 UserDetailsService


    @Autowired
    private IUserDao userDao;

    //把用户名进行验证
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserInfo userInfo = null;
        try {
             userInfo = userDao.findByUsername(s);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //处理自己的用户对象，封装成UserDetaills   框架底层可以 对 username password进行验证
        //用户名和密码 封装到 User里
//        User user = new User (userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAutority(userInfo.getRoles()));
                                                                                            //true 账户可用
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAutority(userInfo.getRoles()));
        return user;
    }



    //作用：返回一个list集合。集合中装入角色描述
    public List<SimpleGrantedAuthority> getAutority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for(Role role:roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName())); // 防止AccessDeniedException: Access is denied
        }
        return list;
    }


    //查询用户所有信息
    @Override
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    @Autowired //注入
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //添加用户，并且密码进行加密
    @Override
    public void save(UserInfo userInfo) throws Exception {
        System.out.println(userInfo.getPassword());
        System.out.println(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        //加密处理
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));

        userDao.save(userInfo);
    }

    //查询用户id
    @Override
    public UserInfo findById(String id) throws Exception {
        return  userDao.findById(id);

    }

    //查询用户·其他角色
    @Override
    public List<Role> findOtherRoles(String userid) throws Exception {
        return userDao.findOtherRoles(userid);
    }
    //添加用户角色
    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        //循环添加
        for (String roleId:roleIds){
            userDao.addRoleToUser(userId,roleId);
        }
    }
}
