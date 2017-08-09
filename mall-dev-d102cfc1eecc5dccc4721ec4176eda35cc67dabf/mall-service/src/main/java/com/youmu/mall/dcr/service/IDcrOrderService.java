/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.dcr.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.youmu.common.page.utils.Page;
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
 * 装修贷款订单的服务实现.
 * @author zh
 * @version $Id: IDCROrderService.java, v 0.1 2017年3月3日 下午7:32:39 zh Exp $
 */
public interface IDcrOrderService {

    /**
     * 查询装修贷款订单
     * @param query
     * @return
     */
    Page<DcrOrderSysListVo> listDcrOrder(DcrOrderQuery query);

    /**
     * 处理用户的装修贷款申请
     * @param id
     */
    void handleDCRRequest(DCROrder order);

    /**
     * 提交合同的数据.
     * @param compact
     * @return
     */
    void updateDcrCompactData(DCRCompact compact);

    /**
     * 装修贷款订单的详情
     * @param id
     * @return
     */
    DCRCompactSysVo dcrCompactDetial(Long id);

    /**
     * 审核合同数据
     * @param id 订单的id
     * @param isPass 是否通过标志位
     * @param remark 备注
     * @return
     */
    void verifiCompact(Long id, Boolean isPass, String remark);

    /**
     * 查询银行装修订单
     * @param query
     * @return
     */
    List<DcrOrderBusBankListVo> listBusBankDcrOrder(DcrOrderBusBankQuery query);

    /**
     * 银行审核合同
     * @param id
     * @param isPass
     * @param remark
     */
    void bankVerifiCompact(Long id, Boolean isPass, String remark);

    /**
     * 查询正在进行中的任务列表.
     * @param query
     * @return
     */
    Page<BusDcrOrderListVo> listBusInProgressDcrOrder(DcrOrderQuery query);

    /**
     * 提交商户的装修流程
     * @param phase 装修流程的id
     */
    void busSubmitDcProgress(DCPhase phase);

    /**
     * 
     * @param query
     * @return
     */
    Page<BusDcrOrderListVo> listBusCompleteDcrOrder(DcrOrderQuery query);

    /**
     * 列出用户装修贷款订单列表
     * @return
     */
    List<UserDcrOrderListVo> listUserDcrOrder(DcrOrderQuery query);

    /**
     * 用户审核装修进度
     * @param id
     * @param isPass
     * @param remark
     */
    void userVerifiDcProgress(Long id, Boolean isPass, String remark);

    /**
     * 系统审核装修进度
     * @param id
     * @param isPass
     * @param remark
     */
    void sysVerifiDcProgress(Long id, Boolean isPass, String remark);

    /**
     * 更新装修打款信息
     * @param id
     * @return
     */
    void updateDcProgressRemitInfo(Long id);

    /**
     * 列出装修流程信息
     */
    List<DCProcessVo> listDcProcessInfo();

    /**
     * 更新装修流程信息
     * @param process
     * @return
     */
    void updateDcProcessInfo(List<DCPhase> process);

    /**
     * 下载财务对账单
     * @param query
     * @param response
     */
    void downLoadDcOrder(DcrOrderDcProgressQuery query, HttpServletResponse response);

    /**
     * 显示操作中的装修订单
     * @param query
     * @return
     */
    Page<DcOrderSysListVo> listDcOrderInOperator(DcrOrderDcProgressQuery query);

    /**
     * 查询已经操作的记录
     * @param query
     * @return
     */
    Page<DcOrderSysListVo>  listDcOrderOperated(DcrOrderDcProgressQuery query);

    /**
     * 查询装修流程时装修的信息
     * @param id
     * @return
     */
    DcOrderSysListVo getDcDcrOrderInfo(Long id);

    /**
     * 查询一个装修订单的阶段
     * @param id
     * @return
     */
    List<DCPhaseUserListVo> getDcrDcOrderPhases(Long id);
}
