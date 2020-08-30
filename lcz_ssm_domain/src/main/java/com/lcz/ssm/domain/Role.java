package com.lcz.ssm.domain;

import org.springframework.security.core.userdetails.User;

import java.security.acl.Permission;
import java.util.List;

//角色实体类
public class Role {

    private String id;
    private String roleName;                //角色名称
    private String roleDesc;                //角色描述
    private List<Permission> permissions;   //###资源权限信息
    private List<User> users;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
