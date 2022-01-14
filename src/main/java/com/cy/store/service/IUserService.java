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
    void register(User user);

    /**
     * 用户登入功能
     *
     * @param username 用户名
     * @param password 密码
     * @return 当前匹配的用户数据，如果没有就返回null
     */
    User login(String username, String password);
}
