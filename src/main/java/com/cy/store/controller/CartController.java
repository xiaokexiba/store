package com.cy.store.controller;

import com.cy.store.service.ICartService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 处理购物车数据的控制器类
 *
 * @author xiaoke
 */
@RequestMapping("carts")
@RestController
public class CartController extends BaseController {

    @Autowired
    private ICartService iCartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session) {
        //从session中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        //调用业务对象执行添加购物车
        iCartService.addToCart(uid, pid, amount, username);
        return new JsonResult<>(OK);
    }
}
