package com.cy.store.service;

import com.cy.store.entity.User;

/**
 * 处理用户数据的业务层接口
 *
 * @author xiaoke
 */
public interface IUserService {

    /**
     * 用户的注册
     *
     * @param user 用户数据
     */
    void reg(User user);
}
