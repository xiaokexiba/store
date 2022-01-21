package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 处理购物车数据的持久层接口
 *
 * @author xiaoke
 */
public interface CartMapper {

    /**
     * 插入购物车数据
     *
     * @param cart 购物车数据
     * @return 受影响的行数
     */
    Integer insert(Cart cart);

    /**
     * 修改购物车数据中商品数量
     *
     * @param cid          购物车数据id
     * @param num          购物车中的商品数量
     * @param modifiedUser 修改者
     * @param modifiedTime 修改时间
     * @return
     */
    Integer updateNumByCid(@Param("cid") Integer cid,
                           @Param("num") Integer num,
                           @Param("modifiedUser") String modifiedUser,
                           @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据用户id和商品id查询购物车中的数据
     *
     * @param uid 用户id
     * @param pid 商品id
     * @return 匹配的购物车数据，没有则返回null
     */
    Cart findByUidAndPid(@Param("uid") Integer uid,
                         @Param("pid") Integer pid);
}
