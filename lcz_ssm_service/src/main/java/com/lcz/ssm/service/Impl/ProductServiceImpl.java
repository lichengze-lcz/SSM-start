package com.lcz.ssm.service.Impl;

import com.lcz.ssm.dao.IProductDao;
import com.lcz.ssm.domain.Product;
import com.lcz.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service  //当前是个Service
@Transactional  //事物操作
public class ProductServiceImpl implements IProductService {



    @Autowired //注入类
    public IProductDao productDao;

    @Override
    public List<Product> findAll() throws Exception {

        List<Product> products = productDao.finAll();

        return  products;
    }

    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }
}
