package com.youmu.mall.points.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.youmu.common.global.constant.StatusConstant;
import com.youmu.mall.exception.ParamException;
import com.youmu.mall.order.service.impl.CancelOrderTaskService;
import com.youmu.mall.points.dao.PointsDao;
import com.youmu.mall.points.dao.PointsRecordDao;
import com.youmu.mall.points.domain.PointsRecord;

/**
 * 按天返还积分商品的积分
 * @author Mr.s
 *
 */
@Component
public class BackPointsTaskService {
			
    private static Logger logger = LoggerFactory.getLogger(CancelOrderTaskService.class);

    @Resource
	private PointsDao pointsDao;
    
	@Resource
	private PointsRecordDao pointsRecordDao;

	@Scheduled(cron="0 30 4 * * ?")
	public void backPointsByRecord(){
		
		logger.info("****************系统返还积分[start]*******************");
		
	    //查询所有为待返还或返还中的积分记录
		List<PointsRecord> records = pointsRecordDao.getPointsRecordsNotDone();
		//用以保存结算完成的积分记录id
		if(records.size()>0){
			for(PointsRecord record : records){
				if(record == null){
	                throw new ParamException("积分记录不能为空");
	            }
				if(record.getPointsType()!=StatusConstant.ONE){
					throw new ParamException("不是购物返还的积分记录");
				}
				
				Long pointsValue = record.getPointsValue();
				Long balancedAmount = record.getBalancedAmount();
				Long userId = record.getUserId();
				Long figure = new Long(0);
				 int progress = record.getProgress();
				 int backdays = record.getBackdays();
				 int balancedDays = record.getBalancedDays();
				if(!(progress==4||progress==5)){
					throw new ParamException("记录进度错误"); 
		     	}
				if(backdays<=balancedDays){
					throw new ParamException("已返还天数大于等于应返还天数");
				}
				if(pointsValue == null){
					throw new ParamException("pointsValue is null");

				}
				if(balancedAmount == null){
					throw new ParamException("balancedAmount is null");

				}
				if(pointsValue.longValue()<=balancedAmount.longValue()){
					throw new ParamException("已返积分大于等于应返还积分");
				}
				//积分整除天数时正常返还 除不尽时 最后一天把剩下的值一起返还
				if(backdays-balancedDays==1){
						figure = pointsValue/backdays + pointsValue%backdays;
				}else{
						figure = pointsValue/backdays;
				 	}
				pointsDao.addPointsById(userId, figure);
				balancedDays = balancedDays + 1 ;
				balancedAmount = balancedAmount + figure;
				
				if(balancedDays == 1){
					record.setProgress(5);
				}
				if(balancedAmount.equals(pointsValue)&&balancedDays == backdays){
					record.setProgress(6);
				}
				
				record.setBalancedAmount(balancedAmount);
				record.setBalancedDays(balancedDays);
				pointsRecordDao.updateRecord(record);
			}
		}
		logger.info("****************系统返还积分[end]*******************");
	}
}
