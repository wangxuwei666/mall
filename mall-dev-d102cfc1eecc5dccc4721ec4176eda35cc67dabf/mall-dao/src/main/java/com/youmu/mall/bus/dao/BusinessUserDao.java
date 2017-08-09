/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.bus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youmu.mall.user.domain.BusinessUser;
import com.youmu.mall.user.query.BusinessUserQuery;
import com.youmu.mall.user.vo.BusinessUserListVo;
import com.youmu.mall.user.vo.LoggedBusinessUserVo;

/**
 * 商户用户数据访问接口.
 * @author zh
 * @version $Id: BusinessUserDao.java, v 0.1 2017年2月28日 上午11:07:28 zh Exp $
 */
public interface BusinessUserDao {

    /**
     * 查询商户用户的重复数量.
     * @param businessUser
     * @return
     */
    Long selectBusinessUserRepeatCount(BusinessUser businessUser);

    /**
     * 添加一个商户用户.
     * @param businessUser
     */
    void insertBusinessUser(BusinessUser businessUser);

    /**
     * 修改商户的账户
     * @param businessUser
     */
    void updateBusinessUser(BusinessUser businessUser);

    /**
     * 删除一个商户用户
     * @param id
     */
    void deleteBusinessUser(Long id);

    /**
     * 分页查询分页的数量
     * @param query
     * @return
     */
    Long selectBusinessUserCount(BusinessUserQuery query);

    /**
     * 分页查询商户列表
     * @param query
     * @return
     */
    List<BusinessUserListVo> selectBusinessUserList(BusinessUserQuery query);

    /**
     * 查询商户用户的登录信息.
     * @param account
     * @return
     */
    BusinessUser selectBusinessUserLoginInfo(String account);

    /**
     * 根据商户的ID查询登录了的商户的信息
     * @param longUserId
     * @return
     */
    LoggedBusinessUserVo selectLoggedBusinessInfo(Long longUserId);
    

    /**
     * 修改商户的登录密码
     * @param id 商户的id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    int updateBusinessUserPassword(@Param("id") Long id, @Param("oldPassword") String oldPassword, @Param("newPassword") String newPassword);

    /**
     * 获取商户用户的id
     * @param id
     * @return
     */
    BusinessUser getBusinessUserById(Long id);
}
