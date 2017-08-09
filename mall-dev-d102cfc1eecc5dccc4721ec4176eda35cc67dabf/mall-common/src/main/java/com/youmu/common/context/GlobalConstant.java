/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.context;

/**
 * 全局常量
 * @author yujiahao
 * @version $Id: GlobalConstant.java, v 0.1 2017年2月26日 下午2:18:25 yujiahao Exp $
 */
public class GlobalConstant {
    /**
     * ZERO
     */
    public static final int ZERO = 0;
    
    /**
     * ONE
     */
    public static final int ONE = 1;
    
    /**
     * TWO
     */
    public static final int TWO = 2;
    
    /**
     * THREE
     */
    public static final int THREE = 3;
    
    /**
     * FOUR
     */
    public static final int FOUR = 4;

    /** 装修贷款优惠券的id */
    public static final int DCR_BUSINESS_TYPE = 5;
    
    /** 订单装修进度与总进度的分割符号 1/5 2/5....  */
    public static final String DC_PHASE_SEPEATOR = "/";

    /** 短信验证码的过期时间 */
    public static final int AUTH_CODE_TIME_OUT = 10 * 60 * 1000;

    /** 微信自动登陆状态 */
    public static final int WX_AUTO_LOGIN = 1;
    
    /** 在微信公众号里面注册 */
    public static int REGIST_IN_WX = 2;
    
    /** 在微信公众号里面注册 */
    public static int WX_PAY_OPENID = 3;
    
    /** 在微信公众号里面分享*/
    public static final int WX_Share = 4;
    
    /** 装修类型 */
    public static final int DC_TYPE = 2;
    
    /** 非装修类型  */
    public static final int NOT_DC_TYPE = 1;

    public static class UserStatus {

        public static int DISABLE = -1;
        
        public static int ENABLE = 0;
    }

    /** 装修贷款订单的主状态 */
    public static class DCROrderStatus{

        // 订单的主流程 
        // 订单申请  
        public static final int USER_APPLY = 0;
        
        // 订单处理人员量房处理
        public static final int SYS_PROCESSING = 1;

        // 二维码核销过期 --- dead
        public static final int USER_QRCODE_EXPIRE = 2;
        
        // 用户核销二维码成功
        public static final int USER_USE_QRCODE = 3;

        // 商户提交合同成功
        public static final int BUSINESS_SUBMIT_COMPACT = 4;

        // 后台人员审核合同数据被驳回
        public static final int SYS_CONTRACT_REJECT = 5;
        
        // 后台人员审核数据
        public static final int SYS_AUDIT_CONTRACT = 6;

        // 银行审核人员审核合同被驳回
        public static final int BANK_AUDIT_REJECT = 7;

        // 银行业务人员审核通过
        public static final int BANK_AUDIT = 8;

        // 11  装修中
        public static final int BUSINESS_DECORATING   = 9;

        // 12 已经结束
        public static final int ORDER_FINISHED = 10;

    }
    
    /** 装修贷款订单状态 **/
    public static class DecorateStatus {
        /** 待提交  */
        public static final int WAIT_SUBMIT = 0;
        
        /** 待审核 */
        public static final int WAIT_VERIFI = 1;

        /** 等待放款 */ 
        public static final int WAIT_REMIT = 2;
        
        /** 完成 */
        public static final int FINISH = 3;
    }
    
    /** 装修流程审核标志位 **/
    public static class DCPhaseVerifiStatus {
        
        /** 不能审核  */
        public static final int NOT_OPRATOR = 0;
        
        /** 待审核 */
        public static final int WAIT_VERIFI = 1;

        /** 审核不通过 */ 
        public static final int NOT_PASSED = 2;
        
        /** 审核通过*/
        public static final int PASSED = 3;      
    }

    public static class BusinessUserType {
        
        /** 普通商户用户 */
        public static final int NORMAL = 1;
        
        /** 银行审核商户用户 */
        public static final int BANK = 2;
        
        /** 商户后台管理人员 */
        public static final int ADMIN = 3;
    }

}
