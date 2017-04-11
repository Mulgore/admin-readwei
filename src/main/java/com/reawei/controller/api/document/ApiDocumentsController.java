package com.reawei.controller.api.document;

import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.plugins.Page;
import com.reawei.common.Constant;
import com.reawei.common.utils.DateUtil;
import com.reawei.controller.sys.BaseController;
import com.reawei.entity.RwDocument;
import com.reawei.service.IRwDocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前端接口 文档
 * Created by xingwu on 2017/4/11.
 */
@Controller
@RequestMapping("/api")
public class ApiDocumentsController extends BaseController {

    @Resource
    private IRwDocumentService documentService;

    @Permission(action = Action.Skip)
    @RequestMapping("/documents/getList")
    @ResponseBody
    public String getDocumentsList(Integer current) {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Page<RwDocument> page = getPage(Constant.PAGE_SIZE);
        page.setCurrent(current);
        page.setOrderByField("click_rate");
        page.setAsc(false);
        page = documentService.selectPage(page);
        for (RwDocument doc : page.getRecords()) {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("id", doc.getId());
            data.put("title", doc.getTitle());
            data.put("author", doc.getAuthor());
            data.put("clickRate", doc.getClickRate());
            data.put("description", doc.getDescription());
            data.put("contents", doc.getContents());
            data.put("createTime", DateUtil.DateToStr(doc.getCreateTime()));
            list.add(data);
        }
        return toJson(list);
    }

}
