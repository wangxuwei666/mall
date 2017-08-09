/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.json.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author yujiahao
 * @version $Id: JSONUtil.java, v 0.1 2017年2月27日 下午6:09:14 yujiahao Exp $
 */
public class JacksonUtil {
    
    public static Map JSON2Map(String string){
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> m = new HashMap<String, Object>();
        try {  
            m = mapper.readValue(string, Map.class);  
        } catch (JsonParseException e) {  
            e.printStackTrace();  
        } catch (JsonMappingException e1) {  
            e1.printStackTrace();  
        } catch (IOException e2) {  
            e2.printStackTrace();  
        }
        return m;
    }
    
    
}
