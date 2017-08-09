/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.date.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 日期工具类.
 * @author zh
 * @version $Id: DateUtils.java, v 0.1 2017年3月1日 下午3:00:45 zh Exp $
 */
public class DateUtils {
    
    public static String getDate(Date param) {
        String result = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(param == null){
            Date date = new Date();
            result = simpleDateFormat.format(date);
        }else{
            result = simpleDateFormat.format(param);
        }
        return result;
    }

    /**
     * 获取时间戳字符串
     * @return
     */
    public static String getTimestapString() {
        return new Date().getTime()+"";
    }
    
    /**
     * 获取半小时之前的日期
     * @return
     */
    public static long getDateBeforeHalfHour(Date param){
        Calendar c = Calendar.getInstance();
        long diff = c.getTimeInMillis() - param.getTime();
        return diff / (60 * 1000);
    }
    
    public static boolean isOverDate(Date param){
        boolean isOver = false;
        if(param.compareTo(new Date()) == -1){
            isOver = true;
        }
        return isOver;
    }
    
    /**
     * 判断两个日期按照 格式解析 是否相等
     * 
     * @param startDate
     * @param endDate
     * @param pattern
     * @return
     */
    public static boolean isEqualDate(Date startDate,Date endDate,String pattern){
        boolean result = false;
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String startDateString = format.format(startDate);
        String endDateString = format.format(endDate);
        
        //是否日期为一天
        if(startDateString.equals(endDateString)){
            result = true;
        }
        return result;
    }
    
    /**
     * 判断是否满足秒杀时间段的要求
     * 1、先确定前后两个时间是否在24小时内（endDate-startDate<24小时）
     * 2、再判断endDate只能为0点
     * 
     * @param startDate
     * @param endDate
     * @param pattern
     * @return
     */
    public static boolean checkSecKillDate(Date startDate,Date endDate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String startDateString = format.format(startDate);
        String endDateString = format.format(endDate);
        
        if(endDate.getTime() - startDate.getTime() <= 0){
            return false;
        }
        
        //是否日期为一天
        if(startDateString.equals(endDateString)){
            //1、判断开始时间不能超过结束时间
            //2、判断前后两个时间 小时 不能相等
            return true;
        }
        if (( endDate.getTime() - startDate.getTime() ) <= 24*60*60*1000 ) {
            //判断是否结束时间为0点
            SimpleDateFormat hourFormat=new SimpleDateFormat("HH");
            String hourStr = hourFormat.format(endDate);
            if("00".equals(hourStr)){
                return true;
            }
        }
        return false;
        
    }
    
    public static void main(String[] args) throws ParseException {
        //1、先确定前后两个时间是否在24小时内（endDate-startDate<24小时），再判断endDate只能为0点
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateStart = format.parse("2017-04-01 01:00:00");
        Date dateEnd = format.parse("2017-04-01 01:00:00");
        
        System.err.println(checkSecKillDate(dateStart,dateEnd));
    }

    /**
     * 比较年月日
     * @return 
     * <pre>
     *  date1 eq date2 retrun 0
     *  date2 gt date2 retrun 1
     *  date1 lt date2 return -1
     * </pre>
     */
    public static int compareDate(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);
        int num1 = c1.get(Calendar.YEAR) * 10000 + c1.get(Calendar.MONTH) * 100 + c1.get(Calendar.DAY_OF_MONTH);
        int num2 = c2.get(Calendar.YEAR) * 10000 + c2.get(Calendar.MONTH) * 100 + c2.get(Calendar.DAY_OF_MONTH);
        return num1 == num2 ? 0 : ( num1 > num2 ? 1 : -1); 
    }
    
    /**
     * 时间字符串转时间格式
     * @param date
     * @return
     */
    public static Date stringToDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 计算两个时间的差值
     * 
     * @param startTime
     * @param endTime
     * @return
     */
    public static Long getDifferTime(String startTime,String endTime){
        long start = stringToDate(startTime).getTime();
        long end = stringToDate(endTime).getTime();
        return end-start;
    }
}
