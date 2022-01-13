package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UsernameDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * 处理用户数据的业务层实现类
 *
 * @author xiaoke
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户的注册
     *
     * @param user 用户数据
     */
    @Override
    public void reg(User user) {
        // 根据参数user对象获取注册的用户名
        String username = user.getUsername();
        // 调用持久层的User findByUsername(String username)方法，根据用户名查询用户数据
        User res = userMapper.findByUsername(username);
        // 判断查询结果是否不为null
        // 是：表示用户名已被占用，则抛出UsernameDuplicateException异常
        if (res != null) {
            throw new UsernameDuplicateException("用户名已存在");
        }

        // 创建当前时间对象
        Date now = new Date();
        // 补全数据：加密后的密码
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMd5Password(user.getPassword(), salt);
        user.setPassword(md5Password);
        // 补全数据：盐值
        user.setSalt(salt);
        // 补全数据：isDelete(0)
        user.setIsDelete(0);
        // 补全数据：4项日志属性
        user.setCreatedUser(username);
        user.setCreatedTime(now);
        user.setModifiedUser(username);
        user.setModifiedTime(now);
        // 表示用户名没有被占用，则允许注册
        // 调用持久层Integer insert(User user)方法，执行注册并获取返回值(受影响的行数)
        Integer rows = userMapper.insert(user);
        // 判断受影响的行数是否不为1
        // 是：插入数据时出现某种错误，则抛出InsertException异常
        if (rows != 1) {
            throw new InsertException("添加用户数据时出现未知错误，请重试或联系管理员");
        }
    }

    private String getMd5Password(String password, String salt) {
        /*
          加密规则：
          1、无视原密码的强度
          2、使用UUID作为盐值，在原密码的左右两边拼接
          3、循环加密3次
         */
        int nums = 3;
        for (int i = 0; i < nums; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

}
