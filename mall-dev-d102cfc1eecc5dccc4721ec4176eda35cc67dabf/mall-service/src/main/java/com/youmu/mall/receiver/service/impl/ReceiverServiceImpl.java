package com.youmu.mall.receiver.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youmu.common.context.UserContext;
import com.youmu.mall.receiver.dao.ReceiverDao;
import com.youmu.mall.receiver.domain.Receiver;
import com.youmu.mall.receiver.service.IReceiverService;
import com.youmu.mall.receiver.vo.AddressVo;
import com.youmu.mall.receiver.vo.ReceiverVO;

@Service
public class ReceiverServiceImpl implements IReceiverService {

    @Autowired
    private ReceiverDao receiverDao;
    
	@Override
	public List<ReceiverVO> findReceiver(Integer isDefault,Long receiveId) {
		return receiverDao.findReceiver(UserContext.getLongUserId(),isDefault,receiveId);
	}

	@Override
	public long saveReceiver(Receiver receiver) {
	    receiver.setUserId(UserContext.getLongUserId());
		if(receiver.getIsDefault() == 1){
			receiverDao.cancelDefault(UserContext.getLongUserId());
		}
		return receiverDao.save(receiver);
	}

	@Override
	public void updateReceiver(Receiver receiver) {
	    receiver.setUserId(UserContext.getLongUserId());
		if(receiver.getIsDefault() == 1){
			receiverDao.cancelDefault(UserContext.getLongUserId());
		}
		receiverDao.update(receiver);
	}

	@Override
	public void deleteReceiver(Long id) {
		receiverDao.deleteById(id,UserContext.getLongUserId());
	}

	@Override
	public List<AddressVo> getCity(Integer provinceCode) {
		return receiverDao.getCity(provinceCode);
	}

	@Override
	public List<AddressVo> getDistrict(Integer cityCode) {
		return receiverDao.getDistrict(cityCode);
	}

	@Override
	public List<AddressVo> getProvince() {
		return receiverDao.getProvince();
	}

    




}
