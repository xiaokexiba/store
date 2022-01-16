package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UsernameDuplicateException;
import com.cy.store.util.JsonResult;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.http.fileupload.impl.FileUploadIOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


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
        // 将以上返回值和状态码OK封装到响应结果中并返回
        return new JsonResult<User>(OK, data);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        //调用session.getAttribute("")获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行修改密码
        iUserService.changePassword(uid, username, oldPassword, newPassword);
        // 返回成功
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
        // 从HttpSession对象中获取uid
        Integer uid = getUidFromSession(session);
        // 调用业务对象执行获取数据
        User data = iUserService.getByUid(uid);
        // 响应成功和数据
        return new JsonResult<User>(OK, data);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        // 从HttpSession对象中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行修改用户资料
        iUserService.changeInfo(uid, username, user);
        // 响应成功
        return new JsonResult<Void>(OK);
    }

    /**
     * 头像文件大小的上限值(10MB)
     */
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    /**
     * 允许上传的头像的文件类型
     */
    public static final List<String> AVATAR_TYPES = new ArrayList<String>();

    /*
     * 初始化允许上传的头像的文件类型
     */
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }

    /**
     * MultipartFile接口是SpringMVC提供一个接口，这个接口为我们包装了获取文件类型的数据
     * (任何类型的file都可以接收)，SpringBoot它有整合了SpringMVC，只需要在处理请求的方法
     * 参数列表上声明一个参数类型为MultipartFile的参数，然后SpringBoot自动将传递给服务的
     * 文件数据赋值赋值给这个参数
     *
     * @param session
     * @param file
     * @return
     * @RequestParam 表示请求中的参数，将请求中的参数注入请求处理方法的某个参数上，
     * 如果名称不一致则可以使用@RequestParam注解进行标记和映射
     */
    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session,
                                           @RequestParam("file") MultipartFile file) {
        // 判断上传的文件是否为空
        if (file.isEmpty()) {
            // 是：抛出异常
            throw new FileEmptyException("上传头像不允许为空");
        }
        // 判断上传的文件大小是否超出限制值
        // getSize()：返回文件的大小，以字节为单位
        if (file.getSize() > AVATAR_MAX_SIZE) {
            // 是：抛出异常
            throw new FileSizeException("不允许上传超过" + (AVATAR_MAX_SIZE / 1024) + "KB的头像文件");
        }
        // 判断上传的文件类型是否超出限制
        String contentType = file.getContentType();
        // public boolean list.contains(Object o)：
        // 当前列表若包含某元素，返回结果为true；若不包含该元素，返回结果为false。
        if (!AVATAR_TYPES.contains(contentType)) {
            // 是：抛出异常
            throw new FileTypeException("不支持使用该类型的文件作为头像，允许的文件类型：\n" + AVATAR_TYPES);
        }
        // 获取当前项目的绝对磁盘路径
        String parent = session.getServletContext().getRealPath("upload");
        // 保存头像文件的文件夹
        File dir = new File(parent);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 保存的头像文件的文件名
        String suffix = "";
        String originalFilename = file.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        String filename = UUID.randomUUID().toString() + suffix;

        // 创建文件对象，表示保存的头像文件
        File dest = new File(dir, filename);
        // 执行保存头像文件
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            // 抛出异常
            throw new FileStateException("文件状态异常，可能文件已被移动或删除");
        } catch (IOException e) {
            // 抛出异常
            throw new FileUploadIoException("上传文件时读写错误，请稍后重尝试");
        }

        // 头像路径
        String avatar = "/upload/" + filename;
        // 从Session中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 将头像写入到数据库中
        iUserService.changeAvatar(uid, username, avatar);

        // 返回成功头像路径
        return new JsonResult<String>(OK, avatar);
    }
}
