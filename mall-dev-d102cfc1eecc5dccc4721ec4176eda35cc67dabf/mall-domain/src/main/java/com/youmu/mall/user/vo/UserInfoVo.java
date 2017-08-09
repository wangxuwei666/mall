/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.user.vo;

import com.youmu.common.upload.utils.ImageUploadUtils;

/**
 * 我的
 * @author yujiahao
 * @version $Id: UserInfoVo.java, v 0.1 2017年3月6日 下午2:28:25 yujiahao Exp $
 */
public class UserInfoVo {
    /** 用户电话 */
    private String  mobile;
    
    /** 用户头像 */
    private String  headIcon;
    
    /**优惠劵数量*/
    private Integer couponCount;
    
    /**收货地址数量*/
    private Integer receiverCount;
    
    /**待付款*/
    private Integer unPaidCount;
    
    /**待收货*/
    private Integer unReceiveCount;
    

    public UserInfoVo() {
        super();
    }

    /**
     * @param mobile
     * @param headIcon
     */
    public UserInfoVo(String mobile, String headIcon,Integer couponCount,Integer receiverCount,Integer unPaidCount,Integer unReceiveCount) {
        this.mobile = mobile;
        this.headIcon = headIcon;
        this.couponCount = couponCount;
        this.receiverCount = receiverCount;
        this.unPaidCount = unPaidCount;
        this.unReceiveCount = unReceiveCount;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHeadIcon() {
        if(headIcon == null || headIcon.startsWith("http")) {
            return headIcon;
        }   else {
            return ImageUploadUtils.fillPath(headIcon);
        }
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public Integer getReceiverCount() {
        return receiverCount;
    }

    public void setReceiverCount(Integer receiverCount) {
        this.receiverCount = receiverCount;
    }

    public Integer getUnPaidCount() {
        return unPaidCount;
    }

    public void setUnPaidCount(Integer unPaidCount) {
        this.unPaidCount = unPaidCount;
    }

    public Integer getUnReceiveCount() {
        return unReceiveCount;
    }

    public void setUnReceiveCount(Integer unReceiveCount) {
        this.unReceiveCount = unReceiveCount;
    }
    
    
}
