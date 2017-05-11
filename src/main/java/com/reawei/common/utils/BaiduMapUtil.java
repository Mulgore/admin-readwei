package com.reawei.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


public class BaiduMapUtil {

    final static String BAIDU_AK = "R62KkaKOTkOHC93qDWipbg0sON1Pwvc6";
    final static String BAIDU_SK = "v7hacvgEy3XVpjy9Ycgn7lL3aFY8ls5B";

    public static void main(String[] args) throws Exception {
        BaiduMapUtil snTest = new BaiduMapUtil();
//        String order = "120.133378, 30.181629";
//        String location = "";
//        String[] arr = order.split(",");
//        String province = "未知";
//
//        if (arr != null && arr.length == 2 &&(isNumeric(arr[0].replace(" ", "")) && isNumeric(arr[1].replace(" ", "")))) {
//            location = arr[1].replace(" ", "") + "," + arr[0].replace(" ", "");
//            String name = snTest.get(location);
//            System.out.println(name);
//            System.out.println(name.substring(name.indexOf("status") + 8, name.indexOf(",")));
//            if (name.substring(name.indexOf("status") + 8, name.indexOf(",")).equals("0")) {
//                name = name.substring(name.indexOf("(") + 1, name.lastIndexOf(")"));
//                System.out.println(name);
//                JSONObject names = JSONObject.fromObject(name);
//                JSONObject results = JSONObject.fromObject(names.getString("result"));
//                JSONObject addressComponents = JSONObject.fromObject(results.getString("addressComponent"));
//                province = addressComponents.getString("province");
//            }
//        }
//        System.out.println(province);
//        snTest.Post();
        System.out.println(snTest.getLocationByIP("123.206.181.24"));
    }

    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]+.*[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
    public String get(String location) throws Exception {
        /**
         * ak设置了sn校验不能直接使用必须在url最后附上sn值，get请求计算sn跟url中参数对出现顺序有关，需按序填充paramsMap，
         * post请求是按字母序填充，具体参照testPost()
         */
        Map paramsMap = new LinkedHashMap<String, String>();
        paramsMap.put("callback", "renderReverse");
        paramsMap.put("location", location);
        paramsMap.put("output", "json");
        paramsMap.put("pois", "1");
        paramsMap.put("ak", BAIDU_AK);

        // 调用下面的toQueryString方法，对paramsMap内所有value作utf8编码
        String paramsStr = toQueryString(paramsMap);

        // 对paramsStr前面拼接上/geocoder/v2/?，后面直接拼接yoursk
        String wholeStr = new String("/geocoder/v2/?" + paramsStr + BAIDU_SK);

        // 对上面wholeStr再作utf8编码
        String tempStr = URLEncoder.encode(wholeStr, "UTF-8");

        // 调用下面的MD5方法得到sn签名值
        String sn = MD5(tempStr);

        // 算得sn后发送get请求
        HttpClient client = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(
                "http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=" + location + "&output=json&pois=1&ak=" + BAIDU_AK + "&sn="
                        + sn);
        HttpResponse response = client.execute(httpget);
        InputStream is = response.getEntity().getContent();
        String result = inStream2String(is);
        // 打印响应内容
        return result;
    }

    public void Post() throws Exception {
        /**
         * 以http://api.map.baidu.com/geodata/v3/geotable/create创建表为例
         */
        LinkedHashMap<String, String> paramsMap = new LinkedHashMap<String, String>();
        paramsMap.put("geotype", "1");
        paramsMap.put("ak", "yourak");
        paramsMap.put("name", "geotable80");
        paramsMap.put("is_published", "1");

        // post请求是按字母序填充，对上面的paramsMap按key的字母序排列
        Map<String, String> treeMap = new TreeMap<String, String>(paramsMap);
        String paramsStr = toQueryString(treeMap);

        String wholeStr = new String("/geodata/v3/geotable/create?" + paramsStr
                + "yoursk");
        String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
        // 调用下面的MD5方法得到sn签名值
        String sn = MD5(tempStr);

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(
                "http://api.map.baidu.com/geodata/v3/geotable/create");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("geotype", "1"));
        params.add(new BasicNameValuePair("ak", "yourak"));
        params.add(new BasicNameValuePair("name", "geotable80"));
        params.add(new BasicNameValuePair("is_published", "1"));
        params.add(new BasicNameValuePair("sn", sn));
        HttpEntity formEntity = new UrlEncodedFormEntity(params);
        post.setEntity(formEntity);
        HttpResponse response = client.execute(post);
        InputStream is = response.getEntity().getContent();
        String result = inStream2String(is);
        // 打印响应内容
        System.out.println(result);
    }

    // 对Map内所有value作utf8编码，拼接返回结果
    public String toQueryString(Map<?, ?> data)
            throws UnsupportedEncodingException, URIException {
        StringBuffer queryString = new StringBuffer();
        for (Entry<?, ?> pair : data.entrySet()) {
            queryString.append(pair.getKey() + "=");
            // queryString.append(URLEncoder.encode((String) pair.getValue(),
            // "UTF-8") + "&");
            queryString.append(URIUtil.encodeQuery((String) pair.getValue(),
                    "UTF-8") + "&");
        }
        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }
        return queryString.toString();
    }

    // MD5计算方法，调用了MessageDigest库函数，并把byte数组结果转换成16进制
    public String MD5(String md5) {
        try {
            MessageDigest md = MessageDigest
                    .getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return null;

    }


    public String getLocationByIP(String ip) throws Exception {
        /**
         * ak设置了sn校验不能直接使用必须在url最后附上sn值，get请求计算sn跟url中参数对出现顺序有关，需按序填充paramsMap，
         * post请求是按字母序填充，具体参照testPost()
         */
        Map paramsMap = new LinkedHashMap<String, String>();
        paramsMap.put("ip", ip);
        paramsMap.put("coor", "bd09ll");
        paramsMap.put("ak", BAIDU_AK);

        // 调用下面的toQueryString方法，对paramsMap内所有value作utf8编码
        String paramsStr = toQueryString(paramsMap);

        // 对paramsStr前面拼接上/geocoder/v2/?，后面直接拼接yoursk
        String wholeStr = new String("/location/ip?" + paramsStr + BAIDU_SK);

        // 对上面wholeStr再作utf8编码
        String tempStr = URLEncoder.encode(wholeStr, "UTF-8");

        // 调用下面的MD5方法得到sn签名值
        String sn = MD5(tempStr);

        // 算得sn后发送get请求
        HttpClient client = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(
                "http://api.map.baidu.com/location/ip?coor=bd09ll&ip=" + ip + "&ak=" + BAIDU_AK + "&sn="
                        + sn);
        HttpResponse response = client.execute(httpget);
        InputStream is = response.getEntity().getContent();
        String result = inStream2String(is);
        // 打印响应内容
        return result;
    }

    // 将输入流转换成字符串
    private static String inStream2String(InputStream is) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len = -1;
        while ((len = is.read(buf)) != -1) {
            baos.write(buf, 0, len);
        }
        return new String(baos.toByteArray(), "UTF-8");
    }
}
