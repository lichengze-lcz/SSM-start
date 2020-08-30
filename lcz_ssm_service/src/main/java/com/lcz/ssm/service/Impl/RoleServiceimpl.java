package com.lcz.ssm.service.Impl;

import com.lcz.ssm.dao.IRoleDao;
import com.lcz.ssm.domain.Pemission;
import com.lcz.ssm.domain.Role;
import com.lcz.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service        //交给spring管理
@Transactional  //事物操作
public class RoleServiceimpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;



    //查询所有角色信息
    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.finAll();
    }

    //添加角色
    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public Role findById(String id) throws Exception {
        return roleDao.findById(id);
    }

    @Override
    public void deleteRoleById(String roleId) throws Exception {

        //从role_permission表中删除
        roleDao.deleteFromRole_PermissionByRoleId(roleId);
        //从role表中删除
        roleDao.deleteRoleById(roleId);
        //从user_role表中删除（最后删）
        roleDao.deleteFromUser_RoleByRoleId(roleId);

    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for(String permissionId:permissionIds){
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
    //通过roleid 查询出对应permissionid，进行添加操作
    @Override
    public List<Pemission> findOtherPermissions(String roleId) {
        return roleDao.findOtherPermissions(roleId);
    }


}
