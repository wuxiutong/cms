package wxt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wuxiutong on 15/5/17.
 */
public class DateUtils {
    //从字符串获取时间日期
    public  static Date getDateFromStr(String dateStr,String dateFormat) throws ParseException {
        if("".equalsIgnoreCase(dateFormat.trim())){
            dateFormat = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.parse(dateStr);
    } //从时间日期获取String
    public  static String  getStrFromDate(Date date,String dateFormat) throws ParseException {
        if("".equalsIgnoreCase(dateFormat.trim())){
            dateFormat = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }
    //比较连个日期的大小，若前者大于或这则返回1，如果小于则返回-1.否则返回0
    public  static int  equalsDateStr(String  date1,String date2, String dateFormat) throws ParseException {
        int result = 0;
        if("".equalsIgnoreCase(dateFormat.trim())){
            dateFormat = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date_one = sdf.parse(date1);
        Date date_two = sdf.parse(date2);
      //  System.out.println(date_one+" : "+date_two);
        if(date_one.before(date_two)){
            result = -1;
        }else if(date_one.after(date_two)){
            result = 1;
        }
        return result;
    }
    //比较连个日期的大小，若前者大于或这则返回1，如果小于则返回-1.否则返回0
    public  static int  equalsDate(Date  date1,Date date2) throws ParseException {
        int result = 0;
        if(date1.before(date2)){
            result = -1;
        }else if(date1.after(date2)){
            result = 1;
        }
        return result;
    }
}
