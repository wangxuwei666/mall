/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.ks.service;

import java.util.List;

/**
 * 关键词统计服务
 * @author zh
 * @version $Id: IKSService.java, v 0.1 2017年4月26日 上午11:48:01 zh Exp $
 */
public interface IKSService {
    
    public static final int MAX_HOT_KEYWORDS = 20;
    
    /** 商品搜索热词存在redis中的key  */
    public static final String PRODUCT_SEARCH_KEY = "product-search";
    
    /**
     * 查询热搜词.
     * 
     * @param key 热搜词统计的别名
     * @param num 热搜词的数量
     * @return 热搜词列表
     */
    List<String> findHotSearchKeywords(String key, int num);
    
    /**
     * 保存一个热搜词通过.
     * 
     * @param key 热搜词的key
     * @param keywords 热搜词
     */
    void saveSearchKeywords(String key, String keywords);
    
}
