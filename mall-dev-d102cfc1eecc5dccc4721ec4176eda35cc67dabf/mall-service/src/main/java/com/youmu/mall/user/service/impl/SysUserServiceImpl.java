/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.user.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youmu.common.context.GlobalConstant;
import com.youmu.common.context.TokenContext;
import com.youmu.common.context.UserContext;
import com.youmu.common.digest.utils.MD5Util;
import com.youmu.common.page.utils.Page;
import com.youmu.common.token.utils.JwtUtils;
import com.youmu.common.token.utils.TokenUtils;
import com.youmu.mall.base.query.PageQuery;
import com.youmu.mall.exception.BusinessException;
import com.youmu.mall.redis.login.dao.RedisLoginDao;
import com.youmu.mall.sys.domain.SysMenu;
import com.youmu.mall.sys.utils.SysMenuUtil;
import com.youmu.mall.user.dao.SysUserDao;
import com.youmu.mall.user.domain.SysUser;
import com.youmu.mall.user.service.ISysUserService;
import com.youmu.mall.user.vo.SysUserListVo;

/**
 * 系统用户接口实现类.
 * @author zh
 * @version $Id: SysUserServiceImpl.java, v 0.1 2017年2月27日 下午7:42:08 zh Exp $
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Resource
    private SysUserDao sysUserDao;
    
    @Resource
    private RedisLoginDao redisLoginDao;

    /** 
     * @see com.youmu.mall.user.service.ISysUserService#login(com.youmu.mall.user.domain.SysUser)
     */
    @Override
    public SysUser login(String account, String password, HttpServletResponse response) {
        //  查询系统用户的登录信息
        SysUser u = sysUserDao.selectSysUserLoginInfo(account);
        if(u == null) {
            throw new BusinessException("账号不存在");
        }
        if(!StringUtils.equals(u.getPassword(), MD5Util.MD5(password))) {
            throw new BusinessException("密码错误");
        }
        if(u.getStatus() != 0) {
            throw new BusinessException("用户已经被禁用");
        }
        String token = JwtUtils.createToken(u.getId().toString(), TokenContext.TOKEN_EXPIRE);
        // 将用户登录信息放在redis中
        redisLoginDao.login(u.getId().toString(), token, TokenContext.SESSION_EXPIRE,  u);
        // 将token放入cookie
        TokenUtils.setTokenInCookie(response, TokenUtils.SYS_TOKEN_SUFFIX, token);
        // 不返回密码
        u.setPassword(null);
        // 菜单树处理
        List<SysMenu> menus = u.getMenus();
        // 没有菜单
        if(u.getMenus() == null || menus.isEmpty()) {
            throw new BusinessException("你没有权限登录,请联系管理员!");
        }
        u.setMenus(SysMenuUtil.convertSysMenusToTree(menus));
        return u;
    }

    /** 
     * @see com.youmu.mall.user.service.ISysUserService#logout()
     */
    @Override
    public void logout() {
        redisLoginDao.logout(UserContext.getUserId());
    }

    /** 
     * @see com.youmu.mall.user.service.ISysUserService#addSysUser(com.youmu.mall.user.domain.SysUser)
     */
    @Override
    public void addSysUser(SysUser user) {
        long count = sysUserDao.selectSysUserRepeatCount(user);
        if(count > 0) {
           throw new BusinessException("账号已经存在");
        }
        user.setPassword(MD5Util.MD5(user.getPassword()));
        sysUserDao.insertSysUser(user);
    }

    /** 
     * @see com.youmu.mall.user.service.ISysUserService#listSysUser(com.youmu.mall.base.query.PageQuery)
     */
    @Override
    public Page<SysUserListVo> listSysUser(PageQuery query) {
        Page<SysUserListVo> page = new Page<>(query.getPageNum(), query.getPageSize());
        page.setTotal(sysUserDao.selectSysUserCount(query));
        if(page.getTotal() > 0) {
            page.setData(sysUserDao.selectSysUserList(query));
        }
        return page;
    }

    /** 
     * @see com.youmu.mall.user.service.ISysUserService#disableSysUser(java.lang.Long)
     */
    @Override
    public void disableSysUser(Long id) {
        logger.debug("{} disable user : {}.", UserContext.getUserId(), id);
        sysUserDao.updateSysUserStatus(id, GlobalConstant.UserStatus.DISABLE);
    }

    /** 
     * @see com.youmu.mall.user.service.ISysUserService#enableSysUser(java.lang.Long)
     */
    @Override
    public void enableSysUser(Long id) {
        logger.debug("{} enable user : {}.", UserContext.getUserId(), id);
        sysUserDao.updateSysUserStatus(id, GlobalConstant.UserStatus.ENABLE);
    }

    /** 
     * @see com.youmu.mall.user.service.ISysUserService#updateSysUser(com.youmu.mall.user.domain.SysUser)
     */
    @Override
    public void updateSysUser(SysUser user) {
        long count = sysUserDao.selectSysUserRepeatCount(user);
        if(count > 0) {
           throw new BusinessException("账号已经存在");
        }
        logger.debug("{} update user {}.", UserContext.getUserId(), user.getId());
        user.setPassword(null);
        sysUserDao.updateSysUser(user);
    }

    /** 
     * @see com.youmu.mall.user.service.ISysUserService#resetSysUserPassword(java.lang.Long, java.lang.String, java.lang.String)
     */
    @Override
    public void resetSysUserPassword(Long operatorUseId, String userId, String password) {
        logger.info("{} reset sys user {} passowrd to {}", operatorUseId, userId, password);
        sysUserDao.updateSysUserPassword(userId, MD5Util.MD5(password));
    }

    /** 
     * @see com.youmu.mall.user.service.ISysUserService#deleteSysUser(java.lang.Long, java.lang.Long)
     */
    @Override
    public void deleteSysUser(Long operator, Long id) {
        logger.info("{} delete user {}.", operator, id);
        sysUserDao.deleteSysUserById(id);
    }

    /** 
     * @see com.youmu.mall.user.service.ISysUserService#getUpdateSysUser(java.lang.Long)
     */
    @Override
    public SysUserListVo getUpdateSysUser(Long id) {
        return sysUserDao.selectUpdateSysUser(id);
    }

    /** 
     * @see com.youmu.mall.user.service.ISysUserService#resetPassword(java.lang.Long, java.lang.String, java.lang.String)
     */
    @Transactional
    @Override
    public void resetPassword(Long userId, String oldPassword, String newPassword) {
        int row = sysUserDao.changePassowrd(userId, MD5Util.MD5(oldPassword), MD5Util.MD5(newPassword));
        if(row != 1) {
            throw new BusinessException("原密码有误");
        }
    }
}

