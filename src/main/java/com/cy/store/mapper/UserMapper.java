package com.cy.store.mapper;

import com.cy.store.entity.User;

/**
 * 处理用户数据操作的持久层接口
 *
 * @author xiaoke
 */
public interface UserMapper {
    /**
     * 插入用户数据
     *
     * @param user 用户数据
     * @return 受影响的行数
     */
    Integer insert(User user);

    /**
     * 根据用户名查询用户数据
     *
     * @param username 用户名
     * @return 如果匹配成功就返回匹配的用户数据，如果没有成功就返回null
     */
    User findByUsername(String username);
}
