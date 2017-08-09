/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.wx.utils;

/**
 * 微信用户对象.
 * @author zh
 * @version $Id: WxUserInfo.java, v 0.1 2017年3月10日 下午1:32:46 zh Exp $
 */
public class WxUserInfo {
    
    private String openid;
    
    private String nickname;
    
    private String sex;

    private String province;

    private String city;
    
    private String country;

    private String headimgurl;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "WxUserInfo [openid=" + openid + ", nickname=" + nickname + ", sex=" + sex + ", province=" + province + ", city=" + city + ", country=" + country + ", headimgurl=" + headimgurl + "]";
    }
}
