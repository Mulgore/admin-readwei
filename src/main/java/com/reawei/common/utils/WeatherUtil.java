package com.reawei.common.utils;


import com.baomidou.kisso.common.IpHelper;
import jdk.nashorn.internal.ir.RuntimeNode;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by xingwu on 2017/5/9.
 */
public class WeatherUtil {

//    http://wthrcdn.etouch.cn/weather_mini?city=北京
//    通过城市名字获得天气数据，json数据
//    http://wthrcdn.etouch.cn/weather_mini?citykey=101010100
//    通过城市id获得天气数据，json数据

    final static String KEY = "b7909838d5354844825b15b68d5593c8";
    /**
     * 获取一周天气<br>
     * 方 法 名：getWeekWeatherMap <br>
     *
     * @param Cityid 城市编码
     */
    public static List<Map<String, Object>> getWeekWeatherMap(String Cityid)
            throws IOException, NullPointerException {
        // 连接中央气象台的API
        URL url = new URL("http://wthrcdn.etouch.cn/weather_mini?citykey=" + Cityid + ".html");
        URLConnection connectionData = url.openConnection();
        connectionData.setConnectTimeout(1000);
        // 得到1到6天的天气情况
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    connectionData.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null)
                sb.append(line);
            String datas = sb.toString();
            System.out.println(datas);
            JSONObject jsonData = JSONObject.fromObject(datas);
            JSONObject info = jsonData.getJSONObject("weatherinfo");
            for (int i = 1; i <= 6; i++) {
                // 得到未来6天的日期
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_YEAR, i - 1);
                Date date = cal.getTime();
                SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("city", info.getString("city").toString());// 城市
                map.put("date_y", sf.format(date));// 日期
                map.put("week", getWeek(cal.get(Calendar.DAY_OF_WEEK)));// 星期
                map.put("fchh", info.getString("fchh").toString());// 发布时间
                map.put("weather", info.getString("weather" + i).toString());// 天气
                map.put("temp", info.getString("temp" + i).toString());// 温度
                map.put("wind", info.getString("wind" + i).toString());// 风况
                map.put("fl", info.getString("fl" + i).toString());// 风速
                // map.put("index", info.getString("index").toString());//
                // 今天的穿衣指数
                // map.put("index_uv", info.getString("index_uv").toString());//
                // 紫外指数
                // map.put("index_tr", info.getString("index_tr").toString());//
                // 旅游指数
                // map.put("index_co", info.getString("index_co").toString());//
                // 舒适指数
                // map.put("index_cl", info.getString("index_cl").toString());//
                // 晨练指数
                // map.put("index_xc", info.getString("index_xc").toString());//
                // 洗车指数
                // map.put("index_d", info.getString("index_d").toString());//
                // 天气详细穿衣指数
                list.add(map);
            }
        } catch (SocketTimeoutException e) {
            System.out.println("连接超时");
        } catch (FileNotFoundException e) {
            System.out.println("加载文件出错");
        }

        return list;

    }

    /**
     * 获取实时天气1<br>
     * 方 法 名： getTodayWeather <br>
     *
     * @param Cityid 城市编码
     */
    public static Map<String, Object> getTodayWeather1(String Cityid)
            throws IOException, NullPointerException {
        // 连接中央气象台的API
        URL url = new URL("http://www.weather.com.cn/data/sk/" + Cityid
                + ".html");
        URLConnection connectionData = url.openConnection();
        connectionData.setConnectTimeout(1000);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    connectionData.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null)
                sb.append(line);
            String datas = sb.toString();
            System.out.println(datas);
            JSONObject jsonData = JSONObject.fromObject(datas);
            JSONObject info = jsonData.getJSONObject("weatherinfo");
            map.put("city", info.getString("city").toString());// 城市
            map.put("temp", info.getString("temp").toString());// 温度
            map.put("WD", info.getString("WD").toString());// 风向
            map.put("WS", info.getString("WS").toString());// 风速
            map.put("SD", info.getString("SD").toString());// 湿度
            map.put("time", info.getString("time").toString());// 发布时间

        } catch (SocketTimeoutException e) {
            System.out.println("连接超时");
        } catch (FileNotFoundException e) {
            System.out.println("加载文件出错");
        }

        return map;

    }


    /**
     * 获取实时天气2<br>
     * 方 法 名： getTodayWeather <br>
     *
     * @param Cityid 城市编码
     */
    public static Map<String, Object> getTodayWeather2(String Cityid)
            throws IOException, NullPointerException {
        // 连接中央气象台的API
        URL url = new URL("http://www.weather.com.cn/data/cityinfo/" + Cityid
                + ".html");
        URLConnection connectionData = url.openConnection();
        connectionData.setConnectTimeout(1000);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    connectionData.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null)
                sb.append(line);
            String datas = sb.toString();
            System.out.println(datas);
            JSONObject jsonData = JSONObject.fromObject(datas);
            JSONObject info = jsonData.getJSONObject("weatherinfo");
            map.put("city", info.getString("city").toString());// 城市
            map.put("temp1", info.getString("temp1").toString());// 最高温度
            map.put("temp2", info.getString("temp2").toString());// 最低温度
            map.put("weather", info.getString("weather").toString());//天气
            map.put("ptime", info.getString("ptime").toString());// 发布时间

        } catch (SocketTimeoutException e) {
            System.out.println("连接超时");
        } catch (FileNotFoundException e) {
            System.out.println("加载文件出错");
        }

        return map;

    }

    private static String getWeek(int iw) {
        String weekStr = "";
        switch (iw) {
            case 1:
                weekStr = "星期天";
                break;
            case 2:
                weekStr = "星期一";
                break;
            case 3:
                weekStr = "星期二";
                break;
            case 4:
                weekStr = "星期三";
                break;
            case 5:
                weekStr = "星期四";
                break;
            case 6:
                weekStr = "星期五";
                break;
            case 7:
                weekStr = "星期六";
                break;
            default:
                break;
        }
        return weekStr;
    }

    public static void main(String[] args) {
//        try {
//            //测试获取实时天气1(包含风况，湿度)
//            Map<String, Object> map = getTodayWeather1("101010100");
//            System.out.println(map.get("city") + "\t" + map.get("temp")
//                    + "\t" + map.get("WD") + "\t" + map.get("WS")
//                    + "\t" + map.get("SD") + "\t" + map.get("time"));
//
//            //测试获取实时天气2(包含天气，温度范围)
//            Map<String, Object> map2 = getTodayWeather2("101010100");
//            System.out.println(map2.get("city") + "\t" + map2.get("temp1")
//                    + "\t" + map2.get("temp2") + "\t" + map2.get("weather")
//                    + "\t" + map2.get("ptime"));
//
//            //测试获取一周天气
//            List<Map<String, Object>> listData = getWeekWeatherMap("101010100");
//            for (int j = 0; j < listData.size(); j++) {
//                Map<String, Object> wMap = listData.get(j);
//                System.out.println(wMap.get("city") + "\t" + wMap.get("date_y")
//                        + "\t" + wMap.get("week") + "\t" + wMap.get("weather")
//                        + "\t" + wMap.get("temp") + "\t" + wMap.get("wind")
//                        + "\t" + wMap.get("fl"));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Map<String,Object> ret = weekWeadTher("121.48789949","31.24916171");
        if (ret != null){
            System.out.println(ret.get("today"));
            System.out.println(ret.get("future"));
        }

    }

    public static Map<String,Object> weekWeadTher(String lon, String lat) {
        Map<String,Object> ret = new HashMap<>();
        String url = "http://apis.haoservice.com/weather/geo?lon="+lon+"&lat="+lat+"8&key="+KEY;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(url);
            HttpResponse response = client.execute(httpget);

            InputStream is = response.getEntity().getContent();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = is.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            String result =new  String(baos.toByteArray(),"UTF-8");
            JSONObject jsonData = JSONObject.fromObject(result);
            System.out.println(jsonData);
            JSONObject results = JSONObject.fromObject(jsonData.getJSONObject("result"));
            JSONObject today = JSONObject.fromObject(results.getJSONObject("today"));
            JSONArray  future = results.getJSONArray("future");
            ret.put("today",today);
            ret.put("future",future);
            return ret;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
