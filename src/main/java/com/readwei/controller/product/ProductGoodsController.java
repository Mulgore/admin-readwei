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
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String goodsView(Model model) {
        return "product/goods/list";
    }

    /**
     * 宝贝列表
     */
    @Permission("5002")
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
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
    @RequestMapping(value = "/add/view", method = RequestMethod.GET)
    public String goodsAddView(Model model) {
        EntityWrapper<RwProductCategory> wrapper = new EntityWrapper<RwProductCategory>();
        RwProductCategory category = new RwProductCategory();
        category.setPid(0l);
        wrapper.setEntity(category);
        model.addAttribute("category", productCategoryService.selectList(wrapper));
        return "product/goods/add";
    }

    /**
     * 保存宝贝实现
     *
     * @param product 宝贝信息
     * @return
     */
    @Permission("5002")
    @RequestMapping(value = "/add/do", method = RequestMethod.POST)
    @ResponseBody
    public String goodsSave(RwProduct product, Double prices) {
        boolean rlt = false;

        product.setModifyTime(new Date());
        product.setPrice((int) MathExtend.divide(prices, 0.01, 2));
        product.setStatus(0);
        if (product.getId() == null) {
            product.setCreateTime(new Date());
            rlt = productService.insert(product);
            if (!rlt) {
                return callbackFail("商品保存存失败！！！");
            }
            EntityWrapper<RwProduct> wrapper = new EntityWrapper<RwProduct>();
            wrapper.setEntity(product);
            RwProduct checkProduct = productService.selectOne(wrapper);
            if (checkProduct == null) {
                return callbackFail("商品添加不完整，主图为添加失败！！！");
            }
            String[] imgUrls = this.request.getParameterValues("imgUrl");
            RwProductImage productImage = new RwProductImage();
            productImage.setPId(checkProduct.getId());
            for (String url : imgUrls) {
                if (url != null && url.equals(" ")) {
                    productImage.setCreateTime(new Date());
                    productImage.setModifyTime(new Date());
                    productImage.setImageUrl(url);
                    rlt = productImageService.insert(productImage);
                    if (!rlt) {
                        return callbackFail("商品主图保存失败！！！");
                    }
                }
            }
        }
        if (product.getId() != null) {
            rlt = productService.updateById(product);
            if (!rlt) {
                return callbackFail("商品保存存失败！！！");
            }

            String[] imgUrls = this.request.getParameterValues("imgUrl");
            RwProductImage productImage = new RwProductImage();
            productImage.setPId(product.getId());

            for (String url : imgUrls) {
                if (url != null && url.equals(" ")) {
                    productImage.setCreateTime(new Date());
                    productImage.setModifyTime(new Date());
                    productImage.setImageUrl(url);
                    rlt = productImageService.insertOrUpdate(productImage);
                    if (!rlt) {
                        return callbackFail("商品主图修改失败！！！");
                    }
                }
            }
        }


        return callbackSuccess(rlt);
    }

    /**
     * 删除宝贝(物理删除)
     *
     * @param id 宝贝ID
     * @return
     */
    @Permission("5002")
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ResponseBody
    public String goodsDel(Long id) {
        boolean rlt = false;
        rlt = productService.deleteById(id);
        if (!rlt) {
            return callbackFail("商品删除失败！！！");
        }
        return callbackSuccess(rlt);
    }


    /**
     * 宝贝详情页面
     *
     * @return
     */
    @Permission("5002")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String goodsInfoView(Model model, Long goodsId) {
        EntityWrapper<RwProductCategory> wrapper = new EntityWrapper<RwProductCategory>();
        RwProductCategory category = new RwProductCategory();
        category.setPid(0l);
        wrapper.setEntity(category);
        model.addAttribute("category", productCategoryService.selectList(wrapper));
        RwProduct product = productService.selectById(goodsId);
        product.setPrices(MathExtend.divide(product.getPrice(), 100, 2));
        model.addAttribute("goods", product);
        return "product/goods/add";
    }
}
