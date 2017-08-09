package com.youmu.mall.points.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youmu.common.context.UserContext;
import com.youmu.mall.points.dao.PointsDao;
import com.youmu.mall.points.service.PointsService;

@Service
public class PointsServiceImpl implements PointsService{

	@Autowired
	private PointsDao pointsDao;
	
	@Override
	public Long getPointsById() {
		Long userid = UserContext.getLongUserId();
		Long figure = pointsDao.getPointsById(userid);
		if(figure != null){
			return figure;
		}
		else{
			pointsDao.insertPointsById(userid);
			return pointsDao.getPointsById(userid);
		}

	}

	@Override
	public void addPointsById(Long userid, Long figure) {
		
	}

	@Override
	public void subPointsById(Long userid, Long figure) {
		
	}

}
