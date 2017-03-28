package com.readwei.controller.product;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.readwei.common.utils.MathExtend;
import com.readwei.controller.sys.BaseController;
import com.readwei.entity.RwProduct;
import com.readwei.entity.RwProductCategory;
import com.readwei.entity.RwProductImage;
import com.readwei.service.IRwProductCategoryService;
import com.readwei.service.IRwProductImageService;
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
 * 宝贝列表
 * </p>
 *
 * @author hubin
 * @Date 2016-01-06
 */
@Controller
@RequestMapping("/product/goods")
public class ProductGoodsController extends BaseController {

    @Autowired
    private IRwProductService productService;
    @Autowired
    private IRwProductCategoryService productCategoryService;
    @Autowired
    private IRwProductImageService productImageService;

    /**
     * 宝贝列表页面
     */
    @Permission("5002")
    @RequestMapping("/list")
    public String goodsView(Model model) {
        return "product/goods/list";
    }

    /**
     * 宝贝列表
     */
    @Permission("5002")
    @RequestMapping("/getList")
    @ResponseBody
    public String goodsGetList() {
        Page<RwProduct> page = getPage();
        EntityWrapper<RwProduct> wrapper = new EntityWrapper<RwProduct>();
        RwProduct product = new RwProduct();
        wrapper.setEntity(product);
        page.setOrderByField("create_time");
        page.setAsc(false);
        page = productService.selectPage(page, wrapper);
        for (RwProduct pro : page.getRecords()) { // 迭代遍历
            pro.setcName(productCategoryService.selectById(pro.getCategoryId()).getName());
            pro.setPrices(MathExtend.divide(pro.getPrice(), 100, 2));
        }
        return jsonPage(page);
    }

    /**
     * 添加宝贝页面
     *
     * @return
     */
    @Permission("5002")
    @RequestMapping(value = "add/view", method = RequestMethod.GET)
    public String goodsAddView(Model model) {
        EntityWrapper<RwProductCategory> wrapper = new EntityWrapper<RwProductCategory>();
        RwProductCategory category = new RwProductCategory();
        category.setPid(0l);
        wrapper.setEntity(category);
       model.addAttribute(productCategoryService.selectList(wrapper));
        return "product/goods/add";
    }

    /**
     * 保存宝贝实现
     *
     * @param product 宝贝信息
     * @return
     */
    @Permission("5002")
    @RequestMapping(value = "add/do", method = RequestMethod.POST)
    @ResponseBody
    public String goodsSave(RwProduct product) {
        boolean rlt = false;
        product.setCreateTime(new Date());
        product.setModifyTime(new Date());
        rlt = productService.insert(product);
        if (!rlt) {
            return callbackFail("商品保存存失败！！！");
        }
        EntityWrapper<RwProduct> wrapper = new EntityWrapper<RwProduct>();
        wrapper.setEntity(product);
        RwProduct checkProduct = productService.selectOne(wrapper);
        String[] imgUrls = this.request.getParameterValues("imgUrl");
        RwProductImage productImage = new RwProductImage();
        productImage.setPId(checkProduct.getId());
        for (String url : imgUrls) {
            productImage.setCreateTime(new Date());
            productImage.setModifyTime(new Date());
            productImage.setImageUrl(url);
            rlt = productImageService.insert(productImage);
        }
        return callbackSuccess(rlt);
    }
}
