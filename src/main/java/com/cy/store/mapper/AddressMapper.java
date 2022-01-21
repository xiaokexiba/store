package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 处理收货地址的持久层接口
 *
 * @author xiaoke
 */
public interface AddressMapper {
    /**
     * 插入收货地址数据
     *
     * @param address 收货地址数据
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /**
     * 查看某用户的收货地址的数量
     *
     * @param uid 用户的uid
     * @return 返回该用户的收货地址的数量
     */
    Integer countByUid(Integer uid);

    /**
     * 查询某用户的收货地址列表数据
     *
     * @param uid 收货地址归属的用户id
     * @return 该用户的收货地址列表数据
     */
    List<Address> findByUid(Integer uid);

    /**
     * 将该用户所有的收货地址设置为非默认地址
     *
     * @param uid 收货地址归属的用户id
     * @return 受影响的行数
     */
    Integer updateNonDefaultByUid(Integer uid);

    /**
     * 将指定的收货地址设置为默认地址
     *
     * @param aid          指定的收货地址id
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateDefaultByAid(@Param("aid") Integer aid,
                               @Param("modifiedUser") String modifiedUser,
                               @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据收货地址aid值，查询收货地址详情
     *
     * @param aid 收货地址id值
     * @return 该id对应的收货地址详情，没有则返回null
     */
    Address findByAid(Integer aid);

    /**
     * 根据收货地址id，删除数据
     *
     * @param aid 收货地址id
     * @return 受影响的行数
     */
    Integer deleteByAid(Integer aid);

    /**
     * 查询某用户的最后修改的收货地址
     *
     * @param uid 该用户的id
     * @return 该用户最后修改的收货地址，没有则返回null
     */
    Address findLastModified(Integer uid);
}
