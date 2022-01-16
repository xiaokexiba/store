package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

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

    /**
     * 根据UID更改用户密码
     *
     * @param uid          用户的id
     * @param password     新密码
     * @param modifiedUser 最后的修改人
     * @param modifiedTime 最后修改的时间
     * @return 受影响的行数
     */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     * 根据UID查询用户数据
     *
     * @param uid 用户的id
     * @return 匹配的用户数据，没有匹配就返回null
     */
    User findByUid(Integer uid);

    /**
     * 根据uid更改用户资料
     *
     * @param user 封装了用户uid和新个人资料的对象
     * @return 受影响的行数
     */
    Integer updateInfoByUid(User user);

    /**
     * 根据uid更新用户头像
     *
     * @param uid          用户的uid
     * @param avatar       新头像的路径
     * @param modifiedUser 最后的修改人
     * @param modifiedTime 修改的时间
     * @return 受影响的行数
     */
    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime") Date modifiedTime);

}
