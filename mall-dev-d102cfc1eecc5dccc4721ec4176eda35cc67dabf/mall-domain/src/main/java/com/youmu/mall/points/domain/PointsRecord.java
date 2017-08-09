package com.youmu.mall.points.domain;

import com.youmu.mall.base.domain.BaseDomain;

/**
 * 积分交易记录
 * @author Mr.s
 *
 */
@SuppressWarnings("serial")
public class PointsRecord extends BaseDomain{
	
	/**记录id*/
	private Long id;
	
	/**积分表id*/
	private Long pointsId;
	
	/**用户id*/
	private Long userId;
	
	/**商品id*/
	private Long productId;
	
	/**订单sn*/
	private String orderSn;
	
	/**积分获取的途径：1-购买 ..*/
	private int pointsType;
	
	/**积分值*/
	private Long pointsValue; 
	
	/**已结算积分值*/
	private Long  balancedAmount;
	
	/**返还天数*/
	private int backdays;
	
	/**已返还天数*/
	private int balancedDays;

	/**进度跟踪*/
	private int progress;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPointsId() {
		return pointsId;
	}

	public void setPointsId(Long pointsId) {
		this.pointsId = pointsId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public int getPointsType() {
		return pointsType;
	}

	public void setPointsType(int pointsType) {
		this.pointsType = pointsType;
	}

	public Long getPointsValue() {
		return pointsValue;
	}

	public void setPointsValue(Long pointsValue) {
		this.pointsValue = pointsValue;
	}

	public Long getBalancedAmount() {
		return balancedAmount;
	}

	public void setBalancedAmount(Long balancedAmount) {
		this.balancedAmount = balancedAmount;
	}

	public int getBackdays() {
		return backdays;
	}

	public void setBackdays(int backdays) {
		this.backdays = backdays;
	}

	public int getBalancedDays() {
		return balancedDays;
	}

	public void setBalancedDays(int balancedDays) {
		this.balancedDays = balancedDays;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}
	

	
	
}
