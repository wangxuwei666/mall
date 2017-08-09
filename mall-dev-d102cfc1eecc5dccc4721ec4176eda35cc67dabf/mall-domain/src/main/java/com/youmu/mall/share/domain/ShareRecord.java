package com.youmu.mall.share.domain;

/**
 * 分享记录
 * @author Mr.s
 *
 */
public class ShareRecord {

	/** id*/
	private Long id;
	
	/** 商品id*/
	private Long productId;
	
	/** 老用户userid*/
	private Long userId;
	
	/** 新用户的openid*/
	private String newOpenId;
	
	public ShareRecord(Long productId, Long userId, String newOpenId) {
		super();
		this.productId = productId;
		this.userId = userId;
		this.newOpenId = newOpenId;
	}

	public ShareRecord() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNewOpenId() {
		return newOpenId;
	}

	public void setNewOpenId(String newOpenId) {
		this.newOpenId = newOpenId;
	}

	
	
}
