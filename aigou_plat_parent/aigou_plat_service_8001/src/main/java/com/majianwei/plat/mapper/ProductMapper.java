package com.majianwei.plat.mapper;

import com.majianwei.plat.domain.Product;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 商品 Mapper 接口
 * </p>
 *
 * @author wbtest
 * @since 2019-03-30
 */
public interface ProductMapper extends BaseMapper<Product> {
    void updateBatchState(Map<String,Object> map);
    void updateStateToOffSale(Map<String,Object> map);

}
