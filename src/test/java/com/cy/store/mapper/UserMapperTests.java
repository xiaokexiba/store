package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith：表示启动这个单元测试类(单元测试类是不能够运行的)，需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
//@SpringBootTest：表示标注当前的类是一个测试类，不会随同项目一起打包
@SpringBootTest
public class UserMapperTests {
    //idea有检测功能，接口本身是不能够创建Bean的（动态代理），
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
    public void insert() {
        User user = new User();
        user.setUsername("tom");
        user.setPassword("123");
        Integer res = userMapper.insert(user);
        System.out.println(res);
    }

    @Test
    public void findByUsername() {
        User user = userMapper.findByUsername("tom");
        System.out.println(user);
    }
}