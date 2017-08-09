package com.youmu.mall.points.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youmu.common.global.constant.StatusConstant;
import com.youmu.mall.exception.ParamException;
import com.youmu.mall.points.dao.PointsRecordDao;
import com.youmu.mall.points.service.PointsRecordService;

@Service
public class PointsRecordServiceImpl implements PointsRecordService{

	@Autowired
	private PointsRecordDao pointsRecordDao;

	@Override
	public void updateRecordProgress(String sn) {
		
		int progress = pointsRecordDao.getProgressBySn(sn);
		
		if( progress >= 4){
			throw new ParamException("您已成功申请返还积分");
		}
		
		pointsRecordDao.updateRecordProgress(sn, StatusConstant.FOUR);
	
	}

	@Override
	public int getProgressBySn(String sn) {
		return pointsRecordDao.getProgressBySn(sn);
	}
}
