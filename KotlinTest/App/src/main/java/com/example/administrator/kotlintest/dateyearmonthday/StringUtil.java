package com.example.administrator.kotlintest.dateyearmonthday;

import java.sql.Clob;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by lirl on 2016-11-17.
 */
public class StringUtil {
    public static boolean IsNullOrEmpty(Object obj) {
        return obj == null || "".equals(obj.toString());
    }
    public static String toString(Object obj){
        if(obj == null) return "";
        return obj.toString();
    }
    public static String join(Collection s, String delimiter) {
        StringBuffer buffer = new StringBuffer();
        Iterator iter = s.iterator();
        while (iter.hasNext()) {
            buffer.append(iter.next());
            if (iter.hasNext()) {
                buffer.append(delimiter);
            }
        }
        return buffer.toString();
    }
    public static String clob2String(Clob clob) throws Exception {
        return (clob != null ? clob.getSubString(1, (int) clob.length()) : null);
    }
    public Timestamp ToDate(Object o){
        try{
            if(o.getClass() == String.class){

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                o = format.parse(o.toString());
                return new Timestamp(((Date)o).getTime());
            }
            return o != null ? new Timestamp(((Date)o).getTime()) : null;
        }catch(Exception ex){
            return null;
        }
    }
    public static String TrimEnd(String select,String sp){
        if(select==null||select.trim().equals("")){
            return select;
        }
        if( select.substring(select.length()-1,select.length()).equals(sp)){
            select = select.substring(0,select.length()-1);
        }
        return select;
    }
}
