package com.lcz.ssm.service.Impl;

import com.lcz.ssm.dao.ISysLogDao;
import com.lcz.ssm.domain.SysLog;
import com.lcz.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//AOP日志 业务层接口
@Service
@Transactional
public class ISysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() throws Exception {
        return sysLogDao.findAll();
    }

}
