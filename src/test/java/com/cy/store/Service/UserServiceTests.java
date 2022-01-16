package com.cy.store.Service;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

//@RunWith：表示启动这个单元测试类(单元测试类是不能够运行的)，需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
//@SpringBootTest：表示标注当前的类是一个测试类，不会随同项目一起打包
@SpringBootTest
public class UserServiceTests {
    //idea有检测功能，接口本身是不能够创建Bean的（动态代理），
    @Autowired
    private IUserService iUserService;
    @Autowired
    private UserMapper userMapper;

    /**
     * 单元测试方法：就可以单独独立运行，不用启动整个项目，可以做单元测试，提升了代码的测试效率
     * 1、必须被@Test注解修饰
     * 2、返回值必须是void
     * 3、方法的参数列表不能指定任何类型
     * 4、方法的访问修饰符必须是public
     */
    @Test
    public void register() {
        try {
            User user = new User();
            user.setUsername("tim");
            user.setPassword("123");
            user.setGender(1);
            user.setPhone("17858802974");
            user.setEmail("lower@tedu.cn");
            iUserService.register(user);
            System.out.println("注册成功！");
        } catch (ServiceException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login() {
        User user = iUserService.login("tim", "123");
        System.out.println(user);
    }

    @Test
    public void updateAvatarByUid() {
        Integer uid = 1;
        String avatar = "/upload/avatar.png";
        String modifiedUser = "超级管理员";
        Date modifiedTime = new Date();
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, modifiedUser, modifiedTime);
        System.out.println("rows=" + rows);
    }
    @Test
    public void changeAvatar() {
        try {
            Integer uid = 20;
            String username = "头像管理员";
            String avatar = "/upload/avatar.png";
            iUserService.changeAvatar(uid, username, avatar);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
