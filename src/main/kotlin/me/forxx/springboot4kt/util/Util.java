package me.forxx.springboot4kt.util;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by GaoMingQiang on 2017/7/18 14:55.
 */
public class Util {


    /**
     * 判断对象是否为空
     * @param o     目标对象
     * @param type  类型，默认空 字符串，array数组，map，list
     * @return
     */
    public static Boolean isEmptys(Object o,String type){
        try {
            if(type==null || "".equals(type.trim()) || "str".equals(type)){
                if(o==null || "".equals(o) || "null".equals(o)){
                    return true;
                }
                String str = o+"";
                if("".equals(str.trim()) || str.length()<1){
                    return true;
                }
            }
            if("array".equals(type)){
                if(o==null){
                    return true;
                }
                Object[] arro = (Object[]) o;
                if(arro.length<1){
                    return true;
                }
            }
            if("map".equals(type)){
                if(o==null){
                    return true;
                }
                Map mapo = (Map) o;
                if(mapo.isEmpty()){
                    return true;
                }
            }
            if("list".equals(type)){
                if(o==null){
                    return true;
                }
                List list = (List) o;
                if(list.isEmpty()){
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 实体类转Map
     * @param obj
     * @return
     */
    public static Map<String, Object> model2Map(Object obj){
        Map<String, Object> map = new HashMap<String, Object>();
        if (obj == null || "".equals(obj)) {
            return map;
        }
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    if(value==null){
                        value = "";
                    }
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            return map;
        }
        return map;
    }
    /**
     * 按顺序替换字符串中#{paraN}字符串
     * @param text
     * @param paras
     * @return
     */
    public static String replaceParaN(String text,String... paras){
        for(int i=1;i<=paras.length;i++){
            text = text.replace("#{para"+i+"}", paras[i-1]);
        }
        return text;
    }

    /**
     * 产生1000-9999之间的随机数，用作验证码
     * @return
     */
    public static String getRandCode(){
        Random random = new Random();
        return String.valueOf((random.nextInt(9999)%(9000)+1000));
    }
    /**
     * 产生100000-999999之间的随机数，用作验证码
     * @return
     */
    public static String getSendNote(){
        Random random = new Random();
        return String.valueOf((random.nextInt(999999)%(900000)+100000));
    }
    /**
     * 生成随机4-8位字符串
     * @return
     */
    public static String getRandStr(){
        Random rand = new Random();
        int index = rand.nextInt(8)%(4)+4;	//产生4-8的随机数
        String str = "1,2,3,4,5,6,7,8,9,0,q,w,e,r,t,y,u,i,o,p,a,s,d,f,g,h,j,k,l,z,x,c,v,b,n,m,Q,W,E,R,T,Y,U,I,O,P,A,S,D,F,G,H,J,K,L,Z,X,C,V,B,N,M";
        String[] arrstr = str.split(",");
        StringBuffer newstr = new StringBuffer();
        for(int i=0;i<index;i++){
            int j = rand.nextInt(62);
            newstr.append(arrstr[j]);
        }
        return newstr.toString();
    }

    /**
     * 将字符串后4位替换为****
     * @param str	字符串长度必须大于4
     * @return
     */
    public static String strToStar(String str){
        return new StringBuilder(str).replace(str.length()-4, str.length(), "****").toString();
    }
    /**
     * 将手机号中间4位替换为****
     * @param str	字符串长度必须大于7
     * @return
     */
    public static String strPhoneToStar(String str){
        return new StringBuilder(str).replace(3, 7, "****").toString();
    }

    /**
     * 根据属性名获取属性值
     * */
    public static Object getObjectByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter);
            Object value = method.invoke(o);
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前日期，格式为yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getDate(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Timestamp(System.currentTimeMillis()));
    }
    /**
     * 获取当前日期，格式为yyyy-MM-dd
     * @return
     */
    public static String getDateY_M_d(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Timestamp(System.currentTimeMillis()));
    }
    /**
     * 获取当前日期，用于保存文件前缀，格式为yyMMddHHmmssSSS
     * @return
     */
    public static String getDateFile(){
        SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmssSSS");
        return df.format(new Timestamp(System.currentTimeMillis()));
    }
    /**
     * 获取当前日期的分，格式为yyMMddHH
     * @return
     */
    public static String getDatemm(){
        SimpleDateFormat df = new SimpleDateFormat("yyMMddHH");
        return df.format(new Timestamp(System.currentTimeMillis()));
    }

    /**
     * 将时间戳转换为字符串，格式为yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String date2str(Timestamp date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    /**
     * 时间字符串转时间戳
     * @param str
     * @return
     */
    public static Long str2date(String str){
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date= null;
        try {
            date = simpleDateFormat .parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }
    /**
     * 将时间戳转换为字符串，格式为yyyy-MM-dd HH:mm
     * @param date
     * @return
     */
    public static String date2str2(Timestamp date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return df.format(date);
    }
    /**
     * 将时间戳转换为字符串，格式为yyyyMMdd
     * @param date
     * @return
     */
    public static String date2str3(Timestamp date){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(date);
    }
    /**
     * 将时间戳转换为字符串，格式为yyyy-MM-dd
     * @param date
     * @return
     */
    public static String date2str4(Timestamp date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }
    /**
     * 计算时间加减天数，
     * @param date	时间戳
     * @param day	加减天数
     * @return
     */
    public static Long dateJday(Long date,Integer day){
        Calendar cd = Calendar.getInstance();
        cd.setTimeInMillis(date);
        cd.add(Calendar.DATE, +day);
        return cd.getTimeInMillis();
    }
    /**
     * 计算时间加减月份，
     * @param date
     * @param yue
     * @return
     */
    public static Long dateJyue(Long date,Integer yue){
        Calendar cd = Calendar.getInstance();
        cd.setTimeInMillis(date);
        cd.add(Calendar.MONTH, +yue);
        return cd.getTimeInMillis();
    }

    /**
     * 将时间修改为xx 23:59:59
     * @param date
     * @return
     */
    public static Long date2date235959(Long date){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = sf.format(date)+" 23:59:59";
        Date d = new Date();
        Long ld = date;
        try {
            d = sf2.parse(s);
            ld = d.getTime();
        } catch (ParseException e) {
            ld = date;
        }
        return ld;
    }
    /**
     * 修改时间为XX 00:00:00秒
     * @param date
     * @return
     */
    public static Long date2date000000(Long date){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = sf.format(date)+" 00:00:00";
        Date d = new Date();
        Long ld = date;
        try {
            d = sf2.parse(s);
            ld = d.getTime();
        } catch (ParseException e) {
            ld = date;
        }
        return ld;
    }
    /**
     * 计算时间天数
     * @param t1 结束时间
     * @param t2 开始时间
     * @return
     */
    public static Integer getJDateDay(Long t1,Long t2) {
        BigDecimal b1 = new BigDecimal(t1);
        BigDecimal b2 = new BigDecimal(t2);
        BigDecimal c1 = b1.subtract(b2);
        BigDecimal c2 = new BigDecimal(1000*60*60*24);
        return Integer.valueOf(c1.divide(c2,2, RoundingMode.HALF_EVEN).intValue());
    }
    /**
     * 获取当前月份第一天 xxx 00:00:00
     * @return
     */
    public static String getYueFirstDay2000000(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(cale.getTime());
    }

    /**
     * 比较两个时间大小
     * @param DATE1 第一个时间
     * @param DATE2 第二个时间
     * @return
     */
    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取下个月第一天 xxxx-xx-xx 00:00:00
     * @return
     */
    public static String getNextMonthDay(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH, 1);
        cale.add(Calendar.MONTH,1);
        return format.format(cale.getTime());
    }

    /**
     * 获得当前年到下几年的时间
     * @param year 年份
     * @return
     */
    public static String getYear(Integer year ){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.YEAR,year);
        return format.format(cale.getTime());
    }
    /**
     * 获得当前月到下几月的时间
     * @param month 月份
     * @return
     */
    public static String getMonth(Integer month ){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH,month);
        return format.format(cale.getTime());
    }
    /**
     * string 类型转化为Date类型
     * @param date
     * @return
     * @throws Exception
     */
    public static Date getDateByString(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取客户端ip
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ip!=null && ip.length()>15){ //"***.***.***.***".length() = 15
            if(ip.indexOf(",")>0){
                ip = ip.substring(0,ip.indexOf(","));
            }
        }
        return ip;
    }
    /**
     * 获取网页的html代码
     * @param url	网址
     * @param encoding	网站编码
     * @return
     */
    public static String getUrlHtml(String url, String encoding){
        StringBuffer content = new StringBuffer();
        InputStreamReader theHTML = null;
        try {
            URL u = new URL(url);
            InputStream in = new BufferedInputStream(u.openStream());
            theHTML = new InputStreamReader(in, encoding);
            int c;
            while ((c = theHTML.read()) != -1) {
                content.append((char) c);
            }
        }
        catch (MalformedURLException e) {
            throw new RuntimeException("打开网页【"+url+"】MalformedURLException异常");
        } catch (IOException e) {
            throw new RuntimeException("IOException异常");
        }finally{
            if(theHTML!=null){
                try {
                    theHTML.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException异常");
                }
            }
        }
        return content.toString();
    }

    /**
     * 生成32位唯一UUID
     * @return
     */
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }


    /**
     * 经纬度距离计算
     * @param lat1  我的纬度
     * @param lat2  目标纬度
     * @param lng1  我的经度
     * @param lng2  目标经度
     * @return  米
     */
    public static Integer latLngDist(Double lat1,Double lat2,Double lng1,Double lng2){
        // 维度
        double latd1 = (Math.PI / 180) * lat1;
        double latd2 = (Math.PI / 180) * lat2;
        // 经度
        double lond1 = (Math.PI / 180) * lng1;
        double lond2 = (Math.PI / 180) * lng2;
        // 地球半径
        double R = 6371.393;
        // 两点间距离 km，如果想要米的话，结果*1000就可以了
        double d = Math.acos(Math.sin(latd1) * Math.sin(latd2) + Math.cos(latd1) * Math.cos(latd2) * Math.cos(lond2 - lond1)) * R;
        double s = d * 1000;
        return (int)s;
    }

    /**
     * 获得多位的00001数
     * @param id 用户id
     * @param length 数字长度
     * @return
     */
    public static String getID(Integer id ,Integer length){
        String seq = String.valueOf(id);
        String seqfirst = "";
        for (int i = 0; i < length - seq.length(); i++) {
            seqfirst += "0";

        }
        return seq = seqfirst + seq;
    }

    /**
     * 去掉数组中重复的字符
     * @param arr
     * @return
     */
    public static List<String> outRepeat(String[] arr){
        List list = new ArrayList();
        //遍历数组往集合里存元素
        for(int i=0;i<arr.length;i++){
            //如果集合里面没有相同的元素才往里存
            if(!list.contains(arr[i])){
                list.add(arr[i]);
            }
        }
        return list;
    }

    /**
     * 保留2位小数，四舍五入
     * @param f
     * @return
     */
    public static Float floatHalfUp(Float f){
        BigDecimal b = new BigDecimal(f);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * 去除,号分隔的字符串中重复的字符
     * @param str
     * @return
     */
    public static String strOutRepetition(String str){
        String[] test1 = str.split(",");
        List<String> list = new ArrayList();
        for (int i = 0; i < test1.length; i++) {
            if (!list.contains(test1[i]))
                list.add(test1[i]);
        }
        String strr = "";
        for (String string : list){
            strr += ","+string;
        }
        if (!Util.isEmptys(strr,"")){
            strr = strr.substring(1,strr.length());
        }
        return strr;
    }

    /**
     * 元转分，乘100，保留0位小数，多余小数舍弃
     * @param yuan
     * @return
     */
    public static Integer yuan2Fen(Object yuan){
        BigDecimal b = new BigDecimal(yuan+"");
        return b.multiply(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_DOWN).intValue();
    }
    /**
     * 分转元，除100，保,2位小数，多余小数舍弃
     * @param fen
     * @return
     */
    public static Double fen2Yuan(Object fen){
        BigDecimal b = new BigDecimal(fen+"");
        return b.divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
    }

    /**
     *
     * @Title 分页整理
     * @param total		数据总数
     * @param pageSize	每页多少条
     * @param pageNum	当前第几页
     * @return List<Map>
     */
    public static List<Map> getPage(Integer total, Integer pageSize, Integer pageNum){
        List<Map> list = new ArrayList<Map>();
        Integer pageCount = 1;
        if(total%pageSize==0){
            pageCount = total/pageSize;
        }else{
            pageCount = total/pageSize+1;
        }
        if(pageCount<5 || pageNum<4){
            for(int i=1;i<(pageCount+1>6?6:pageCount+1);i++){
                Map map = new HashMap();
                map.put("pageNum",i);
                map.put("pageSize",pageSize);
                map.put("total",total);
                list.add(map);
            }
        }else{
            if(pageNum+2<pageCount+1){
                for(int i=pageNum-2;i<pageNum+3;i++){
                    Map map = new HashMap();
                    map.put("pageNum",i);
                    map.put("pageSize",pageSize);
                    map.put("total",total);
                    list.add(map);
                }
            }else{
                for(int i=pageCount-4;i<pageCount+1;i++){
                    Map map = new HashMap();
                    map.put("pageNum",i);
                    map.put("pageSize",pageSize);
                    map.put("total",total);
                    list.add(map);
                }
            }
        }
        return list;
    }


}
