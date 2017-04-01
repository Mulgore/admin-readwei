package com.reawei.controller.express;

import com.baomidou.kisso.annotation.Permission;
import com.reawei.controller.sys.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 物流表 前端控制器
 * </p>
 *
 * @author xingwu
 * @since 2017-03-19
 */
@Controller
@RequestMapping("/express")
public class ExpressController extends BaseController {

    @Permission("8001")
    @RequestMapping("/list")
    public String listView(){
        return "/express/list";
    }

    @Permission("8001")
    @RequestMapping("/getList")
    @ResponseBody
    public String getList(){
        return jsonPage(null);
    }

}
