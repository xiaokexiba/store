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

    /**
     * 修改密码
     *
     * @param uid         当前登入的用户的id
     * @param username    用户名
     * @param oldPassword 原密码
     * @param newPassword 新密码
     */
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword);

    /**
     * 获取当前登入的用户信息
     *
     * @param uid 当前登入的用户的uid
     * @return 当前登入的用户信息
     */
    User getByUid(Integer uid);

    /**
     * 修改用户的资料
     *
     * @param uid      当前登入的用户uid
     * @param username 当前登入的用户名
     * @param user     用户新的数据
     */
    void changeInfo(Integer uid, String username, User user);
}
