package com.tcs.denmail.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tcs.denmail.common.exception.TcsApplicationException;

public final class DateUtil {

    /**
     * 現在のDateを返す
     * 
     * @return
     * @throws TcsApplicationException
     */
    public static Date getDatetime() throws TcsApplicationException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String string = sdf.format(new Date());
        Date date = null;
        try {
            date = sdf.parse(string);
        } catch (ParseException e) {
            throw new TcsApplicationException("DM0001F", e.getCause());
        }
        return date;
    }

    /**
     * 指定した日時文字列のDateを返す
     * 
     * @param yyyyMMddHHmmss
     * @return
     * @throws TcsApplicationException
     */
    public static Date getDatetime(String yyyyMMddHHmmss) throws TcsApplicationException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date dateTime = null;
        try {
            dateTime = sdf.parse(yyyyMMddHHmmss);
        } catch (ParseException e) {
            throw new TcsApplicationException("DM0001F", e.getCause());
        }
        return dateTime;
    }

    /**
     * 指定した日付文字列のDateを返す
     * 
     * @param yyyyMMdd
     * @return
     * @throws TcsApplicationException
     */
    public static Date getDate(String yyyyMMdd) throws TcsApplicationException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = sdf.parse(yyyyMMdd);
        } catch (ParseException e) {
            throw new TcsApplicationException("DM0001F", e.getCause());
        }
        return date;
    }

    /**
     * 指定したDateを指定したformatで日時文字列として返す
     * 
     * @param date
     * @param format
     * @return
     */
    public static String getDatetimeString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

}
