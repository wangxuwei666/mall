/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.text.utils;

import java.util.List;

import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;

/**
 * 提取字符串中的关键词.
 * @author zh
 * @version $Id: KeywordsUtils.java, v 0.1 2017年4月26日 下午6:38:11 zh Exp $
 */
public class KeywordsUtils {
    
    private static KeyWordComputer keyWordComputer = new KeyWordComputer(1);
    
    public static String extractKeyword(String content) {
        List<Keyword> list = keyWordComputer.computeArticleTfidf(content);
        return list.isEmpty() ? null : list.get(0).getName();
    }
    
    public static void main(String[] args) {
        System.err.println(extractKeyword("的"));
    }
}
