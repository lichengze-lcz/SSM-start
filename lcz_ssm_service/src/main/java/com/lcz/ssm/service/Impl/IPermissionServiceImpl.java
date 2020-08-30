package com.lcz.ssm.service.Impl;

import com.lcz.ssm.dao.IPermissionDao;
import com.lcz.ssm.domain.Pemission;
import com.lcz.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPermissionServiceImpl implements IPermissionService {

    //注入持久层数据
    @Autowired
    private IPermissionDao permissionDao;


    //查找资源权限信息
    @Override
    public List<Pemission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    //添加资源权限信息
    @Override
    public void save(Pemission permission) throws Exception {
        permissionDao.save(permission);
    }
}
