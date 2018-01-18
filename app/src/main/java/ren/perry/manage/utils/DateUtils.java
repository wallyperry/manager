package ren.perry.manage.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author perry
 * @date 2017/10/24
 * WeChat 917351143
 */

public class DateUtils {

    /**
     * 时间戳 => 时间（*1000 =>解决php与java时间戳的差别）
     */
    @SuppressLint("SimpleDateFormat")
    public static String stampToDate(String s) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date(Long.valueOf(s) * 1000));
    }

    /**
     * 时间 => 时间戳（*1000 =>解决php与java时间戳的差别）
     */
    @SuppressLint("SimpleDateFormat")
    public static String dateToStamp(String s) {
        String res = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(s);
            long ts = date.getTime() / 1000;
            res = String.valueOf(ts);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }
}
