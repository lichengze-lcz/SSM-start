package com.lcz.ssm.service;

import com.lcz.ssm.domain.Product;

import java.util.List;

//业务层接口
public interface IProductService {

    public List<Product> findAll() throws Exception;

    void save(Product product) throws Exception;
}
