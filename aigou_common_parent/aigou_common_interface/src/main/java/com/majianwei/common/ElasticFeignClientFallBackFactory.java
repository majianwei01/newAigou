package com.majianwei.common;

import com.majianwei.common.es_productDoc.ProductDoc;
import com.majianwei.util.AjaxResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ElasticFeignClientFallBackFactory implements FallbackFactory<ElasticFeignClient> {

    @Override
    public ElasticFeignClient create(Throwable throwable) {
        return new ElasticFeignClient() {
            @Override
            public AjaxResult addOne(ProductDoc productDoc) {
                return null;
            }

            @Override
            public AjaxResult addBatch(List<ProductDoc> list) {
                return null;
            }

            @Override
            public AjaxResult delOne(Long id) {
                return null;
            }

            @Override
            public AjaxResult delBatch() {
                return null;
            }
        };
    }
}
