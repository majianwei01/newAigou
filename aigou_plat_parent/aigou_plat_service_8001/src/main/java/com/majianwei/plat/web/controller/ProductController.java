package com.majianwei.plat.web.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.majianwei.plat.domain.Product;
import com.majianwei.plat.domain.ProductExt;
import com.majianwei.plat.domain.Specification;
import com.majianwei.plat.query.ProductQuery;
import com.majianwei.plat.service.IProductExtService;
import com.majianwei.plat.service.IProductService;
import com.majianwei.plat.service.ISpecificationService;
import com.majianwei.util.AjaxResult;
import com.majianwei.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    public IProductService productService;
    @Autowired
    public IProductExtService productExtService;
    @Autowired
    public ISpecificationService specificationService;

    /**
    * 保存和修改公用的
    * @param product  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Product product){
        try {
            if(product.getId()!=null){
                productService.updateById(product);
            }else{
                productService.insert(product);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            productService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMsg("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Product get(@PathVariable(value="id",required=true) Long id)
    {
        return productService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Product> list(){

        return productService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Product> json(@RequestBody ProductQuery query)
    {
        Page<Product> page = new Page<Product>(query.getPage(),query.getRows());
            page = productService.selectPage(page);
            return new PageList<Product>(page.getTotal(),page.getRecords());
    }

    /**
     * 显示属性的数据:
     * @param productTypeId
     * @return
     */
    @RequestMapping(value = "/viewPropertiesList/{productTypeId}/{productId}", method = RequestMethod.GET)
    public List<Specification> viewProperties(@PathVariable("productTypeId") Long productTypeId, @PathVariable("productId") Long productId) {
        //判断是添加显示属性还是编辑显示属性：
        //怎么判断：根据当前商品对应的扩展表的显示属性是否有值来判断
        System.out.println("productId"+productId);
        System.out.println("productTypeId"+productTypeId);
        Wrapper<ProductExt> wrapperExt=new EntityWrapper<>();
        wrapperExt.eq("productId",productId);
        //拿到当前对应扩展表的某条数据
        ProductExt productExt = productExtService.selectOne(wrapperExt);
        //&& !productExt.getViewProperties().isEmpty()
        if(productExt!=null && !productExt.getViewProperties().isEmpty() ){
            System.out.println("扩展表中有ViewProperties。。。");
            //有viewProerperties:
            String viewProperties = productExt.getViewProperties();
            System.out.println("viewProperties:"+viewProperties);
            System.out.println("==========================================");
            return  JSON.parseArray(viewProperties, Specification.class);
        }else {
            System.out.println("没有就从属性表中拿ViewProperties。。。");
            //查询这个商品分类的显示属性
            Wrapper<Specification> wrapperSpe = new EntityWrapper<>();
            wrapperSpe.eq("product_type_id", productTypeId);//查询哪一个商品分类
            wrapperSpe.eq("type", 2);//查询显示属性
            List<Specification> specifications = specificationService.selectList(wrapperSpe);
            System.out.println(specifications);
            return specifications;
        }
    }
    @RequestMapping(value = "/viewPropertiesSave", method = RequestMethod.POST)
    public AjaxResult viewPropertiesSave(@RequestBody Map<String, Object> map) {
        try {
            System.out.println(map);
            System.out.println("编辑修改显示属性。。。");
            List<Specification> viewProperties = (List<Specification>) map.get("viewProperties");
            // java.lang.Integer cannot be cast to java.lang.Long
            Long productId = Long.valueOf((Integer) map.get("productId") + "");
            System.out.println(viewProperties);
            // (T entity, Wrapper<T> wrapper);
            //productExt需要跟新的值
            ProductExt productExt = new ProductExt();
            //要的是字符串：
            //fastjson
            String jsonStr = JSON.toJSONString(viewProperties);
            productExt.setViewProperties(jsonStr);
            Wrapper<ProductExt> wrapper = new EntityWrapper<>();
            wrapper.eq("productId", productId);
            //使用wrapper作为一个 where productId =???条件
            productExtService.update(productExt, wrapper);
            return AjaxResult.me().setMsg("操作成功");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("操作失败").setSuccess(false);
        }

    }
    @RequestMapping(value = "/skuProperties/{productTypeId}", method = RequestMethod.GET)
    public List<Specification> viewProperties(@PathVariable("productTypeId") Long productTypeId) {
            //查询这个商品分类的显示属性
            Wrapper<Specification> wrapperSpe = new EntityWrapper<>();
            wrapperSpe.eq("product_type_id", productTypeId);//查询哪一个商品分类
            wrapperSpe.eq("type", 1);//查询显示属性
            List<Specification> specifications = specificationService.selectList(wrapperSpe);
            return specifications;
    }

    @RequestMapping(value = "/skuPropertiesSave", method = RequestMethod.POST)
    public AjaxResult skuPropertiesSave(@RequestBody Map<String, Object> map) {
        try {
            // 接收前台传来的三个参数productId，skuProperties，skuDatas
            List<Map<String,Object>> skuPropertiesSpe = (List<Map<String,Object>>) map.get("skuProperties");
            List<Map<String,String>> skuDatasMap = (List<Map<String, String>>) map.get("skuDatas");
            Long productId = Long.valueOf((Integer) map.get("productId") + "");
            System.out.println("productId:"+productId);
            System.out.println("skuDatasMap:"+skuDatasMap);
            System.out.println("skuPropertiesSpe:"+skuPropertiesSpe);

            //2:调用service的方法：执行逻辑：多张表数据保存
            productService.saveSku(productId,skuDatasMap,skuPropertiesSpe);
            return AjaxResult.me().setMsg("操作成功");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("操作失败").setSuccess(false);
        }

    }

    //商品上架，下架
    @RequestMapping(value = "/dealSale/{saleType}", method = RequestMethod.POST)
    public AjaxResult dealSale(@PathVariable("saleType") Long saleType, @RequestBody String ids) {
        System.out.println("saleType:" + saleType);
        System.out.println("ids:" + ids);
        try {
            if (saleType != null && saleType != 0) {
                if (saleType == 1) {
                    //上架
                    System.out.println("进入到上架操作");
                    productService.onSale(ids);
                } else {
                    //下架
                    productService.offSale(ids);
                }
            }
            return AjaxResult.me().setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg("操作失败:"+e.getMessage());
        }
    }

}
