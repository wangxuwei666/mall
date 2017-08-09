package com.youmu.mall.user.domain;

/**
 * 用户分享
 * @author Mr.s
 *
 */
public class UserShare {

	/** id*/
	private Long id;
	
	/** 用户id*/
	private Long userId;
	
	/** 分享类型(1-积分商品分享)*/
	private Integer shareType;
	
	/** 分享次数*/
	private Integer shareTimes;
	
	public UserShare() {
		super();
	}

	public UserShare(Long userId, Integer shareType) {
		super();
		this.userId = userId;
		this.shareType = shareType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getShareType() {
		return shareType;
	}

	public void setShareType(Integer shareType) {
		this.shareType = shareType;
	}

	public Integer getShareTimes() {
		return shareTimes;
	}

	public void setShareTimes(Integer shareTimes) {
		this.shareTimes = shareTimes;
	}
		
}