package com.youmu.mall.points.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youmu.mall.points.domain.PointsRecord;
/**
 * 积分记录接口
 * @author Mr.s
 *
 */
public interface PointsRecordDao {

	/**
	 * 创建记录
	 * @param pointsrecord 
	 */
	void insertRecord(PointsRecord pointsrecord);
	
	/**
	 * 更新记录进度
	 * @param progress
	 */
	void updateRecordProgress(@Param("sn")String sn,@Param("progress")int progress);
	
	/**
	 * 取消订单时根据订单sn删除积分记录 
	 * @param sn 
	 */
	void deleteRecordBySn(@Param("sn")String sn);
	
	/**
	 * 查询所有处于返还中的积分记录
	 * @return
	 */
	List<PointsRecord> getPointsRecordsNotDone();
	
	/**
	 * 根据记录id更新返还信息
	 * @param id
	 */
	void updateRecord(PointsRecord pointsRecord);
	
	/**
	 * 根据订单sn获取积分记录进度
	 * @param sn
	 * @return
	 */
	int getProgressBySn(@Param("sn")String sn);
}
 