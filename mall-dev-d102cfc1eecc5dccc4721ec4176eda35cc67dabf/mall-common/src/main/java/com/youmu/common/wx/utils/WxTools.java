/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.wx.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.youmu.common.cofig.utils.ConfigUtils;
import com.youmu.common.digest.utils.MD5Util;
import com.youmu.common.http.utils.HttpUtils;

/**
 * 微信工具类。
 * @author zh
 * @version $Id: WxTools.java, v 0.1 2017年3月9日 上午10:23:40 zh Exp $
 */
public class WxTools {
    
    private static Logger logger = LoggerFactory.getLogger(WxTools.class);
    
    /**
     * 获取签名
     * 
     * @param params
     * @return
     */
    public static String getSign(Map<String,Object> params){
        Collection<String> keyset= params.keySet();  
        List<String> keys = new ArrayList<String>(keyset);  
        //对key键值按字典升序排序  
        Collections.sort(keys);  
        StringBuffer buffer = new StringBuffer();
        for(String key:keys){
            if(key.equals("sign")) {
                continue;
            }
            logger.info(key+"="+params.get(key));
            buffer.append(key).append("=").append(params.get(key)).append("&");
        }
        buffer.append("key").append("=").append(ConfigUtils.getWeixinKey());
        logger.info("buffer:"+buffer.toString());
        return MD5Util.MD5(buffer.toString()).toUpperCase();
    }

    /**
     * 
     * @param result
     * @return
     */
     public static String getXml(Map<String,Object> map){
        Document document = DocumentHelper.createDocument();
        // root element
        Element rootElement = document.addElement("xml");
        for(String key:map.keySet()){
            Element nameElement = rootElement.addElement(key);
            nameElement.setText(String.valueOf(map.get(key)));
        }
        logger.info("xml:"+document.asXML());
        return document.asXML();
    }
    
    public static Map<String,String> getMapByXml(String xml) {
        Map<String, String> map = new HashMap<String, String>(); 
        Document document = null;
        try {
            document = DocumentHelper.parseText(xml);
        } catch (DocumentException e1) {
            e1.printStackTrace();
            return null;
        }
        document.getName();
        Element root = document.getRootElement(); 
        for (Iterator iterator = root.elementIterator(); iterator.hasNext();) { 
            Element e = (Element) iterator.next(); 
            map.put(e.getName(), e.getText()); 
        } 
        return map; 
    }
    
    public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        logger.debug("need sigin js ticket string is {}.", string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
