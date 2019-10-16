package com.majianwei.plat.service;

import com.majianwei.plat.domain.Product;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品 服务类
 * </p>
 *
 * @author wbtest
 * @since 2019-03-30
 */
public interface IProductService extends IService<Product> {

    void saveSku(Long productId, List<Map<String, String>> skuDatasMap, List<Map<String, Object>> skuPropertiesSpe);
    //上架
    void onSale(String ids);
    //下架
    void offSale(String ids);
}
