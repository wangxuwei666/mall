package com.youmu.mall.web.bus.front.filter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.youmu.common.context.TokenContext;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.token.utils.TokenUtils;
import com.youmu.mall.redis.login.dao.RedisLoginDao;

public class AuthorizationFilter implements  HandlerInterceptor {
    
  @Resource
  RedisLoginDao redisLoginDao;

  private Logger logger = LoggerFactory.getLogger(getClass());
  

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    boolean flag = false;
    String errMsg = "";
    response.setContentType("application/json;charset=UTF-8");
    String token = TokenUtils.getTokenInHeader(request);
    token = token == null ? TokenUtils.getTokenInCookie(request, TokenUtils.BUS_FRONT_TOKEN_SUFFIX) : token;
    logger.debug("token content is {}.", token);
    if(token == null) {
        errMsg = "请重新登录";
    } else {
      try {
        String uid = TokenUtils.getUidInToken(token);
        logger.debug("now auth uid is {}.", uid);
        if(redisLoginDao.validateToken(uid, token)){
            request.setAttribute("uid", uid);
            redisLoginDao.resetLoginExpireTime(uid, TokenContext.SESSION_EXPIRE);
            flag = true;
        }else {
            redisLoginDao.logout(uid);
            errMsg = "你的账号在别处登录，请修改密码";
        }
      } catch (Exception e) {
        logger.error("用户认证失败：", e);
        errMsg = "请重新登录";
      }
    }
    if(!flag) {
        // 删除cookie里的token
        TokenUtils.removeTokenInCookie(response, TokenUtils.BUS_FRONT_TOKEN_SUFFIX);
        response.getWriter().print(JSON.toJSONString(JsonResultUtils.fail(JsonResultUtils.AUTH_EXCEPTION, errMsg)));
    }
    return flag;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
  }
}
