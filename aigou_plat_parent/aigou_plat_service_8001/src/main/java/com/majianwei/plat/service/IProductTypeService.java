package com.majianwei.plat.service;

import com.majianwei.plat.domain.ProductType;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品目录 服务类
 * </p>
 *
 * @author wbtest
 * @since 2019-03-30
 */
public interface IProductTypeService extends IService<ProductType> {
    List<ProductType> treeStructure();
    List<ProductType> treeData();

    List<Map<String, Object>> getCrumbs(Long productTypeId);
}
