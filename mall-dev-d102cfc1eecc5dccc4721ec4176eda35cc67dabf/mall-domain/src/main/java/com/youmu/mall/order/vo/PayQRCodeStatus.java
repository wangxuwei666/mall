/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.order.vo;

/**
 * 支付二维码状态.
 * @author zh
 * @version $Id: PayQRCodeStatus.java, v 0.1 2017年4月22日 下午12:14:11 zh Exp $
 */
public class PayQRCodeStatus {

    public enum QRCodeStatus {
                       WAIT, // 继续轮询
                       SUCCESS, // 跳转到订单完成界面
                       CANCEL, // 跳转到待支付界面
                       FAIL, // 错误提示 和 跳转到待支付界面
    }

    /** 请求状态 */
    private String       status = "ok";

    // wait 等待用户支付   success 支付成功 cancel 用户取消  fail 支付失败
    private QRCodeStatus qrCodeStatus;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public QRCodeStatus getQrCodeStatus() {
        return qrCodeStatus;
    }

    public void setQrCodeStatus(QRCodeStatus qrCodeStatus) {
        this.qrCodeStatus = qrCodeStatus;
    }

}
