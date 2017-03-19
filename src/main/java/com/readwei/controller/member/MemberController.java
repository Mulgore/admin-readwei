package com.readwei.controller.member;

import com.baomidou.kisso.annotation.Permission;
import com.readwei.controller.sys.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author xingwu
 * @since 2017-03-19
 */
@Controller
@RequestMapping("/member")
public class MemberController extends BaseController {

    @Permission("7001")
    @RequestMapping("/list")
    public String listView(){
        return "member/list";
    }

    @Permission("7001")
    @RequestMapping("/getList")
    @ResponseBody
    public String getList(){
        return jsonPage(null);
    }

}
