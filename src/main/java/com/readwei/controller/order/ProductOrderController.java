package com.readwei.controller.order;

import com.baomidou.kisso.annotation.Permission;
import com.readwei.controller.sys.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author xingwu
 * @since 2017-03-19
 */
@Controller
@RequestMapping("/order")
public class ProductOrderController extends BaseController{

    @Permission("6001")
    @RequestMapping("/list")
    public String listView(){
        return "order/list";
    }

    @Permission("6001")
    @RequestMapping("/getList")
    @ResponseBody
    public String getList(){
        return jsonPage(null);
    }
}
