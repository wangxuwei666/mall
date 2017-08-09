/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.receiver.service;

import java.util.List;

import com.youmu.mall.receiver.domain.Receiver;
import com.youmu.mall.receiver.vo.AddressVo;
import com.youmu.mall.receiver.vo.ReceiverVO;

/**
 * 收货人地址服务层接口
 * @author yujiahao
 * @version $Id: IReceiverService.java, v 0.1 2017年3月3日 下午4:20:13 yujiahao Exp $
 */
public interface IReceiverService {
    List<ReceiverVO> findReceiver(Integer isDefault,Long receiveId);
    
    long saveReceiver(Receiver receiver);
    
    void updateReceiver(Receiver receiver);
    
    void deleteReceiver(Long id);
    
    List<AddressVo> getCity(Integer provinceCode);
    
    List<AddressVo> getDistrict(Integer cityCode);
    
    List<AddressVo> getProvince();
    
}
