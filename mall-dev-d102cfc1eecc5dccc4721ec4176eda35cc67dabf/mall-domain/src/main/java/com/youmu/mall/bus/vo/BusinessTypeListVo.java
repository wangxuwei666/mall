/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.bus.vo;

import com.youmu.common.upload.utils.ImageUploadUtils;

/**
 * 商户的行业.
 * @author zh
 * @version $Id: Industry.java, v 0.1 2017年2月26日 下午3:18:58 zh Exp $
 */
public class BusinessTypeListVo{
    
    /** id */
    private Long id;
    
    /** 行业名称 */
    private String name;
    
    /** 行业图标 */
    private String icon;
    
    private String icon1;
    
    private String icon2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return ImageUploadUtils.fillPath(icon);
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getIcon1() {
		return ImageUploadUtils.fillPath(icon1);
	}

	public void setIcon1(String icon1) {
		this.icon1 = icon1;
	}

	public String getIcon2() {
		return ImageUploadUtils.fillPath(icon2);
	}

	public void setIcon2(String icon2) {
		this.icon2 = icon2;
	}
    
}
