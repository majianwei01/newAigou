package com.majianwei.redis.controller;

import com.majianwei.common.ElasticFeignClient;
import com.majianwei.common.es_productDoc.ProductDoc;
import com.majianwei.redis.ESservice.IEsService;
import com.majianwei.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/common")
public class EsController implements ElasticFeignClient {

    @Autowired
    private IEsService esService;

    @RequestMapping(value = "/es",method = RequestMethod.POST)
    @Override
    public AjaxResult addOne(@RequestBody ProductDoc productDoc) {
        try {
            esService.addOne(productDoc);
            return AjaxResult.me().setMsg("上架成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("上架失败").setSuccess(false);
        }
    }

    @RequestMapping(value = "/esall",method = RequestMethod.POST)
    @Override
    public AjaxResult addBatch( @RequestBody List<ProductDoc> list) {
        try {
            esService.addBatch(list);
            return AjaxResult.me().setMsg("上架成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("上架失败").setSuccess(false);
        }

    }

    @RequestMapping(value = "/es/{id}",method = RequestMethod.DELETE)
    @Override
    public AjaxResult delOne(@PathVariable("id") Long id) {
        try {
            esService.delOne(id);
            return AjaxResult.me().setMsg("下架成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("下架失败").setSuccess(false);
        }
    }

    @RequestMapping(value = "/esall",method = RequestMethod.DELETE)
    @Override
    public AjaxResult delBatch() {
        try {
            esService.delBatch();
            return AjaxResult.me().setMsg("下架成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("下架失败").setSuccess(false);
        }
    }
}
