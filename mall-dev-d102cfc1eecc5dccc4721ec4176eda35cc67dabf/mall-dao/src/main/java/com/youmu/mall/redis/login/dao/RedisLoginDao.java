/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.redis.login.dao;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.youmu.mall.base.domain.BaseDomain;

/**
 * 登录缓存工具.
 * 
 * @author zh
 * @version $Id: LoginUtils.java, v 0.1 2017年2月25日 下午5:16:56 zh Exp $
 */
@Component
public class RedisLoginDao {
        
        @Resource
        private  RedisTemplate<String, String> redisTemplate;
        
        /** 登录token信息  */
        private  final String TOKEN_PREFIEX = "online:token:";
        
        /** 登录的用户信息 */
        private  final String LOGGED_PREFIX = "online:logged:";
        
        /** 用户注册时验证码前缀 */
        private final String  REGIST_AUTH_PREFIX = "reg:auth:";
        
        /** 用户重置密码时验证码前缀 */
        private final String  RESTP_AUTH_PREFIX = "restp:auth:";
        
        /** 用户重置手机号码时新手机验证码前缀 */
        private final String  RESTM_AUTH_PREFIX = "restm:auth:";
        
        /** 用户重置手机号码时旧手机验证码前缀 */
        private final String  RESTMO_AUTH_PREFIX = "restmo:auth:";
        
        public void login(String uid, String token, int timeOut, BaseDomain user) {
            redisTemplate.opsForValue().set(TOKEN_PREFIEX + uid, token, timeOut, TimeUnit.MILLISECONDS);
            redisTemplate.opsForValue().set(LOGGED_PREFIX + uid, JSON.toJSONString(user),timeOut, TimeUnit.MILLISECONDS);
        }
        
        /**
         * 
         * @Title: logout
         * @Description: 退出
         * @param @param sessionId
         * @return void
         * @throws
         */
        public  void logout(String uid){
            redisTemplate.opsForValue().getOperations().delete(Arrays.asList(TOKEN_PREFIEX + uid, LOGGED_PREFIX + uid));
        }
        
        /**
         * 检查用户的token是否正确.
         * @param uid
         * @param token
         * @return
         */
        public boolean validateToken(String uid, String token) {
            return StringUtils.equals(redisTemplate.opsForValue().get(TOKEN_PREFIEX + uid), token);
        }
        
        /**
         * 获取已经登录的用户信息.
         * 
         * @param uid
         * @param clz
         * @return
         */
        public <T> T getLoggedUserInfo(String uid, Class<T> clz) {
            return JSON.parseObject(redisTemplate.opsForValue().get(LOGGED_PREFIX + uid), clz);
        }

        /**
         * 保存注册的验证码
         * @param mobile 手机号码
         * @param authCode 验证码
         * @param seconds 保存的时间
         */
        public void saveRegistAuthCode(String mobile, String authCode, int expire) {
            redisTemplate.opsForValue().set(REGIST_AUTH_PREFIX + mobile, authCode, expire, TimeUnit.MILLISECONDS);
        }

        /**
         * 获取用户注册的验证码
         * @param mobile
         * @return
         */
        public String getRegistAuthCode(String mobile) {
            return redisTemplate.opsForValue().get(REGIST_AUTH_PREFIX + mobile);
        }
        
        /**
         * 删除注册的验证码
         * @param mobile
         */
        public void deleteRegistAuthCode(String mobile) {
            redisTemplate.opsForValue().getOperations().delete(REGIST_AUTH_PREFIX + mobile);
        }

        /**
         * 保存重置密码时的验证码
         * @param mobile 用户手机号码
         * @param authCode 验证码
         * @param seconds 过期 秒
         */
        public void saveResetPasswordAuthCode(String mobile, String authCode, int milliseconds) {
            redisTemplate.opsForValue().set(RESTP_AUTH_PREFIX + mobile, authCode, milliseconds, TimeUnit.MILLISECONDS);
        }
        
        /**
         * 获取用户重置密码的验证码
         * @param mobile 用户手机号码
         * @return
         */
        public String getResetPasswordAuthCode(String mobile) {
            return redisTemplate.opsForValue().get(RESTP_AUTH_PREFIX + mobile);
        }
        
        /**
         * 删除重置密码，缓存的验证码
         * @param mobile
         */
        public void deleteResetPasswordAuthCode(String mobile) {
            redisTemplate.opsForValue().getOperations().delete(RESTP_AUTH_PREFIX + mobile);
        }

        /**
         * 保存重置手机号码的验证码
         * @param uid 用户id
         * @param authCode 验证码
         * @param seconds 秒
         */
        public void saveResetMobileAuthCode(String mobile, String authCode, int miliseconds) {
            redisTemplate.opsForValue().set(RESTM_AUTH_PREFIX + mobile, authCode, miliseconds, TimeUnit.MILLISECONDS);
        }

        /**
         * 获取重置手机号码的验证码.
         * @param mobile mobile手机号码
         * @return
         */
        public String getResetMobileAuthCode(String mobile) {
            return redisTemplate.opsForValue().get(RESTM_AUTH_PREFIX + mobile);
        }

        /**
         * 删除重置手机号码的验证码
         * @param id
         */
        public void deleteResetMobileAuthCode(String mobile) {
            redisTemplate.opsForValue().getOperations().delete(RESTM_AUTH_PREFIX + mobile);
        }

        /**
         * 重置手机号码时向旧手机号码发送的验证码
         * @param string
         * @param authCode
         * @param i
         */
        public void saveOldResetMobileAuthCode(String mobile, String authCode, int miliseconds) {
            redisTemplate.opsForValue().set(RESTMO_AUTH_PREFIX + mobile, authCode, miliseconds, TimeUnit.MILLISECONDS);
        }
        
        /**
         * 重置手机号码时向旧手机号码发送的验证码
         * @param uid 用户id
         */
        public String getOldResetMobileAuthCode(String mobile) {
            return redisTemplate.opsForValue().get(RESTMO_AUTH_PREFIX + mobile);
        }
        
        /**
         * 重置手机号码时向旧手机号码发送的验证码
         */
        public void deleteOldResetMobileAuthCode(String mobile) {
            redisTemplate.opsForValue().getOperations().delete(RESTMO_AUTH_PREFIX + mobile);
        }

        /**
         * token在访问时重置时间.
         * @param uid
         */
        public void resetLoginExpireTime(String uid, int timeOut) {
            redisTemplate.boundValueOps(TOKEN_PREFIEX + uid).expire(timeOut, TimeUnit.MILLISECONDS);
            redisTemplate.boundValueOps(LOGGED_PREFIX + uid).expire(timeOut, TimeUnit.MILLISECONDS);
        }
}
