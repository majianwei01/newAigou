package com.majianwei.redis.ESservice;

import com.majianwei.common.es_productDoc.ProductDoc;

import java.util.List;

public interface IEsService {
    void addOne(ProductDoc productDoc);
    void addBatch(List<ProductDoc> list);
    void delOne(Long id);
    void delBatch();
}
