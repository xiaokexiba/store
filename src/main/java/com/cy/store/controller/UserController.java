package com.cy.store.controller;

import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UsernameDuplicateException;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


/**
 * 处理用户相关请求的控制器类
 *
 * @author xiaoke
 */
@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUserService iUserService;

    @RequestMapping("register")
    public JsonResult<Void> register(User user) {
        // 调用业务对象执行注册
        iUserService.register(user);
        // 返回
        return new JsonResult<Void>(OK);
    }

    /**
     * 约定大于配置:开发思想来完成，省略大量的配置甚至注解的编写
     * <p>
     * 1.接收数据方式:请求处理方法的参数列表设置为pojo类型来接收前端的数据，
     * SpringBoot会将前端的url地址中的参数名和pojo类的属性名进行比较，
     * 如果这两个名称项目，则将值注入到pojo类中对应的属性上
     * <p>
     * 2.接收数据方式:请求处理方法的参数列表设置为非pojo类型
     * SpringBoot会直接将请求的参数名和方法的参数名直接进行比较，
     * 如果名称相同则自动完成值的依赖注入
     */
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        // 调用业务对象的方法执行登录，并获取返回值
        User data = iUserService.login(username, password);
        //登录成功后，将uid和username存入到HttpSession中
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());
        System.out.println(getUidFromSession(session));
        System.out.println(getUsernameFromSession(session));
        // 将以上返回值和状态码OK封装到响应结果中并返回
        return new JsonResult<User>(OK, data);
    }
}
