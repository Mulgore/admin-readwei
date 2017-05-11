package com.reawei.controller.sys;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.kisso.common.IpHelper;
import com.reawei.common.utils.GetPlaceByIp;
import com.reawei.common.utils.WeatherUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * <p>
 * 图表演示
 * </p>
 *
 * @author hubin
 * @Date 2016-05-11
 */
@Controller
@RequestMapping("/sys/echarts")
public class EchartsController extends BaseController {


    /**
     * 地图
     */
    @Permission(action = Action.Skip)
    @RequestMapping("/map")
    public String map(Model model) {
        String ipAddress = IpHelper.getIpAddr(this.request);
        Map<String, String> lonLatData = GetPlaceByIp.getlonLatByIP(ipAddress);
        String city = "";
        if (lonLatData != null) {
            Map<String, Object> weatherData = WeatherUtil.weekWeadTher(lonLatData.get("lon"), lonLatData.get("lat"));
            if (weatherData != null) {
                logger.info("===================== { today weather= " + weatherData.get("today") + " } ==============");
                logger.info("===================== { future weather= " + weatherData.get("future") + " } ==============");
                JSONObject today = (JSONObject) weatherData.get("today");
                JSONArray future = (JSONArray) weatherData.get("future");
                city = today.get("city").toString();
                for (Object arr : future) {
                    JSONObject val = (JSONObject) weatherData.get("arr");
                    val.get("temperature");
                }
            }
        }
        model.addAttribute("city", city);
        return "/echarts/map";
    }

}
