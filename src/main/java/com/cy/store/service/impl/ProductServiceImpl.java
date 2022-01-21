package com.cy.store.service.impl;

import com.cy.store.entity.Product;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.IProductService;
import com.cy.store.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 处理商品数据的业务层接口实现类
 *
 * @author xiaoke
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询热销商品前四名
     *
     * @return 热销商品前四名的集合
     */
    @Override
    public List<Product> findHotList() {
        List<Product> list = productMapper.findHotList();
        for (Product product : list) {
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }

    /**
     * 根据商品id查询商品详情
     *
     * @param pid 商品id
     * @return 匹配的商品详情，如果没有匹配的数据则返回null
     */
    @Override
    public Product findById(Integer pid) {
        Product product = productMapper.findById(pid);
        if (product == null) {
            throw new ProductNotFoundException("尝试访问的商品数据不存在");
        }
        //将查询结果的部分属性设置为null
        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);
        //返回查询结果
        return product;
    }
}
