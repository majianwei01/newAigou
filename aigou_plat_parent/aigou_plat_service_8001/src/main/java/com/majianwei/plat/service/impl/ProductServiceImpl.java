package com.majianwei.plat.service.impl;

import com.alibaba.fastjson.JSON;
import com.majianwei.common.ElasticFeignClient;
import com.majianwei.common.es_productDoc.ProductDoc;
import com.majianwei.plat.domain.Product;
import com.majianwei.plat.domain.ProductExt;
import com.majianwei.plat.mapper.ProductExtMapper;
import com.majianwei.plat.mapper.ProductMapper;
import com.majianwei.plat.mapper.SkuMapper;
import com.majianwei.plat.service.IProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.majianwei.util.commontools.StrUtils;
import com.majianwei.util.commontools.TypeConversion;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author wbtest
 * @since 2019-03-30
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private ProductExtMapper productExtMapper;
    @Autowired
    private ElasticFeignClient elasticFeignClient;


    @Override
    public void saveSku(Long productId, List<Map<String, String>> skuDatasMap, List<Map<String, Object>> skuPropertiesSpe) {



    }

    //上架的实现
    @Override
    public void onSale(String ids) {
        //将前台传过来的ids转换成Long类型的数组集合
        /*  List<Long> longs = StrUtils.splitStr2LongArr(ids);*/
        long[] longs = TypeConversion.getLong(ids);
        List<Long> longList = new ArrayList<>();
        for (long aLong : longs) {
            longList.add(aLong);
            System.out.println("后台转换前台参数为:"+aLong);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("ids", longs);
        map.put("onSaleTime", new Date().getTime());
        //将装了ids的map传到后台在mapper.xml里进行遍历
        productMapper.updateBatchState(map);

        List<Product> productList = productMapper.selectBatchIds(longList);
        //准备一个es对象集合来装从数据库中查出来的数据
        List<ProductDoc> productDocList = new ArrayList<>();
        for (Product product : productList) {
            ProductDoc productDoc = new ProductDoc();
            productDoc.setId(product.getId());
            productDoc.setBrandId(product.getBrandId());
            productDoc.setProductTypeId(product.getProductTypeId());
            productDoc.setSaleCount(product.getSaleCount());
            productDoc.setCommentCount(product.getCommentCount());
            productDoc.setViewCount(product.getViewCount());
            productDoc.setOnSaleTime(product.getOnSaleTime());
            productDoc.setMaxPrice(product.getMaxPrice());
            productDoc.setMinPrice(product.getMinPrice());

            //将标题跟副标题拼在一起。后面在es 中模糊查询
            productDoc.setAll(product.getName()+" "+product.getSubName());

            //viewProperties
            ProductExt productExt = new ProductExt();
            productExt.setProductId(product.getId());
            productExt = productExtMapper.selectOne(productExt);

            //转成json数组的字符串
            String viewProperties = productExt.getViewProperties();
            List<Map> viewmaps = JSON.parseArray(viewProperties, Map.class);
            productDoc.setViewProperties(viewmaps);

            //转成json数组的字符串
            String skuProperties = productExt.getSkuProperties();
            List<Map> skumaps = JSON.parseArray(skuProperties, Map.class);
            productDoc.setViewProperties(skumaps);

            String medias = product.getMedias();
            String[] strings = StrUtils.splitStr2StrArr(medias);
            //转成string的集合
            productDoc.setMedias(Arrays.asList(strings));
            productDocList.add(productDoc);
        }
        //将数据库数据写入ES中
        elasticFeignClient.addBatch(productDocList);
    }

    /*
    * 下架的实现
    * 1.后台接收参数修改状态为下架
    * 2.将后台下架的数据在es中删除
    * */
    @Override
    public void offSale(String ids) {
        //装前台传过来的ids
        long[] longs = TypeConversion.getLong(ids);
        List<Long> longList = new ArrayList<>();
        for (long id : longs) {
            longList.add(id);
            System.out.println("后台转换前台参数为:"+id);
            //es将数据删除
            elasticFeignClient.delOne(id);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("ids", longList);
        map.put("offSaleTime", new Date().getTime());
        //将装了ids的map传到后台在mapper.xml里进行遍历
        productMapper.updateStateToOffSale(map);

    }

}
