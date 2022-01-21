package com.cy.store.mapper;

import com.cy.store.entity.Product;

import java.util.List;

/**
 * 处理商品数据的持久层接口
 *
 * @author xiaoke
 */
public interface ProductMapper {
    /**
     * 查询热销商品的前四名
     *
     * @return 热销商品前四名的集合
     */
    List<Product> findHotList();

    /**
     * 根据商品id查询商品详情
     *
     * @param id 商品id
     * @return 商品详细信息
     */
    Product findById(Integer id);
}
