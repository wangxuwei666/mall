/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.ks.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.youmu.common.text.utils.KeywordsUtils;
import com.youmu.mall.ks.service.IKSService;

/**
 * 关键词统计实现类.
 * @author zh
 * @version $Id: IKSSServiceImpl.java, v 0.1 2017年4月26日 上午11:56:57 zh Exp $
 */
@Service
public class IKSSServiceImpl implements IKSService{
    
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    
    /** 
     * @see com.youmu.mall.ks.service.IKSService#findHotSearchKeywords(java.lang.String, int)
     */
    @Override
    public List<String> findHotSearchKeywords(final String key, final int num) {
        if(StringUtils.isBlank(key) || num < 1)
            return Collections.emptyList();
        
        String[] keywords = redisTemplate.execute(new RedisCallback<String[]>() {

            @Override
            public String[] doInRedis(RedisConnection conn) throws DataAccessException {
                if(conn.exists(key.getBytes())) {
                    Set<byte[]> set = conn.zRevRangeByScore(key.getBytes(), 0d, Double.MAX_VALUE);
                    int len = set.size();
                    String[] array = new String[len];
                    int i = 0;
                    for (byte[] bs : set) {
                        array[i] = new String(bs);
                        i++;
                    }
                    return array;
                }
                return new String[0];
            }
        });
        
        if(keywords.length > num) {
            String[] array = new String[num];
            System.arraycopy(keywords, 0, array, 0, num);
            return Arrays.asList(array);
        }
        
        return Arrays.asList(keywords);
    }

    /** 
     * @see com.youmu.mall.ks.service.IKSService#saveSearchKeywords(java.lang.String, java.lang.String)
     */
    @Override
    public void saveSearchKeywords(final String key, String keywords) {
        
        if(StringUtils.isBlank(key) || StringUtils.isBlank(keywords))
            return;
        
        // 分词提取关键词 暂时一次搜索提取一个核心关键词
        final String kw = KeywordsUtils.extractKeyword(keywords);
        if(kw == null) {
            return;
        }
        
        /**
         * 1. 限制搜索内容的长度
         * 2. 单词拆分 使用分词器分词后再统计
         */
        redisTemplate.execute(new RedisCallback<Void>() {

            @Override
            public Void doInRedis(RedisConnection conn) throws DataAccessException {
                // 存在key
                conn.zIncrBy(key.getBytes(), 1D, kw.getBytes());
                if(conn.exists(key.getBytes())) {
                    long count = conn.zCard(key.getBytes());
                    if(count >= MAX_HOT_KEYWORDS) {
                        Set<byte[]> set = conn.zRangeByScore(key.getBytes(), 0d, Double.MAX_VALUE);
                        Iterator<byte[]> iterator = set.iterator();
                        conn.zRem(key.getBytes(), iterator.next());
                    }
                }
                return null;
            }
        });
    }
    
    
    
}
