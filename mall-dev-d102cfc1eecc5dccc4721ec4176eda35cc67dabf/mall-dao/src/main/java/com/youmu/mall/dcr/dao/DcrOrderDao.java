/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.dcr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youmu.mall.dcr.domain.DCPhase;
import com.youmu.mall.dcr.domain.DCRCompact;
import com.youmu.mall.dcr.domain.DCROrder;
import com.youmu.mall.dcr.query.DcrOrderBusBankQuery;
import com.youmu.mall.dcr.query.DcrOrderDcProgressQuery;
import com.youmu.mall.dcr.query.DcrOrderQuery;
import com.youmu.mall.dcr.vo.BusDcrOrderListVo;
import com.youmu.mall.dcr.vo.DCPhaseUserListVo;
import com.youmu.mall.dcr.vo.DCProcessVo;
import com.youmu.mall.dcr.vo.DCRCompactSysVo;
import com.youmu.mall.dcr.vo.DcOrderSysListVo;
import com.youmu.mall.dcr.vo.DcrOrderBusBankListVo;
import com.youmu.mall.dcr.vo.DcrOrderSysListVo;
import com.youmu.mall.dcr.vo.UserDcrOrderListVo;

/**
 * 装修贷款订单数据访问接口.
 * @author zh
 * @version $Id: DCROrderDao.java, v 0.1 2017年3月2日 下午4:34:31 zh Exp $
 */
public interface DcrOrderDao {

    /**
     * 插入一条装修贷款订单记录.
     * @param order
     */
    public void insertDCROrder(DCROrder order);

    /**
     * 修改装修贷款订单的状态.
     * @param userQrcodeExpire
     * @return 
     */
    public int updateDCROrderByCouponNo(DCROrder order);

    /**
     * 查询系统装修贷款订单的数量.
     * @param query
     * @return
     */
    public Long selectSysDcrOrderCount(DcrOrderQuery query);

    /**
     * 查询系统装修贷款申请订单的系统查询列表.
     * @param query
     * @return
     */
    public List<DcrOrderSysListVo> selectSysDcrOrderList(DcrOrderQuery query);

    /**
     * 后台人员处理装修贷款请求.
     * @param order 装修贷款的订单.
     */
    public void updateDcrRequestOrder(DCROrder order);

    /**
     * 添加合同数据.
     * @param compact
     */
    public void insertDcrCompactData(DCRCompact compact);

    /**
     * 查询订单的状态根据订单号
     * @param orderNo
     * @return
     */
    public Integer selectDcrOrderStatusByOrderNo(String orderNo);

    /**
     * 上传合同数据时修改订单的状态
     * @param dcrOrder
     */
    public void updateOrderBySubmitCompact(DCROrder dcrOrder);

    /**
     * 查询装修贷款订单最新的装修贷款合同数据.
     * @param id
     * @return
     */
    public DCRCompactSysVo selectDcrOrderCompactDetail(Long id);

    /**
     * 更新装修订单在系统审核合同数据时
     * @param id
     * @param isPass
     * @param remark
     */
    public void updateOrderBySysVerifiCompact(DCROrder order);

    /**
     * 根据订单查询订单的状态
     * @param id
     * @return
     */
    public Integer selectDcrOrderStatusById(Long id);

    /**
     * 查询银行审核的订单状态
     * @param query
     * @return
     */
    public List<DcrOrderBusBankListVo> selectBusBankDcrOrderList(DcrOrderBusBankQuery query);

    /**
     * 修改订单信息在银行审核商户时.
     * @param order
     */
    public void updateOrderByBankVerifiCompact(DCROrder order);

    /**
     * 根据装修贷款订单通过id.
     * @param id
     * @return
     */
    public DCROrder selectDcrOrderById(Long id);

    /**
     * 获取所有的装修流程
     * @return
     */
    public List<DCPhase> selectAllDCProcess();

    /**
     * 批量保存装修阶段
     * @param dcPhases
     */
    public void batchInsertDCPhase(@Param("list") List<DCPhase> dcPhases);

    /**
     * 查询装修中的贷款订单数据.
     * @param query 查询对象。
     */
    public List<BusDcrOrderListVo> selectBusInProgressDcrOrderList(DcrOrderQuery query);
    
    /**
     * 查询装修完成的贷款订单数据.
     * @param query 查询对象。
     */
    public List<BusDcrOrderListVo> selectBusCompleteDcrOrderList(DcrOrderQuery query);

    /**
     * 修改装修流程
     * @param phase
     */
    public void updateDCPhase(DCPhase phase);

    /**
     * 查询用户的装修列表.
     * @param query
     * @return
     */
    public List<UserDcrOrderListVo> selectUserDcrOrderList(DcrOrderQuery query);

    /**
     * 查询装修阶段数据通过id
     * @param id
     * @return
     */
    public DCPhase selectDCPhaseById(Long id);

    /**
     * 更新订单信息在打款时.
     * @param order
     */
    public void updateOrderByFinish(DCROrder order);

    /**
     * 查询所有的装修流程
     * @return
     */
    public List<DCProcessVo> selectAllDcProcess();

    /**
     * 修改装休的流程信息
     * @param process
     */
    public void updateDcProcess(@Param("list") List<DCPhase> process);

    /** 
     * 查询当前装修阶段的状态
     */
	public Integer selectDCPhaseStatusById(Long id);

    /**
     * 查询需要
     * @param query
     * @return
     */
    public Long selectSysDcrInOperatorOrderCount(DcrOrderDcProgressQuery query);

    /**
     * 查询装修订单结果.
     * @param query
     * @return
     */
    public List<DcOrderSysListVo> selectSysDcrInOperatorOrderList(DcrOrderDcProgressQuery query);

    /**
     * 查询已经操作的装修订单数量
     * @param query
     * @return
     */
    public Long selectSysDcrOperatoredOrderCount(DcrOrderDcProgressQuery query);

    /**
     * 查询系统已经审核/打款操作订单列表
     * @param query
     * @return
     */
    public List<DcOrderSysListVo> selectSysDcrOperatoredOrderList(DcrOrderDcProgressQuery query);

    public DcOrderSysListVo selectDcDcrOrderInfo(Long id);

    /**
     * 获取商户后台进行中的装修订单数量
     * @param query
     * @return
     */
    public Long selectBusInProgressDcrOrderCount(DcrOrderQuery query);

    /**
     * 订单的id
     * @param id
     * @return
     */
    public List<DCPhaseUserListVo> selectDcrDcOrderPhases(Long id);
    
}
