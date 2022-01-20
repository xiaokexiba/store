package com.cy.store.Service;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IAddressService;
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
public class AddressServiceTests {
    //idea有检测功能，接口本身是不能够创建Bean的（动态代理），
    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private IAddressService iAddressService;

    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(1);
        address.setName("admin");
        address.setPhone("17858802974");
        address.setAddress("雁塔区小寨赛格");
        Integer rows = addressMapper.insert(address);
        System.out.println("rows = " + rows);
    }

    @Test
    public void countByUid() {
        Integer count = addressMapper.countByUid(1);
        System.out.println("count = " + count);
    }

    @Test
    public void addNewAddress() {
        try {
            Integer uid = 2;
            String username = "管理员";
            Address address = new Address();
            address.setName("张三");
            address.setPhone("17858805555");
            address.setAddress("雁塔区小寨华旗");
            iAddressService.addNewAddress(uid, username, address);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void setDefault() {
        try {
            Integer aid = 5;
            Integer uid = 2;
            String username = "系统管理员";
            iAddressService.setDefault(aid, uid, username);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
