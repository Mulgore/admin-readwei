package com.reawei.common.utils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;


/**
 * java根据url获取json对象
 *
 * @author openks
 * @since 2013-7-16
 * 需要添加java-json.jar才能运行
 */
public class GetPlaceByIp {

    final static String BAIDU_AK = "R62KkaKOTkOHC93qDWipbg0sON1Pwvc6";

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = JSONObject.parseObject(jsonText);
            return json;
        } finally {
            is.close();
            // System.out.println("同时 从这里也能看出 即便return了，仍然会执行finally的！");
        }
    }

    public static Map<String,String> getlonLatByIP(String ip) {
        Map<String, String> ret = new HashMap<>();
        //这里调用百度的ip定位api服务 详见 http://api.map.baidu.com/lbsapi/cloud/ip-location-api.htm
        try {
            JSONObject json = readJsonFromUrl("http://api.map.baidu.com/location/ip?ak="+BAIDU_AK+"&ip=" + ip + "&coor=bd09ll");
            System.out.println(json.toString());
            JSONObject location = JSONObject.parseObject(((JSONObject) json.get("content")).get("point").toString());
            ret.put("lat",location.get("y").toString());
            ret.put("lon",location.get("x").toString());
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException, JSONException {
        System.out.println(getlonLatByIP("123.206.181.24"));
    }
}