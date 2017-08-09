/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.dcr.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.CharEncoding;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youmu.common.context.GlobalConstant;
import com.youmu.common.context.UserContext;
import com.youmu.common.excel.utils.ExcelUtils;
import com.youmu.common.page.utils.Page;
import com.youmu.mall.dcr.dao.DcrOrderDao;
import com.youmu.mall.dcr.domain.DCPhase;
import com.youmu.mall.dcr.domain.DCRCompact;
import com.youmu.mall.dcr.domain.DCROrder;
import com.youmu.mall.dcr.query.DcrOrderBusBankQuery;
import com.youmu.mall.dcr.query.DcrOrderDcProgressQuery;
import com.youmu.mall.dcr.query.DcrOrderQuery;
import com.youmu.mall.dcr.service.IDcrOrderService;
import com.youmu.mall.dcr.vo.BusDcrOrderListVo;
import com.youmu.mall.dcr.vo.DCPhaseUserListVo;
import com.youmu.mall.dcr.vo.DCProcessVo;
import com.youmu.mall.dcr.vo.DCRCompactSysVo;
import com.youmu.mall.dcr.vo.DcOrderSysListVo;
import com.youmu.mall.dcr.vo.DcrOrderBusBankListVo;
import com.youmu.mall.dcr.vo.DcrOrderSysListVo;
import com.youmu.mall.dcr.vo.UserDcrOrderListVo;
import com.youmu.mall.exception.BusinessException;
import com.youmu.mall.exception.ParamException;
import com.youmu.mall.redis.login.dao.RedisLoginDao;
import com.youmu.mall.user.domain.BusinessUser;
import com.youmu.mall.user.domain.SysUser;
import com.youmu.mall.user.domain.User;

/**
 * 装修贷款订单的实现类.
 * @author zh
 * @version $Id: DCROrderServiceImpl.java, v 0.1 2017年3月3日 下午7:34:02 zh Exp $
 */
@Service
public class DcrOrderServiceImpl implements IDcrOrderService {
    
    @Resource
    private DcrOrderDao dcrOrderDao;
    
    @Resource
    private RedisLoginDao redisLoginDao;

    /** 
     * @see com.youmu.mall.dcr.service.IDcrOrderService#listDcrOrder(com.youmu.mall.dcr.query.DcrOrderQuery)
     */
    @Override
    public Page<DcrOrderSysListVo> listDcrOrder(DcrOrderQuery query) {
        Page<DcrOrderSysListVo> page = new Page<>(query.getPageNum(), query.getPageSize());
        page.setTotal(dcrOrderDao.selectSysDcrOrderCount(query));
        if(page.getTotal() > 0) {
            page.setData(dcrOrderDao.selectSysDcrOrderList(query));
        }
        return page;
    }

    /** 
     * @see com.youmu.mall.dcr.service.IDcrOrderService#handleDCRRequest(java.lang.Long)
     */
    @Override
    public void handleDCRRequest(DCROrder order) {
        // 直接修改订单的标志 位   GlobalConstant.DCROrderStatus.SYS_PROCESSING
        // 需不需要给用装修贷款用户，或者申请的用户发送提醒?
        Integer status = dcrOrderDao.selectDcrOrderStatusById(order.getId());
        // 如果该状态为用户申请
        if(status ==  GlobalConstant.DCROrderStatus.USER_APPLY) {
            order.setOrderStatus(GlobalConstant.DCROrderStatus.SYS_PROCESSING);
            order.setGmtHandle(new Date());
            order.setHandleUser(redisLoginDao.getLoggedUserInfo(UserContext.getUserId(), SysUser.class));
            dcrOrderDao.updateDcrRequestOrder(order);
        }
    }

    /** 
     * @see com.youmu.mall.dcr.service.IDcrOrderService#updateDcrCompactData(com.youmu.mall.dcr.domain.DCRCompact)
     */
    @Transactional
    @Override
    public void updateDcrCompactData(DCRCompact compact) {
        // 查询当前订单的状态
        Integer status = dcrOrderDao.selectDcrOrderStatusByOrderNo(compact.getOrderNo());
        if(status != null && (status.equals(GlobalConstant.DCROrderStatus.USER_USE_QRCODE) 
                || status.equals(GlobalConstant.DCROrderStatus.SYS_CONTRACT_REJECT)
                || status.equals(GlobalConstant.DCROrderStatus.BANK_AUDIT_REJECT)
                )) {
            // 将万元转换为元
            compact.setTotalAmount(compact.getTotalAmount().multiply(new BigDecimal(10000)));
            compact.setSubmitUser(redisLoginDao.getLoggedUserInfo(UserContext.getUserId(), BusinessUser.class));
            compact.setGmtSubmit(new Date());
            // 更新装修订单合同数据
            dcrOrderDao.insertDcrCompactData(compact);
            // 修改主订单的状态和金额
            DCROrder dcrOrder = new DCROrder();
            dcrOrder.setOrderNo(compact.getOrderNo());
            dcrOrder.setCompact(compact);
            dcrOrder.setTotalAmount(compact.getTotalAmount());
            dcrOrder.setOrderStatus(GlobalConstant.DCROrderStatus.BUSINESS_SUBMIT_COMPACT);
            dcrOrderDao.updateOrderBySubmitCompact(dcrOrder);
        } else {
            throw new BusinessException("不能提交合同数据");
        }
    }

    /** 
     * @see com.youmu.mall.dcr.service.IDcrOrderService#dcrCompactDetial(java.lang.Long)
     */
    @Override
    public DCRCompactSysVo dcrCompactDetial(Long id) {
        return dcrOrderDao.selectDcrOrderCompactDetail(id);
    }

    /** 
     * @see com.youmu.mall.dcr.service.IDcrOrderService#verifiCompact(java.lang.Long, java.lang.Boolean, java.lang.String)
     */
    @Override
    public void verifiCompact(Long id, Boolean isPass, String remark) {
        Integer status = dcrOrderDao.selectDcrOrderStatusById(id);
        if(status != null && status.equals(GlobalConstant.DCROrderStatus.BUSINESS_SUBMIT_COMPACT)) {
            DCROrder order = new DCROrder();
            order.setId(id);
            order.setOrderStatus(isPass ? GlobalConstant.DCROrderStatus.SYS_AUDIT_CONTRACT : GlobalConstant.DCROrderStatus.SYS_CONTRACT_REJECT);
            order.setGmtSysVerifi(new Date());
            order.setSysVerifiRremark(remark);
            order.setSysVerifiUser(redisLoginDao.getLoggedUserInfo(UserContext.getUserId(), SysUser.class));
            dcrOrderDao.updateOrderBySysVerifiCompact(order);
        } else {
            throw new BusinessException("不能修改该订单状态");
        }
    }

    /** 
     * @see com.youmu.mall.dcr.service.IDcrOrderService#listBusBankDcrOrder(com.youmu.mall.dcr.query.DcrOrderBusBankQuery)
     */
    @Override
    public List<DcrOrderBusBankListVo> listBusBankDcrOrder(DcrOrderBusBankQuery query) {
        query.setBusinessId(redisLoginDao.getLoggedUserInfo(UserContext.getUserId(), BusinessUser.class).getBusiness().getId());
        return dcrOrderDao.selectBusBankDcrOrderList(query);
    }    

    /** 
     * @see com.youmu.mall.dcr.service.IDcrOrderService#bankVerifiCompact(java.lang.Long, java.lang.Boolean, java.lang.String)
     */
    @Transactional
    @Override
    public void bankVerifiCompact(Long id, Boolean isPass, String remark) {
        Integer status = dcrOrderDao.selectDcrOrderStatusById(id);
        if(status != null && status.equals(GlobalConstant.DCROrderStatus.SYS_AUDIT_CONTRACT)) {
            DCROrder order = new DCROrder();
            order.setId(id);
            order.setOrderStatus(isPass ? GlobalConstant.DCROrderStatus.BUSINESS_DECORATING : GlobalConstant.DCROrderStatus.BANK_AUDIT_REJECT);
            order.setGmtBankVerifi(new Date());
            order.setBankVerifiRemark(remark);
            order.setBankVerifiUser(redisLoginDao.getLoggedUserInfo(UserContext.getUserId(), BusinessUser.class));
            dcrOrderDao.updateOrderByBankVerifiCompact(order);
            // 订单状态修改成功后 如果同意
            if(isPass) {
                // 生成装修所有步骤信息
                DCROrder dcrOrder = dcrOrderDao.selectDcrOrderById(id);
                // 获取所有的装修流程按照id 排序
                List<DCPhase> dcPhases = dcrOrderDao.selectAllDCProcess();
                // 填充每一阶段的初始数据
                if(dcPhases != null && !dcPhases.isEmpty()) {
                    int i = 1;
                    for (DCPhase dcPhase : dcPhases) {
                        dcPhase.setAmount(dcPhase.getProportion().multiply(dcrOrder.getTotalAmount()));
                        dcPhase.setStatus(GlobalConstant.DecorateStatus.WAIT_SUBMIT);
                        dcPhase.setOrder(dcrOrder);
                        dcPhase.setProgress(i+ GlobalConstant.DC_PHASE_SEPEATOR + dcPhases.size());
                        dcPhase.setBusBankAccount(dcrOrder.getVerifiBusiness().getBankAccount());
                        dcPhase.setBusBankName(dcrOrder.getVerifiBusiness().getBankName());
                        dcPhase.setBusBankUserName(dcrOrder.getVerifiBusiness().getBankUsername());
                        i++;
                    }
                    dcrOrderDao.batchInsertDCPhase(dcPhases);
                }
            }
        } else {
            throw new BusinessException("不能修改该订单状态");
        }
    }

    /** 
     * @see com.youmu.mall.dcr.service.IDcrOrderService#listBusInProgressDcrOrder(com.youmu.mall.dcr.query.BusDcrOrderQuery)
     */
    @Override
    public Page<BusDcrOrderListVo> listBusInProgressDcrOrder(DcrOrderQuery query) {
        BusinessUser user = redisLoginDao.getLoggedUserInfo(UserContext.getUserId(), BusinessUser.class);
        Page<BusDcrOrderListVo> page = new Page<>(query.getPageNum(), query.getPageSize());
        query.setOrderStatus(GlobalConstant.DCROrderStatus.BUSINESS_DECORATING);
        query.setGmtOrder("o.gmt_sysverifi desc");
        query.setBusinessId(user.getBusiness().getId());
        page.setTotal(dcrOrderDao.selectBusInProgressDcrOrderCount(query));
        if(page.getTotal() > 0) {
            page.setData(dcrOrderDao.selectBusInProgressDcrOrderList(query));
        }
        return page;
    }

    /** 
     * @see com.youmu.mall.dcr.service.IDcrOrderService#busSubmitDcProgress(java.lang.Long)
     */
    @Override
    public void busSubmitDcProgress(DCPhase phase) {
    	Integer status = dcrOrderDao.selectDCPhaseStatusById(phase.getId());
    	if(status == null) {
    		throw new BusinessException("装修进度不存在");
    	}
    	// 如果已经提交过了还没有审核完成 则不能提交进度
    	if(status.compareTo(GlobalConstant.DecorateStatus.WAIT_VERIFI) >= 0 ) {
    		throw new BusinessException("当前不能提交进度");
    	}
        // 修改装修贷款进度为已经提交
        phase.setStatus(GlobalConstant.DecorateStatus.WAIT_VERIFI);
        phase.setSubmitUser(redisLoginDao.getLoggedUserInfo(UserContext.getUserId(), BusinessUser.class));
        phase.setGmtSubmit(new Date());
        // 设置用户和后台的审核状态为待审核
        phase.setSysVerifiStatus(GlobalConstant.DCPhaseVerifiStatus.WAIT_VERIFI);
        phase.setUserVerifiStatus(GlobalConstant.DCPhaseVerifiStatus.WAIT_VERIFI);
        dcrOrderDao.updateDCPhase(phase);
    }

    /** 
     * @see com.youmu.mall.dcr.service.IDcrOrderService#listBusCompleteDcrOrder(com.youmu.mall.dcr.query.DcrOrderQuery)
     */
    @Override
    public Page<BusDcrOrderListVo> listBusCompleteDcrOrder(DcrOrderQuery query) {
        Page<BusDcrOrderListVo> page = new Page<>(query.getPageNum(), query.getPageSize());
        query.setOrderStatus(GlobalConstant.DCROrderStatus.ORDER_FINISHED);
        query.setGmtOrder("order by o.gmt_finish desc");
        query.setBusinessId(redisLoginDao.getLoggedUserInfo(UserContext.getUserId(), BusinessUser.class).getBusiness().getId());
        page.setTotal(dcrOrderDao.selectSysDcrOrderCount(query));
        if(page.getTotal() > 0) {
            page.setData(dcrOrderDao.selectBusCompleteDcrOrderList(query));
        }
        return page;
    }

    /** 
     * @see com.youmu.mall.dcr.service.IDcrOrderService#listUserDcrOrder(com.youmu.mall.dcr.query.DcrOrderQuery)
     */
    @Override
    public List<UserDcrOrderListVo> listUserDcrOrder(DcrOrderQuery query) {
        query.setUserId(UserContext.getLongUserId());
        return dcrOrderDao.selectUserDcrOrderList(query);
    }

    /** 
     * @see com.youmu.mall.dcr.service.IDcrOrderService#userVerifiDcProgress(java.lang.Long, java.lang.Boolean, java.lang.String)
     */
    @Transactional
    @Override
    public void userVerifiDcProgress(Long id, Boolean isPass, String remark) {
        DCPhase phase = null;
        phase = dcrOrderDao.selectDCPhaseById(id);
        if(phase == null) {
        	throw new BusinessException("该装修进度不存在");
        }
        // 如果已经审核通过了不能再审核
        if(phase.getStatus().compareTo(GlobalConstant.DecorateStatus.WAIT_REMIT) >= 0) {
        	throw new BusinessException("已经审核通过了");
        }
        if(isPass) {
           // 通过判断两个审核状态是否都通过 都通过 则修该阶段的状态为待放款 status = 2 用户审核状态 3 后台审核状态 3
           phase.setUserVerifiStatus(GlobalConstant.DCPhaseVerifiStatus.PASSED);
           if(phase.getSysVerifiStatus().equals(GlobalConstant.DCPhaseVerifiStatus.PASSED)) {
               phase.setStatus(GlobalConstant.DecorateStatus.WAIT_REMIT);
           }
        } else {
           // 如果不通过则重置流程的状态  status = 0 用户审核状态为 0 后台的审核状态为0
           phase = new DCPhase();
           phase.setId(id);
           phase.setUserVerifiStatus(GlobalConstant.DCPhaseVerifiStatus.NOT_PASSED);
           phase.setSysVerifiStatus(GlobalConstant.DCPhaseVerifiStatus.NOT_PASSED);
           phase.setUserVerifiRemark("");
           phase.setSysVerifiRemark("");
           phase.setStatus(GlobalConstant.DecorateStatus.WAIT_SUBMIT);
        }
       phase.setVerifiUser(redisLoginDao.getLoggedUserInfo(UserContext.getUserId(), User.class));
       phase.setGmtUserVerifi(new Date());
       phase.setUserVerifiRemark(remark);
       dcrOrderDao.updateDCPhase(phase);
    }

    /** 
     * @see com.youmu.mall.dcr.service.IDcrOrderService#sysVerifiDcProgress(java.lang.Long, java.lang.Boolean, java.lang.String)
     */
    @Transactional
    @Override
    public void sysVerifiDcProgress(Long id, Boolean isPass, String remark) {
        DCPhase phase = null;
        phase = dcrOrderDao.selectDCPhaseById(id);
        if(phase == null) {
        	throw new BusinessException("该装修进度不存在");
        }
        // 如果已经审核通过了不能再审核
        if(phase.getStatus().compareTo(GlobalConstant.DecorateStatus.WAIT_REMIT) >= 0) {
        	throw new BusinessException("已经审核通过了");
        }
        if(isPass) {
            // 通过判断两个审核状态是否都通过 都通过 则修该阶段的状态为待放款 status = 2 用户审核状态 3 后台审核状态 3
            phase.setSysVerifiStatus(GlobalConstant.DCPhaseVerifiStatus.PASSED);
            if(phase.getUserVerifiStatus().equals(GlobalConstant.DCPhaseVerifiStatus.PASSED)) {
                phase.setStatus(GlobalConstant.DecorateStatus.WAIT_REMIT);
            }
        } else {
            // 如果不通过则重置流程的状态  status = 0 用户审核状态为 0 后台的审核状态为0
            phase = new DCPhase();
            phase.setId(id);
            phase.setUserVerifiStatus(GlobalConstant.DCPhaseVerifiStatus.NOT_PASSED);
            phase.setSysVerifiStatus(GlobalConstant.DCPhaseVerifiStatus.NOT_PASSED);
            phase.setUserVerifiRemark("");
            phase.setSysVerifiRemark("");
            phase.setStatus(GlobalConstant.DecorateStatus.WAIT_SUBMIT);
        }
        phase.setSysVerifiUser(redisLoginDao.getLoggedUserInfo(UserContext.getUserId(), SysUser.class));
        phase.setGmtSysVerifi(new Date());
        phase.setSysVerifiRemark(remark);
        dcrOrderDao.updateDCPhase(phase);
    }

    /** 
     * @see com.youmu.mall.dcr.service.IDcrOrderService#updateDcProgressRemitInfo(java.lang.Long, java.lang.Boolean, java.lang.String)
     */
    @Transactional
    @Override
    public void updateDcProgressRemitInfo(Long id) {
        // 查询状态位 判断状态位是否正确
        DCPhase dcPhase = dcrOrderDao.selectDCPhaseById(id);
        if(dcPhase.getSysVerifiStatus().equals(GlobalConstant.DCPhaseVerifiStatus.PASSED) && dcPhase.getUserVerifiStatus().equals(GlobalConstant.DCPhaseVerifiStatus.PASSED)) {
            // 通过审核
            // 修改打款信息
            DCPhase dcp = new DCPhase();
            dcp.setId(id);
            dcp.setGmtRemit(new Date());
            dcp.setSysRemitUser(redisLoginDao.getLoggedUserInfo(UserContext.getUserId(), SysUser.class));
            dcp.setStatus(GlobalConstant.DecorateStatus.FINISH);
            dcrOrderDao.updateDCPhase(dcp);
            // 判断当前的进度是否是最后一步
            String progress = dcPhase.getProgress();
            String [] progressArray = progress.split(GlobalConstant.DC_PHASE_SEPEATOR);
            Integer nowProgress = Integer.valueOf(progressArray[0]);
            Integer totalProgress = Integer.valueOf(progressArray[1]);
            // 如果装修到了最后 一步 并且打款成功 修改主订单的状态为完成
            if(nowProgress.equals(totalProgress)) {
                DCROrder order = new DCROrder();
                order.setId(dcPhase.getOrderId());
                order.setOrderStatus(GlobalConstant.DCROrderStatus.ORDER_FINISHED);
                order.setGmtFinish(new Date());
                dcrOrderDao.updateOrderByFinish(order);
            }
        } else {
            // 提示错误
            throw new BusinessException("该装修进度未通过审核");
        }
    }

    /** 
     * @see com.youmu.mall.dcr.service.IDcrOrderService#listDcProcessInfo()
     */
    @Override
    public List<DCProcessVo> listDcProcessInfo() {
        return dcrOrderDao.selectAllDcProcess();
    }

    /** 
     * @see com.youmu.mall.dcr.service.IDcrOrderService#updateDcProcessInfo(java.util.List)
     */
    @Override
    public void updateDcProcessInfo(List<DCPhase> process) {
        dcrOrderDao.updateDcProcess(process);
    }

    /** 
     * @see com.youmu.mall.dcr.service.IDcrOrderService#downLoadDcOrder(com.youmu.mall.dcr.query.DcrOrderDcProgressQuery, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void downLoadDcOrder(DcrOrderDcProgressQuery query, HttpServletResponse response) {
        try {
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode("装修流程财务打款账单.xlsx", CharEncoding.UTF_8) + "\"");
            ExcelUtils.exportExcel(DcOrderSysListVo.class, this.listDcOrderOperated(query).getData(), response.getOutputStream(), "yyyy.MM.dd HH:mm:ss");
        } catch (IOException e) {
            throw new BusinessException("导出异常", e);
        }
    }

    /** 
     * @see com.youmu.mall.dcr.service.IDcrOrderService#listDcOrderInOperator(com.youmu.mall.dcr.query.DcrOrderDcProgressQuery)
     */
    @Override
    public Page<DcOrderSysListVo> listDcOrderInOperator(DcrOrderDcProgressQuery query) {
        Page<DcOrderSysListVo> page = new Page<>(query.getPageNum(), query.getPageSize());
        if(query.getOperator() == null) {
            throw  new ParamException("参数异常");
        }
        // 设置查询的状态
        switch (query.getOperator()) {
            // 系统未审核
            case 1:
                query.setIsVerifi(false);
                break;
            case 2:
                query.setIsRemit(false);
                break;
            default:
                throw  new ParamException("参数opreator异常");
        }
        page.setTotal(dcrOrderDao.selectSysDcrInOperatorOrderCount(query));
        if(page.getTotal() > 0) {
            page.setData(dcrOrderDao.selectSysDcrInOperatorOrderList(query));
        }
        return page;
    }

    /** 
     * @see com.youmu.mall.dcr.service.IDcrOrderService#listDcOrderOperated(com.youmu.mall.dcr.query.DcrOrderDcProgressQuery)
     */
    @Override
    public Page<DcOrderSysListVo> listDcOrderOperated(DcrOrderDcProgressQuery query) {
        Page<DcOrderSysListVo> page = new Page<>(query.getPageNum(), query.getPageSize());
        if(query.getOperator() == null) {
            throw  new ParamException("参数异常");
        }
        // 设置查询的状态
        switch (query.getOperator()) {
            // 系统未审核
            case 1:
                query.setIsVerifi(true);
                break;
            case 2:
                query.setIsRemit(true);
                break;
            default:
                throw  new ParamException("参数opreator异常");
        }
        page.setTotal(dcrOrderDao.selectSysDcrOperatoredOrderCount(query));
        if(page.getTotal() > 0) {
            page.setData(dcrOrderDao.selectSysDcrOperatoredOrderList(query));
        }
        return page;
    }

    /** 
     * @see com.youmu.mall.dcr.service.IDcrOrderService#getDcDcrOrderInfo(java.lang.Long)
     */
    @Override
    public DcOrderSysListVo getDcDcrOrderInfo(Long id) {
        return dcrOrderDao.selectDcDcrOrderInfo(id);
    }

    /** 
     * @see com.youmu.mall.dcr.service.IDcrOrderService#getDcrDcOrderPhases(java.lang.Long)
     */
    @Override
    public List<DCPhaseUserListVo> getDcrDcOrderPhases(Long id) {
        return dcrOrderDao.selectDcrDcOrderPhases(id);
    }
}
