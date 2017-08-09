package com.youmu.mall.points.service;


/**
 * 积分记录服务层
 * @author Mr.s
 *
 */
public interface PointsRecordService {

	/**
	 * 根据订单sn更新积分记录进度
	 * @param sn
	 */
	void updateRecordProgress(String sn);
	
	/**
	 * 根据订单sn获取积分记录进度
	 * @param sn
	 * @return
	 */
	int getProgressBySn(String sn);
}
