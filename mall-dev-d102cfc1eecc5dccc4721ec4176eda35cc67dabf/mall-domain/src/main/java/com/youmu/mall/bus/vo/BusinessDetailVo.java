/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.bus.vo;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.youmu.common.upload.utils.ImageUploadUtils;

/**
 * 商户详情对象
 * @author zh
 * @version $Id: BusinessDetailVo.java, v 0.1 2017年3月15日 下午5:21:17 zh Exp $
 */
public class BusinessDetailVo {
    
    /** 详情id */
    private Long id;
    
    /** 商户名称 */
    private String name;
    
    /** 营业执照图片 */
    private String busLicense;
    
    /** 门店图片 */
    private List<String> busStoreImgs;

    /**
     * Getter method for property <tt>id</tt>.
     * 
     * @return property value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     * 
     * @param id value to be assigned to property id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>name</tt>.
     * 
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     * 
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>busLicense</tt>.
     * 
     * @return property value of busLicense
     */
    public String getBusLicense() {
        return busLicense;
    }

    /**
     * Setter method for property <tt>busLicense</tt>.
     * 
     * @param busLicense value to be assigned to property busLicense
     */
    public void setBusLicense(String busLicense) {
        if(StringUtils.isNotBlank(busLicense)) {
            this.busLicense =  ImageUploadUtils.fillPath(busLicense);
        }
    }

    /**
     * Getter method for property <tt>busStoreImgs</tt>.
     * 
     * @return property value of busStoreImgs
     */
    public List<String> getBusStoreImgs() {
        return busStoreImgs;
    }

    /**
     * Setter method for property <tt>busStoreImgs</tt>.
     * 
     * @param busStoreImgs value to be assigned to property busStoreImgs
     */
    public void setBusStoreImgs(String busStoreImgs) {
        if(StringUtils.isNotBlank(busStoreImgs)) {
            this.busStoreImgs = ImageUploadUtils.fillPath(Arrays.asList(busStoreImgs.split(",")));
        }
    }
}
