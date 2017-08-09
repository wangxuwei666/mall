package com.youmu.mall.dcr.dao;

import java.util.List;
import com.youmu.mall.dcr.domain.DCRRegister;
import com.youmu.mall.dcr.query.DcrRegisterQuery;

/**
 * 家装登记接口.
 * @author wxw
 */
public interface DcrRegisterDao {
	
	/**
	 * 插入一条家装登记接口
	 * @param userd
	 */
	void insertUserD(DCRRegister userd);
	
	/**
	 * 查询家装登记列表
	 * @param dcrRegisterQuery
	 * @return
	 */
    List<DCRRegister> getDcrRegisterByType(DcrRegisterQuery dcrRegisterQuery);
	
    /**
     * 查询总数
     * @param dcrRegisterQuery
     * @return
     */
	long getCountByType(DcrRegisterQuery dcrRegisterQuery);
	
}
