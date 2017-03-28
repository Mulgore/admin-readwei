package com.readwei.controller.product;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.readwei.controller.sys.BaseController;
import com.readwei.entity.RwProduct;
import com.readwei.entity.RwProductCategory;
import com.readwei.service.IRwProductCategoryService;
import com.readwei.service.IRwProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * <p>
 * 商品分类
 * </p>
 *
 * @author xingwu
 * @since 2017-03-17
 */
@Controller
@RequestMapping("/product/category")
public class ProductCategoryController extends BaseController {

    @Autowired
    private IRwProductCategoryService productCategoryService;
    @Autowired
    private IRwProductService productService;

    /**
     * 分类列表页面
     */
    @Permission("5002")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String categoryView(Model model) {
        return "product/category/list";
    }// 跳转到商品分类列表页面

    /**
     * 调取商品分类分页接口 selectPage
     * 到数据库中查询商品分类列表
     */
    @Permission("5002")
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @ResponseBody
    public String categoryGetList() {
        Page<RwProductCategory> page = getPage(); // 设置商品分类的分页
        EntityWrapper<RwProductCategory> wrapper = new EntityWrapper<RwProductCategory>(); //设置商品分类的Wrapper
        RwProductCategory category = new RwProductCategory(); // 创建一个商品分类的对象
        category.setPid(0L); // 设置商品分类的Pid为0(顶级)
        wrapper.setEntity(category); // 把商品分类对象赋值给Wrpper
        page.setOrderByField("create_time"); // 设置根据create_time字段排序
        page.setAsc(false);// 设置是否正序排序(flase 否，true 是)
        return jsonPage(productCategoryService.selectPage(page, wrapper)); // 更据productCategoryService接口到数据库中查询商品分类分页
    }

    /**
     * 跳转到添加商品分类页面
     *
     * @return
     */
    @Permission("5002")
    @RequestMapping(value = "/add/view", method = RequestMethod.GET)
    public String addView() {
        return "product/category/add";
    }

    /**
     * 保存商品分类实现，把数据保存到数据库
     *
     * @param category 商品分类信息
     * @return
     */
    @Permission("5002")
    @RequestMapping(value = "/save/do", method = RequestMethod.PUT)
    @ResponseBody
    public String categorySave(RwProductCategory category) {
        boolean rlt = false;
        category.setPid(0L);
        category.setCreateTime(new Date());
        category.setModifyTime(new Date());
        rlt = productCategoryService.insert(category);
        if (!rlt) {// 添加失败的时候，返回值为fasle，给前端传递一个message
            return callbackFail("商品分类添加失败！！！");
        }
        return callbackSuccess(rlt);
    }

    /**
     * 删除商品分类实现方法
     *
     * @param id 商品分类Id
     * @return
     */
    @Permission("5002")
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ResponseBody
    public String categoryDel(Integer id) {
        boolean rlt = false;
        RwProductCategory category = productCategoryService.selectById(id); // 判断分类石否存在
        if (category == null) {
            return callbackFail("商品分类不存在！！！");
        }
        EntityWrapper<RwProduct> wrapper = new EntityWrapper<RwProduct>();
        RwProduct product = new RwProduct();
        product.setCategoryId(id*1l);
        wrapper.setEntity(product);
        int count = productService.selectCount(wrapper); // 判断商品分类是否在使用
        if (count > 0) {
            return callbackFail("商品分类正在使用！！！");
        }
        rlt = productCategoryService.deleteById(id); // 删除分类实现
        if (!rlt) {
            return callbackFail("商品分类删除失败！！！");
        }
        return callbackSuccess(rlt);
    }

    /**
     * 修改商品分类页面
     *
     * @return
     */
    @Permission("5002")
    @RequestMapping(value = "/edit/view", method = RequestMethod.GET)
    public String categoryEditView() {
        return "product/category/edit";
    }

    /**
     * 查询商品分类详情
     *
     * @param id 商品分类Id
     * @return
     */
    @Permission("5002")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public String categoryInfo(Integer id) {
        return toJson(productCategoryService.selectById(id));
    }

    /**
     * 商品分类修改实现方法
     *
     * @param category 商品分类修改
     * @return
     */
    @Permission("5002")
    @RequestMapping(value = "/edit/do", method = RequestMethod.POST)
    @ResponseBody
    public String categoryEditDo(RwProductCategory category) {
        boolean rlt = false;
        category.setModifyTime(new Date());
        rlt = productCategoryService.updateById(category);
        if (!rlt){
            return callbackFail("商品分类修改失败！！！");
        }
        return callbackSuccess(rlt);
    }
}
