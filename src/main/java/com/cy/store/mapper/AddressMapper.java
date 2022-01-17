package com.cy.store.mapper;

import com.cy.store.entity.Address;

/**
 * @author xiaoke
 */
public interface AddressMapper {
    /**
     * 插入收货地址数据
     *
     * @param address 收货地址数据
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /**
     * 查看某用户的收货地址的数量
     *
     * @param uid 用户的uid
     * @return 返回该用户的收货地址的数量
     */
    Integer countByUid(Integer uid);
}
