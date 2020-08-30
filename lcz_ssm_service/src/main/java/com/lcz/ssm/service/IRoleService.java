package com.lcz.ssm.service;

import com.lcz.ssm.domain.Pemission;
import com.lcz.ssm.domain.Role;

import java.util.List;

//角色操作 业务层
public interface IRoleService {

    public List<Role>findAll()throws Exception;


    void save(Role role)throws Exception;

    Role findById(String id)throws Exception;

    void deleteRoleById(String roleId)throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds);

    List<Pemission> findOtherPermissions(String roleId);
}
