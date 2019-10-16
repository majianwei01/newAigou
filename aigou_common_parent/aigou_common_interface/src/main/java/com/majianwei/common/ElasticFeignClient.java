package com.majianwei.common;

import com.majianwei.common.es_productDoc.ProductDoc;
import com.majianwei.util.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/common")
@FeignClient(value = "COMMON-SERVICE",fallbackFactory = ElasticFeignClientFallBackFactory.class)
public interface ElasticFeignClient {
    @RequestMapping(value = "/es",method = RequestMethod.POST)
    AjaxResult addOne(@RequestBody ProductDoc productDoc);

    @RequestMapping(value = "/esall",method = RequestMethod.POST)
    AjaxResult addBatch(@RequestBody List<ProductDoc> list);

    @RequestMapping(value = "/es/{id}",method = RequestMethod.DELETE)
    AjaxResult delOne(@PathVariable("id") Long id);

    @RequestMapping(value = "/esall",method = RequestMethod.DELETE)
    AjaxResult delBatch();
}
