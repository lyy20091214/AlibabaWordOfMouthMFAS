package com.group2.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by lvyou on 2018/12/18.
 */
public class DateTimeUtil {

//    public static Date parse(String dateStr, String format){
//        Date rt=null;
//        SimpleDateFormat sdf=new SimpleDateFormat(format);
//        try {
//            rt=sdf.parse(dateStr);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            rt= Calendar.getInstance().getTime();
//        }
//        return rt;
//    }
//
//    public static String format(Date date,String format){
//        String rdatestr=null;
//        SimpleDateFormat sdf=new SimpleDateFormat(format);
//        rdatestr=sdf.format(date);
//        return rdatestr;
//    }


    public static Long parse(String startTime, String timeFormat){
        System.out.println(startTime);
        return Timestamp.valueOf(startTime).getTime();
    }

    public static String format(Date datetime, String retTimeFmt) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(retTimeFmt);
        String date = simpleDateFormat.format(datetime);
        return Timestamp.valueOf(date).toString();
    }
}
