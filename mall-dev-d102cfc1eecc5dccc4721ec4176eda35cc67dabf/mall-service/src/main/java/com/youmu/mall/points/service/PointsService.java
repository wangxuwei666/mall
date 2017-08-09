package com.youmu.mall.points.service;

/**
 * 积分操作服务层
 * @author Mr.s
 *
 */
public interface PointsService {

	/**
	 * 根据用户id查询对应积分
	 * @param userid
	 * @return
	 */
	Long getPointsById();
	
	/**
	 * 根据用户id增加积分
	 * @param userid
	 * @param figure  积分数值
	 */
	void addPointsById(Long userid,Long figure);
	
	/**
	 * 根据用户id扣用积分
	 * @param userid
	 * @param figure 积分数值
	 */
	void subPointsById(Long userid,Long figure);
}
