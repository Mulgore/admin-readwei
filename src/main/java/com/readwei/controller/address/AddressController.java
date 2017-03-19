package com.readwei.controller.address;

import com.baomidou.kisso.annotation.Permission;
import com.readwei.controller.sys.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 收货地址表 前端控制器
 * </p>
 *
 * @author xingwu
 * @since 2017-03-19
 */
@Controller
@RequestMapping("/address")
public class AddressController extends BaseController{

    @Permission("9001")
    @RequestMapping("/list")
    public String listView(){
        return "address/list";
    }

    @Permission("9001")
    @RequestMapping("/getList")
    @ResponseBody
    public String getList(){
        return jsonPage(null);
    }
}
