package com.cy.store.service;

import com.cy.store.entity.Address;

import java.util.List;

/**
 * 处理收货地址数据的业务层接口
 *
 * @author xiaoke
 */
public interface IAddressService {

    /**
     * 添加新的收货地址
     *
     * @param uid      当前登入的用户的uid
     * @param username 当前登入的用户名
     * @param address  用户提交的收货地址数据
     */
    void addNewAddress(Integer uid, String username, Address address);

    /**
     * 查询某用户的收货地址列表数据
     *
     * @param uid 收货地址归属的用户id
     * @return 该用户的收货地址列表数据
     */
    List<Address> getByUid(Integer uid);

    /**
     * 设置登入用户的默认收货地址
     *
     * @param aid      收货地址的id
     * @param uid      该用户的id
     * @param username 该用户的用户名
     */
    void setDefault(Integer aid, Integer uid, String username);

    /**
     * 删除收货地址
     *
     * @param uid      归属的用户id
     * @param aid      收货地址id
     * @param username 归属的用户的用户名
     */
    void delete(Integer uid, Integer aid, String username);
}
