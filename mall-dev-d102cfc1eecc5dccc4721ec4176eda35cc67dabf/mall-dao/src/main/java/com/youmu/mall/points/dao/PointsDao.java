package com.youmu.mall.points.dao;

import org.apache.ibatis.annotations.Param;

/**
 * 积分数据层
 * 
 * @author Mr.s
 */
public interface PointsDao {

	/**
	 * 根据用户id创建数据
	 * @param userid
	 */
	void insertPointsById(@Param("userid")Long userid);
	
	/**
	 * 根据用户id查询id
	 * @param userid
	 * @return
	 */
	Long getPointsIdById(@Param("userid")Long userid);
	
	/**
	 * 根据用户id查询积分
	 * @param userid 用户id
	 * @return 积分值
	 */
	Long getPointsById(@Param("userid")Long userid);
	
	/**
	 * 根据用户id增加积分
	 * @param userid  用户id
	 * @param figure  增加数值
	 */
	void addPointsById(@Param("userid")Long userid,@Param("figure")Long figure);
	
	/**
	 * 根据用户id扣用积分
	 * @param userid  用户id
	 * @param figure  扣用数值
	 */
	void subPointsById(@Param("userid")Long userid,@Param("figure")Long figure);
	
	
}
