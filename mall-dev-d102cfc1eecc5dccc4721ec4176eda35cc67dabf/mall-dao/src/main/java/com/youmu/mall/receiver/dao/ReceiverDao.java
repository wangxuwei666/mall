/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.receiver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youmu.mall.receiver.domain.Receiver;
import com.youmu.mall.receiver.vo.AddressVo;
import com.youmu.mall.receiver.vo.ReceiverVO;

/**
 * 收货人数据层
 * @author yujiahao
 * @version $Id: ReceiverDao.java, v 0.1 2017年3月3日 上午10:37:29 yujiahao Exp $
 */
public interface ReceiverDao {

    List<ReceiverVO> findReceiver(@Param("userId")Long userId,@Param("isDefault")Integer isDefault,@Param("receiveId")Long receiveId);

    void cancelDefault(@Param("userId")Long userId);

    List<AddressVo> getCity(Integer provinceCode);

    List<AddressVo> getDistrict(Integer cityCode);

    List<AddressVo> getProvince();
    
    /**
     * 保存收货人地址
     * @param receiver
     * @return
     */
    long save(Receiver receiver);

    /**
     * 修改收货人地址
     * @param receiver
     */
    void update(Receiver receiver);

    /**
     * 删除收货人
     * @param id
     * @param userId
     */
    void deleteById(@Param("receiverId")Long id,@Param("userId")Long userId);

}
