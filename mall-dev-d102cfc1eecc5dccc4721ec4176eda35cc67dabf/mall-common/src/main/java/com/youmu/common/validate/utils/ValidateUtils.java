/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.validate.utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.ibatis.javassist.expr.NewArray;

import com.youmu.mall.exception.ParamException;

/**
 * 字符串校验工具类
 * 
 * @author zhanghui
 * @version $Id: StringValidator.java, v 0.1 2016年12月7日 下午6:04:40 zhanghui Exp $
 */
public class ValidateUtils {
    
    public static final BigDecimal BIGDECIMAL_ZERO = new BigDecimal(0);
  
  /** 手机号码的校验规则 */
  public static final String MOBILE_PATTERN = "^[1][3578][0-9]{9}$";
  
  /** 6-10位,必须由字母和数组组成 */
  public static final String PASSWORD_PATTERN = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,32}$";
  
  /** 银行卡账号 */
  public static final String BANK_ACCOUNT_PATTERN = "^([1-9]{1})(\\d{14}|\\d{18})$";
  
  /** 
   * 检查对象是否为手机号码 不是抛出异常 
   */
  public static void checkMobile(String mobile, String errMsg) {
      if(!Pattern.matches(MOBILE_PATTERN, mobile)) {
          throw new ParamException(errMsg);
      }
  }
  
  /** 
   * 检查密码是否正确 不正确抛出异常 
   */
  public static void checkPassword(String pwd, String errMsg) {
      if(!Pattern.matches(PASSWORD_PATTERN, pwd)) {
          throw new ParamException(errMsg);
      }
  }

  
  /** 
   * 检查对象是否为不为空 为空抛出异常 
   */
  public static void checkNotBlank(CharSequence str, String errMsg) {
      if(StringUtils.isBlank(str)) {
          throw new ParamException(errMsg);
      }
  }

    /**
     * 检查两个字符串是否相同 不想同抛出异常
     * @param authCode
     * @param savedCode
     * @param string
     */
    public static void checkEquals(CharSequence str1, CharSequence str2, String errMsg) {
        if(!StringUtils.equals(str1, str2)) {
            throw new ParamException(errMsg);
        }
    }

    /**
     * 检查两个对象是否不相同  相同抛出异常.
     */
    public static void checkNotEquals(CharSequence cs1, CharSequence cs2, String errMsg) {
        if(StringUtils.equals(cs1, cs2)) {
            throw new ParamException(errMsg);
        }
    }

    /**
     * 检查多个对象不为空
     */
    public static void checkNotNull(String errMsg, Object... objects) {
        for (Object object : objects) {
            if(object == null) {
                throw new ParamException(errMsg);
            }
        }
    }
    
    /**
     * 判断两个Bigdecimal类精确值为2的时候，是否相等
     * @param param1
     * @param param2
     * @return
     */
    public static boolean isEqualsByBigDecimal(BigDecimal param1 ,BigDecimal param2){
        boolean result = false;
        if(param1.setScale(2,BigDecimal.ROUND_HALF_EVEN).compareTo(param2.setScale(2,BigDecimal.ROUND_HALF_EVEN)) == 0){
            return !result;
        }
        return result;
    }
    
    /**
     * 判断两个Bigdecimal类精确值为2的时候，前者是否大于后者
     * @param param1
     * @param param2
     * @return
     */
    public static boolean findLargerOne(BigDecimal param1 ,BigDecimal param2){
        boolean result = false;
        if(param1.setScale(2,BigDecimal.ROUND_HALF_EVEN).compareTo(param2.setScale(2,BigDecimal.ROUND_HALF_EVEN)) == 1){
            return !result;
        }
        return result;
    }
    
    /**
     * 检查flag是否正确 不正确返回错误信息
     * @param b
     * @param string
     */
    public static void checkTrue(boolean flag, String errMsg) {
        if(!flag) {
            throw new ParamException(errMsg);
        }
    }

    /**
     * 检查flag是否不正确 正确返回错误信息
     * @param b
     * @param string
     */
    public static void checkFalse(boolean flag, String errMsg) {
        if(flag) {
            throw new ParamException(errMsg);
        }
    }
    
    /**
     * 匹配Luhn算法：可用于检测银行卡卡号
     * @param cardNo
     * @return
     */
    public static boolean matchLuhn(String cardNo) {
        int[] cardNoArr = new int[cardNo.length()];
        for (int i=0; i<cardNo.length(); i++) {
            cardNoArr[i] = Integer.valueOf(String.valueOf(cardNo.charAt(i)));
        }
        for(int i=cardNoArr.length-2;i>=0;i-=2) {
            cardNoArr[i] <<= 1;
            cardNoArr[i] = cardNoArr[i]/10 + cardNoArr[i]%10;
        }
        int sum = 0;
        for(int i=0;i<cardNoArr.length;i++) {
            sum += cardNoArr[i];
        }
        return sum % 10 == 0;
    }

    /**
     * 检查银行卡号
     * @param bankAccount
     * @param string
     */
    public static void checkBankAccount(String bankAccount, String errMsg) {
        if(Pattern.matches(BANK_ACCOUNT_PATTERN, bankAccount) && matchLuhn(bankAccount)) {
            return;
        }else {
            throw new ParamException(errMsg);
        }
    }
}
