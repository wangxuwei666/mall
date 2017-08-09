package com.youmu.mall.user.dao;

import org.apache.ibatis.annotations.Param;

import com.youmu.mall.user.domain.UserShare;

public interface UserShareDao {

	/**
	 * 增加用户分享数据
	 * @param shareUser
	 */
	void insertUserShare(UserShare userShare);
	
	/**
	 * 查找用户分享数据
	 * @param openid
	 * @return
	 */
	UserShare getUserShare(@Param("userId")Long userId,@Param("shareType")Integer shareType);
	
	/**
	 * 更新用户分享次数
	 * @param sharetimes
	 */
	void updateShareTimes(UserShare usershare);
	
}