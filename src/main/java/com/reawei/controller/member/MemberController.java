package com.reawei.controller.member;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.reawei.common.utils.DateUtil;
import com.reawei.common.utils.StringReplaceUtil;
import com.reawei.controller.sys.BaseController;
import com.reawei.entity.RwMember;
import com.reawei.service.IRwMemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

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

    @Resource
    private IRwMemberService memberService;

    /**
     * 会员列表页面
     *
     * @return
     */
    @Permission("7001")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listView() {
        return "member/list";
    }

    /**
     * 会员列表
     *
     * @return
     */
    @Permission("7001")
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @ResponseBody
    public String getList(Integer sort) {
        Page<RwMember> page = getPage();
        EntityWrapper<RwMember> wrapper = new EntityWrapper<RwMember>();
        RwMember member = new RwMember();
        wrapper.setEntity(member);
        if (sort == 1) {
            page.setAsc(true);
        }else {
            page.setAsc(false);
        }
        page.setOrderByField("id");
        page = memberService.selectPage(page, wrapper);
        for (RwMember mem : page.getRecords()) {
            mem.setIdCard(StringReplaceUtil.idCardReplaceWithStar(mem.getIdCard()));
        }
        return jsonPage(page);
    }

    /**
     * 添加会员页面
     *
     * @return
     */
    @Permission("7001")
    @RequestMapping(value = "/add/view", method = RequestMethod.GET)
    public String addMemberView() {
        return "member/add";
    }

    /**
     * 添加会员实现
     *
     * @return
     */
    @Permission("7001")
    @RequestMapping(value = "/save/do", method = RequestMethod.POST)
    @ResponseBody
    public String saveMember(RwMember member, String date) {
        boolean rlt = false;
        member.setCreateTime(new Date());
        member.setModifyTime(new Date());
        member.setBirthday(DateUtil.StrToDate(date));
        rlt = memberService.insert(member);
        return callbackSuccess(rlt);
    }
}
