package com.lcz.ssm.service;

import com.lcz.ssm.domain.Pemission;

import java.util.List;

public interface IPermissionService {

      public List<Pemission> findAll() throws Exception;

      void save(Pemission permission)throws Exception;

}
