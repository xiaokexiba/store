package com.sy.store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class StoreApplicationTests {

    @Autowired//自动装配
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }

    /**
     * HikariProxyConnection@2067586671 wrapping com.mysql.cj.jdbc.ConnectionImpl@6d091cad
     *
     * @throws SQLException
     */
    @Test
    void getConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }
}
