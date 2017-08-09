package com.youmu.mall.bus.dao;

import java.util.Collection;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.youmu.mall.bus.domain.BussnessBusstype;
import com.youmu.mall.bus.vo.BussnessBussVO;

public interface BussnessBusstypeDao {
	
    int deleteByPrimaryKey(Integer id);

    int insert(BussnessBusstype record);

    int insertSelective(BussnessBusstype record);

    BussnessBusstype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BussnessBusstype record);

    int updateByPrimaryKey(BussnessBusstype record);

    /**
     * 插入多个商户类型
     * @param id
     * @param businessTypeSet
     * @return
     */
    int insertBusinessBusstyps(@Param("bussnessId") Long id,@Param("businessTypeSet") Collection<Integer> businessTypeSet);

    int getBussnessTypeByIdAndBussnessTpyeId(@Param("bussnessId") Long bussnessId,@Param("bussnessTpyeIdNew")  Integer bussnessTpyeIdNew);

    void updateBussnessBussType(@Param("bussnessId") Long bussnessId,@Param("bussnessTpyeIdOld") Integer bussnessTpyeIdOld,@Param("bussnessTpyeIdNew") Integer bussnessTpyeIdNew);

    void deleteBusinessTypeByBussnessIdAndBussnessTpyeId(@Param("bussnessId") Long bussnessId,@Param("bussnessTpyeId") Integer bussnessTpyeId);

    /**
     * 获取一组商户类型数据
     * @param id
     * @return
     */
    List<BussnessBussVO> BussnessBussVOList(@Param("bussnessId") Long bussnessId);
    
   // void updateBussnessBussTypeById(@Param("bussnessId") Long bussnessId);   
    
    
    
}

